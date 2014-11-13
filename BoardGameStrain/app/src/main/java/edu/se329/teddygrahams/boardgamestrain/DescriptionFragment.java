package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONObject;

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
        EditText rating = (EditText) rootView.findViewById(R.id.rating);
        EditText gameDescription = (EditText) rootView.findViewById(R.id.game_description);
        EditText gameNotes = (EditText) rootView.findViewById(R.id.game_notes);
        CheckBox favorite = (CheckBox) rootView.findViewById(R.id.game_checkbox);

        title.setText(game.getName());
        maxPlayers.setText(game.getMaxPlayers()+"");
        minPlayers.setText(game.getMinPlayers()+"");
        gameLength.setText(game.getPlayTime()+"");
        favorite.setChecked(game.isFavorite());
        rating.setText(game.getRatingValue()+"");
        gameDescription.setText(game.getDescription());
        gameNotes.setText(game.getNotes());

        Button save = (Button) rootView.findViewById(R.id.save_butt);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGameInfo();
                getActivity().onBackPressed();
            }
        });
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
        EditText rating = (EditText) rootView.findViewById(R.id.rating);
        game.setRatingValue(Integer.parseInt(rating.getText().toString()));
        EditText gameDescription = (EditText) rootView.findViewById(R.id.game_description);
        game.setDescription(gameDescription.getText().toString());
        EditText gameNotes = (EditText) rootView.findViewById(R.id.game_notes);
        game.setNotes(gameNotes.getText().toString());
        CheckBox favorite = (CheckBox) rootView.findViewById(R.id.game_checkbox);
        game.setFavorite(favorite.isChecked());
        JSONUtil jUtil = new JSONUtil(getActivity());
        JSONObject jObj = jUtil.convertGamesListToJsonObject(MainActivity.allGamesList);
        jUtil.saveToFile("all_games", jObj);
    }
}
