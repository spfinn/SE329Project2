package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        EditText title = (EditText) view.findViewById(R.id.game_title);
        EditText minPlayers = (EditText) view.findViewById(R.id.min_players);
        EditText maxPlayers = (EditText) view.findViewById(R.id.max_players);
        EditText gameLength = (EditText) view.findViewById(R.id.game_length);
        EditText gameDescription = (EditText) view.findViewById(R.id.game_description);
        EditText gameNotes = (EditText) view.findViewById(R.id.game_notes);

        title.setText(game.getName());
        maxPlayers.setText("Maximum Player: " + game.getMaxPlayers());
        minPlayers.setText("Minimum Player: " + game.getMinPlayers());
        gameLength.setText("Length of Game: "+ game.getPlayTime()+" minutes");
        gameNotes.setText(game.getNotes());
        gameDescription.setText("Not Available");

        Button back = (Button) view.findViewById(R.id.back_butt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
    public void setBoardGame(BoardGame game){
        this.game = game;
    }
}
