package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bkallaus on 9/28/14.
 */
public class ResultFragment extends Fragment {
    private int numPlayers;
    private double gameLength;
    private ArrayList<BoardGame> gameBoard = new ArrayList<BoardGame>();
    private ResultsListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ListView list = (ListView) view.findViewById(R.id.result_view);

        numPlayers = getArguments().getInt("numPlayers");
        gameLength = getArguments().getInt("gameLength");
        Log.i("Number of Player","Players: "+ numPlayers);
        Log.i("Length of Game","Length: "+ gameLength);

        adapter = new ResultsListAdapter();
        list.setAdapter(adapter);

        new XMLPullingUtil(getActivity(), new OnItemParsedListener() {
            @Override
            public void itemParsed(BoardGame game) {
                if(numPlayers >= game.getMinPlayers() &&
                        numPlayers <= game.getMaxPlayers()
                        && gameLength >= game.getPlayTime()
                        ) {
                    gameBoard.add(game);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.i("Game","Doesn't meet Requirements "+game.getName()+" "+game.getMinPlayers()+" "+game.getMaxPlayers()+" "+game.getPlayTime());
                }
            }
        });

        return view;
    }

class ResultsListAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return gameBoard.size();
    }

    @Override
    public Object getItem(int i) {
        return gameBoard.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        TextView text = new TextView(getActivity());
        text.setPadding(32, 16, 0, 16);
        text.setTextSize(24);
        text.setText(gameBoard.get(i).getName());
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),gameBoard.get(i).getName(), Toast.LENGTH_LONG).show();
            }
        });
        return text ;
    }
}
}
