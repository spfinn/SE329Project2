package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment used to show a basic description of a given boardgame.
 */
public class DescriptionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        TextView title = (TextView) view.findViewById(R.id.game_title);
        TextView minPlayers = (TextView) view.findViewById(R.id.min_players);
        TextView maxPlayers = (TextView) view.findViewById(R.id.max_players);
        TextView gameLength = (TextView) view.findViewById(R.id.game_length);
        TextView gameDescription = (TextView) view.findViewById(R.id.game_description);

        title.setText(getArguments().getString("title"));
        maxPlayers.setText("Maximum Player: " + getArguments().getInt("max"));
        minPlayers.setText("Minimum Player: " + getArguments().getInt("min"));
        gameLength.setText("Length of Game: "+ getArguments().get("length")+" minutes");
        gameDescription.setText("Not Available");

        return view;
    }
}
