package edu.se329.teddygrahams.boardgamestrain;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Carries out JSON operations including file read/write,
 * object creation from JSON data, and JSON data creation from objects.
 */
public class JSONUtil {

    static Context cntxt;

    public JSONUtil(Context context){
        cntxt = context;
    }

    /**
     * Reads a JSON file from hidden internal memory folder for the app.
     * @param filename The file to read.
     * @return The JSON object read from the file.
     */
    public static JSONObject readFromFile(String filename){
        String jsonText = null;
        JSONObject jObj = null;
        try{
            FileInputStream is = cntxt.openFileInput(filename);
            byte [] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            jsonText = new String(buffer);
            Log.i("JSON", filename+" contents: " + jsonText);
        }
        catch(FileNotFoundException e){
            Log.e("JSON", filename + " not found. Creating template.");
            jsonText = "{\""+filename+"\":[]}";
            try {
                saveToFile(filename, new JSONObject(jsonText));
            } catch (JSONException e1) {e1.printStackTrace();}
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try { jObj = new JSONObject(jsonText); }
        catch (JSONException e){e.printStackTrace(); }

        return jObj;
    }

    /**
     * Writes a JSONObject to file.
     * @param filename Desired file name.
     * @param fullDataObj The JSONObject to write to file.
     * @return
     */
    public static boolean saveToFile(String filename, JSONObject fullDataObj) {
        try {
            FileOutputStream fos = cntxt.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(fullDataObj.toString().getBytes());
            fos.close();
            Log.i("JSON", "File: " + filename + " updated.\nNew Contents: " + fullDataObj.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Reads a JSON file from assets folder.
     * @param filename The file to read.
     * @return The JSON object read from the file.
     */
    public JSONObject readFromAssets(String filename){

        String jsonText = null;
        JSONObject jObj = null;

        // attempt to read the specified asset.
        try {
            AssetManager assetManager = cntxt.getAssets();
            InputStream str =  assetManager.open(filename);

            byte [] buffer = new byte[str.available()];
            while (str.read(buffer) != -1);
            jsonText = new String(buffer);
            Log.i("JSON", filename+" contents: " + jsonText);



        } catch (IOException e) {
            e.printStackTrace();
        }

        // make String into JSONObject
        try { jObj = new JSONObject(jsonText); }
        catch (JSONException e){e.printStackTrace(); }

        return jObj;
    }

    /**
     * Parses JSON and creates BoardGame objects.
     * @param gamesArray JSONArray to parse.
     * @return A list of BoardGame objects converted from JSONArray parameter.
     */
    public ArrayList<BoardGame> jsonArrayToGamesList(JSONArray gamesArray){
        ArrayList<BoardGame> gamesToAdd = new ArrayList<BoardGame>();

        // TODO - Any additional BoardGame attributes added later need added below.
        String title = "";
        String notes = "";
        String desc = "";
        boolean favorite = false;
        int min = 0;
        int max = 0;
        int length = 0;
        int rating = 0;
        for(int i=0; i<gamesArray.length(); i++){
            title = "";
            notes = "";
            desc = "";
            favorite=false;
            min = 0;
            max = 0;
            length = 0;
            rating = 0;

            JSONObject aGame = null;
            try {
                aGame = gamesArray.getJSONObject(i);
            } catch (JSONException e) {e.printStackTrace();continue;}

            // get the JSON attribute.
            try {title = aGame.getString("title");}catch (JSONException e) {e.printStackTrace();}
            try {min = aGame.getInt("minplayers");}catch (JSONException e) {e.printStackTrace();}
            try {max = aGame.getInt("maxplayers");}catch (JSONException e) {e.printStackTrace();}
            try {length = aGame.getInt("length");}catch (JSONException e) {e.printStackTrace();}
            try {rating = aGame.getInt("rating");}catch (JSONException e) {}
            //Additional BoardGame attributes can be added above this line.
            try {notes = aGame.getString("notes");}catch (JSONException e) {}
            try {desc = aGame.getString("desc");}catch (JSONException e) {}
            try {favorite = aGame.getBoolean("favorite");}catch (JSONException e) {}

            // Below is for debugging.
            try {String notFound = aGame.getString("Never_Found");}
            catch (JSONException e) {
                Log.i("Game Parser","Done Parsing: " + title);
            }
           BoardGame game = new BoardGame(title, min, max, length, rating);
           game.setNotes(notes);
           game.setDescription(desc);
           game.setFavorite(favorite);

            // create a BoardGame obj from newly parsed variables and add to list.
            gamesToAdd.add(game);
        }
        return gamesToAdd;
    }

    /**
     * Encodes a list of BoardGame objects to JSON data.
     * @param games
     * @return
     */
    public JSONObject convertGamesListToJsonObject(ArrayList<BoardGame> games) {
        JSONObject toReturn = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject aGameObj = null;
        try {
            // for each BoardGame object, encode a JSONObject with the attributes.
            for(int i = 0 ; i < games.size(); i ++)
            {
                BoardGame aGame = games.get(i);
                if(aGame.isEnd())
                    continue;
                //TODO Here is where you place elements into JSONObject
                aGameObj = new JSONObject();
                aGameObj.put("title", aGame.getName());
                aGameObj.put("minplayers", aGame.getMinPlayers());
                aGameObj.put("maxplayers", aGame.getMaxPlayers());
                aGameObj.put("length", aGame.getPlayTime());
                //Additional BoardGame attributes can be added above this line.
                aGameObj.put("notes", aGame.getNotes());
                aGameObj.put("desc", aGame.getDescription());
                aGameObj.put("favorite", aGame.isFavorite());
                aGameObj.put("rating", aGame.getRatingValue());

                array.put(i, aGameObj);// place JSONObject into JSONArray.
            }

            // Place JSONArray into final JSONObject
            toReturn.put("all_games", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}