package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * starting point of the program. This hosts the area for querying and would pass on requirements for board games.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null, false);

        setAdapters(view);

        return view;
    }

    /**
     * Generic Method used to set up private adapters that are hidden.
     * @param view
     */
    public void setAdapters(View view){
        final Spinner numPlayersView = (Spinner) view.findViewById(R.id.playersSpinner);
        final Spinner min = (Spinner) view.findViewById(R.id.minLengthOfGame);
        final Spinner max = (Spinner) view.findViewById(R.id.maxLengthOfGame);
        Button findGame = (Button) view.findViewById(R.id.submit_button);

        final ArrayList<Integer> numPlayers = new ArrayList<Integer>();

        for (int i = 1; i < 11; i++)
            numPlayers.add(i);

        ArrayList<Integer> gameLength = new ArrayList<Integer>();
        for (int i = 0; i <= 360; i = i + 30)
            gameLength.add(i);

        numPlayersView.setAdapter(new myIntegerAdapter(numPlayers));
        min.setAdapter(new myIntegerAdapter(gameLength));
        max.setAdapter(new myIntegerAdapter(gameLength));

        findGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((Integer) min.getSelectedItem())> ((Integer) max.getSelectedItem()))
                {
                    Toast.makeText(getActivity(),"Game Length Not Possible",Toast.LENGTH_SHORT).show();
                    return;
                }
                FragmentTransaction transact =  getActivity().getFragmentManager().beginTransaction();
                ResultFragment fragment = new ResultFragment();
                Bundle bundle = new Bundle();

                bundle.putInt("numPlayers", ((Integer) numPlayersView.getSelectedItem()));
                bundle.putInt("min", ((Integer) min.getSelectedItem()));
                bundle.putInt("max", ((Integer) max.getSelectedItem()));

                fragment.setArguments(bundle);
                transact.replace(R.id.content_main, fragment);
                transact.addToBackStack(null);
                transact.commit();

            }
        });
    }

    /**
     * Simple Adapter to create text views to be set in the spinner view.
     */
    class myIntegerAdapter extends BaseAdapter{
        private ArrayList<Integer> adapterInteger;
        public myIntegerAdapter(ArrayList<Integer> adapterText){
            this.adapterInteger = adapterText;
        }

        @Override
        public int getCount() {
            return adapterInteger.size();
        }

        @Override
        public Object getItem(int i) {
            return adapterInteger.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView text = new TextView(getActivity());
            text.setTextSize(24);
            text.setText(adapterInteger.get(i)+"");
            return text;
        }
    }


}
