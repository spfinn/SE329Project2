package edu.se329.teddygrahams.boardgamestrain;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Main Activity and starting point of the android project.
 * This is only used to start the first fragment and extend a menu to return to the home page.
 */
public class MainActivity extends Activity {

    public static ArrayList<BoardGame> allGamesList = new ArrayList<BoardGame>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set content in case device is rotated
        setContentView(R.layout.activity_main);

        readGamesFromFile();

        if(savedInstanceState == null) {//do not add a new fragment if one already exists.
            getFragmentManager().beginTransaction().add(R.id.content_main, new MainFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //make all fragments have the main menu item
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.find_more)
            getFragmentManager().beginTransaction().replace(R.id.content_main, new MainFragment()).commit();
        else if(id == R.id.add_game){
            getFragmentManager().beginTransaction().replace(R.id.content_main, new AddGameFragment()).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void readGamesFromFile(){
        JSONUtil jUtil = new JSONUtil(this);
        JSONObject data = jUtil.readFromFile("all_games");

        try {
            JSONArray gamesArray = data.getJSONArray("all_games");
            if(gamesArray.length()==0){
                Log.i("Result", "Array Empty, loading from default file...");
                JSONObject jObj = jUtil.readFromAssets("all_games.json");
                gamesArray = jObj.getJSONArray("all_games");
                jUtil.saveToFile("all_games", jObj);
            }

            allGamesList = jUtil.jsonArrayToGamesList(gamesArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("All Games List","allGamesList set with " +allGamesList.size()+" games...");
    }

    public void writeAllGamesListToFile(){
        JSONUtil jUtil = new JSONUtil(this);
        JSONObject jObj = jUtil.convertGamesListToJsonObject(allGamesList);
        jUtil.saveToFile("all_games", jObj);
    }

    /**
     * Converts allGamesList into JSON data and writes it to the server.
     */
    public void writeFileToServer(){
        DBAccess dbAccess = new DBAccess();

        JSONUtil jUtil = new JSONUtil(this);
        JSONObject jObj = jUtil.convertGamesListToJsonObject(allGamesList);

        dbAccess.writeToServer("all_games", jObj.toString());
    }

    /**
     * Reads specified file from the server and converts it's contents to a list of
     * BoardGame objects.
     */
    public void readFileFromServer(String filename){
        DBAccess dbAccess = new DBAccess();
        JSONUtil jUtil = new JSONUtil(this);

        String fileContents = dbAccess.readFromServer(filename);

        JSONObject jObj = null;
        JSONArray gamesArray = null;
        try {
            jObj = new JSONObject(fileContents);
            gamesArray = jObj.getJSONArray("all_games");
        } catch (JSONException e) {e.printStackTrace();}

        allGamesList = jUtil.jsonArrayToGamesList(gamesArray);
    }
}
