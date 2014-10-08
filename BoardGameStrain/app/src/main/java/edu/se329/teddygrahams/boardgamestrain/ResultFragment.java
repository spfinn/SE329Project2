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
    private int min;
    private int max;
    private ArrayList<BoardGame> gameBoard;
    private ResultsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        final ListView list = (ListView) view.findViewById(R.id.result_view);
        gameBoard = new ArrayList<BoardGame>();
        numPlayers = getArguments().getInt("numPlayers");
        min = getArguments().getInt("min");
        max = getArguments().getInt("max");
        Log.i("Number of Player","Players: "+ numPlayers);

        adapter = new ResultsListAdapter();
        list.setAdapter(adapter);

        new XMLPullingUtil(getActivity(), new OnItemParsedListener() {
            @Override
            public void itemParsed(BoardGame game) {
                if(game.isEnd() && gameBoard.size()==0) {
                        gameBoard.add(game);
                        adapter.notifyDataSetChanged();
                        return;
                }
                //Checks to see if a game meets requirements.
                if(numPlayers >= game.getMinPlayers() && numPlayers <= game.getMaxPlayers()
                        && game.getPlayTime() >= min && game.getPlayTime() <= max  ) {
                    gameBoard.add(game);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.i("Game","Doesn't meet Requirements "+game.getName()+" "+game.getMinPlayers()+" "+game.getMaxPlayers()+" "+game.getPlayTime());
                }
            }
        });

        return view;
    }

    /**
     * Hidden adapter to create a results listview adapter.
     */
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
        text.setPadding(0, 16, 0, 16);
        text.setTextSize(24);
        text.setText(gameBoard.get(i).getName());
        if(!gameBoard.get(i).isEnd())
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescriptionFragment desc = new DescriptionFragment();
                Bundle bundle = new Bundle();
                BoardGame game = gameBoard.get(i);
                bundle.putString("title", game.getName());
                bundle.putInt("max", game.getMaxPlayers());
                bundle.putInt("min", game.getMinPlayers());
                bundle.putInt("length", game.getPlayTime());

                desc.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.content_main, desc).addToBackStack(null).commit();
            }
        });
        return text ;
    }
}
}
