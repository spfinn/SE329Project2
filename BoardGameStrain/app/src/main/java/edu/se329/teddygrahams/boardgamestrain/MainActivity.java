package edu.se329.teddygrahams.boardgamestrain;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.content_main, new MainFragment()).commit();

    }

}
