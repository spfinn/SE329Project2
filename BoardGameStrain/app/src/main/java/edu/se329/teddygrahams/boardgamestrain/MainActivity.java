package edu.se329.teddygrahams.boardgamestrain;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Main Activity and starting point of the android project.
 * This is only used to start the first fragment and extend a menu to return to the home page.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set content in case device is rotated
        setContentView(R.layout.activity_main);

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
}
