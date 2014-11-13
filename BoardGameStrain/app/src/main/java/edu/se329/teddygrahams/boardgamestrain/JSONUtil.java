package edu.se329.teddygrahams.boardgamestrain;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONUtil {

    static Context cntxt;

    public JSONUtil(Context context){
        cntxt = context;
    }

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

    public ArrayList<BoardGame> jsonArrayToGamesList(JSONArray gamesArray){
        ArrayList<BoardGame> gamesToAdd = new ArrayList<BoardGame>();
        String title = "";
        int min = 0;
        int max = 0;
        int length = 0;
        int rating = 0;

        for(int i=0; i<gamesArray.length(); i++){
            try {
                JSONObject aGame = gamesArray.getJSONObject(i);
                title = "";
                min = 0;
                max = 0;
                length = 0;
                rating = 0;
                title = aGame.getString("title");
                min = aGame.getInt("minplayers");
                max = aGame.getInt("maxplayers");
                length = aGame.getInt("length");
                rating = aGame.getInt("rating");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            gamesToAdd.add(new BoardGame(title, min, max, length, rating));
        }
        return gamesToAdd;
    }
}