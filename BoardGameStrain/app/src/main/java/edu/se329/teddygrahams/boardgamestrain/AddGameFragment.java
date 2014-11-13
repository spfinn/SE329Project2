package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ben on 11/12/2014.
 */
public class AddGameFragment extends Fragment {
        private BoardGame game;
        private View rootView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_description, container, false);

            Button back = (Button) rootView.findViewById(R.id.back_butt);
            back.setText("Cancel");
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });

            Button save = (Button) rootView.findViewById(R.id.save_butt);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveGameInfo();
                    getActivity().onBackPressed();
                }
            });

            return rootView;
        }

        private void saveGameInfo(){
            EditText title = (EditText) rootView.findViewById(R.id.game_title);
            EditText minPlayers = (EditText) rootView.findViewById(R.id.min_players);
            EditText maxPlayers = (EditText) rootView.findViewById(R.id.max_players);
            EditText gameLength = (EditText) rootView.findViewById(R.id.game_length);
            EditText gameDescription = (EditText) rootView.findViewById(R.id.game_description);
            EditText gameNotes = (EditText) rootView.findViewById(R.id.game_notes);

            game = new BoardGame(title.getText().toString());
            game.setMinPlayers(Integer.parseInt(minPlayers.getText().toString()));
            game.setMaxPlayers(Integer.parseInt(maxPlayers.getText().toString()));
            game.setPlayTime(Integer.parseInt(gameLength.getText().toString()));
            game.setNotes(gameNotes.getText().toString());
            game.setDescription(gameDescription.getText().toString());
        }
}