package edu.se329.teddygrahams.boardgamestrain;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            setContentView(R.layout.activity_main);
            getFragmentManager().beginTransaction().add(R.id.content_main, new MainFragment()).commit();
        }
    }
}
