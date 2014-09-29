package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Created by bkallaus on 9/28/14.
 */
public class ResultFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ListView list = (ListView) view.findViewById(R.id.result_view);
        list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getArguments().getStringArray("results")));
        Log.e("BackStackers", "BackStack: " + getFragmentManager().getBackStackEntryCount());
        return view;
    }

}
