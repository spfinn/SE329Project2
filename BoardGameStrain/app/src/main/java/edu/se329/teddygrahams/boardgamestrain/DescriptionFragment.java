package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Fragment used to show a basic description of a given boardgame.
 */
public class DescriptionFragment extends Fragment {
    private BoardGame game;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_description, container, false);

        EditText title = (EditText) rootView.findViewById(R.id.game_title);
        EditText minPlayers = (EditText) rootView.findViewById(R.id.min_players);
        EditText maxPlayers = (EditText) rootView.findViewById(R.id.max_players);
        EditText gameLength = (EditText) rootView.findViewById(R.id.game_length);
        EditText gameDescription = (EditText) rootView.findViewById(R.id.game_description);
        EditText gameNotes = (EditText) rootView.findViewById(R.id.game_notes);

        title.setText(game.getName());
        maxPlayers.setText(game.getMaxPlayers()+"");
        minPlayers.setText(game.getMinPlayers()+"");
        gameLength.setText(game.getPlayTime()+"");
        gameNotes.setText(game.getNotes());
        gameDescription.setText("Not Available");

        Button back = (Button) rootView.findViewById(R.id.back_butt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }
    public void setBoardGame(BoardGame game){
        this.game = game;
    }

    @Override
    public void onPause(){

        saveGameInfo();

        super.onPause();
    }

    private void saveGameInfo(){
        //Grab new text field data and set the values for this game.
        EditText title = (EditText) rootView.findViewById(R.id.game_title);
        game.setName(""+title.getText());
        EditText minPlayers = (EditText) rootView.findViewById(R.id.min_players);
        game.setMinPlayers(Integer.parseInt(minPlayers.getText().toString()));
        EditText maxPlayers = (EditText) rootView.findViewById(R.id.max_players);
        game.setMaxPlayers(Integer.parseInt(maxPlayers.getText().toString()));
        EditText gameLength = (EditText) rootView.findViewById(R.id.game_length);
        game.setPlayTime(Integer.parseInt(gameLength.getText().toString()));

        EditText gameDescription = (EditText) rootView.findViewById(R.id.game_description);
        EditText gameNotes = (EditText) rootView.findViewById(R.id.game_notes);
    }
}
