package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by bkallaus on 9/28/14.
 */
public class ResultFragment extends Fragment {
    private int numPlayers;
    private int min;
    private int max;
    private boolean showAll;
    private boolean showFavorites;
    private int sort = 2;
    private ArrayList<BoardGame> gameBoardList;
    private ResultsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        final ListView list = (ListView) view.findViewById(R.id.result_view);

        setHasOptionsMenu(true);

        gameBoardList = new ArrayList<BoardGame>();
        fillArguments();

        adapter = new ResultsListAdapter();
        list.setAdapter(adapter);

        pullXMLData();

        return view;
    }

    private void fillArguments(){

        numPlayers = getArguments().getInt("numPlayers");
        min = getArguments().getInt("min",0);
        max = getArguments().getInt("max",1);
        showAll = getArguments().getBoolean("all",false);
        showFavorites = getArguments().getBoolean("favorite",false);
    }


    private void pullXMLData(){
        new XMLPullingUtil(getActivity(), new OnItemParsedListener() {
            @Override
            public void itemParsed(BoardGame game) {
                if(game.isEnd() && gameBoardList.size()==0) {
                    gameBoardList.add(game);
                    adapter.notifyDataSetChanged();
                    return;
                }
                //Checks to see if a game meets requirements.
                if(showAll){
                    if(!game.isEnd())
                        gameBoardList.add(game);

                }else if(numPlayers >= game.getMinPlayers() && numPlayers <= game.getMaxPlayers()
                        && game.getPlayTime() >= min && game.getPlayTime() <= max  ) {
                    gameBoardList.add(game);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.results, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sort) {
           sort =  sort == 1 ? 2 : 1;
            sortList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortList(){
        if(sort == 1){
            Collections.sort(gameBoardList, new Comparator<BoardGame>() {
                @Override
                public int compare(BoardGame game1, BoardGame game2) {
                    return game1.getName().compareTo(game2.getName());
                }
            });
        } else  if(sort == 2){
            Collections.sort(gameBoardList, new Comparator<BoardGame>() {
                @Override
                public int compare(BoardGame game1, BoardGame game2) {
                    return game2.getName().compareTo(game1.getName());
                }
            });
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Hidden adapter to create a results listview adapter.
     */
class ResultsListAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return gameBoardList.size();
    }

    @Override
    public Object getItem(int i) {
        return gameBoardList.get(i);
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
        text.setText(gameBoardList.get(i).getName());
        if(!gameBoardList.get(i).isEnd())
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescriptionFragment desc = new DescriptionFragment();
                desc.setBoardGame(gameBoardList.get(i));
                getFragmentManager().beginTransaction().replace(R.id.content_main, desc).addToBackStack(null).commit();
            }
        });
        return text ;
    }
}
}
