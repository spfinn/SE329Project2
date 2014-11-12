package edu.se329.teddygrahams.boardgamestrain;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
}