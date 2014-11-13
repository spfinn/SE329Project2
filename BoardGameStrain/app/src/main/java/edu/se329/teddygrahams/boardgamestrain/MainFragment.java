package edu.se329.teddygrahams.boardgamestrain;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * starting point of the program. This hosts the area for querying and would pass on requirements for board games.
 */
public class MainFragment extends Fragment {

    ArrayList<BoardGame> allGamesList = new ArrayList<BoardGame>();

    private ArrayList<Integer> numPlayers = new ArrayList<Integer>();
    private ArrayList<Integer> gameLength = new ArrayList<Integer>();

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
        final EditText numPlayersView = (EditText) view.findViewById(R.id.playersSpinner);
        final EditText min = (EditText) view.findViewById(R.id.minLengthOfGame);
        final EditText max = (EditText) view.findViewById(R.id.maxLengthOfGame);


        ((Button) view.findViewById(R.id.submit_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (( Integer.parseInt(min.getText().toString())) > (Integer.parseInt(max.getText().toString()))) {
                    Toast.makeText(getActivity(), "Game Length Not Possible", Toast.LENGTH_SHORT).show();
                    return;
                }
                FragmentTransaction transact = getActivity().getFragmentManager().beginTransaction();
                ResultFragment fragment = new ResultFragment();
                Bundle bundle = new Bundle();

                bundle.putInt("numPlayers", (Integer.parseInt(numPlayersView.getText().toString())));
                bundle.putInt("min", (Integer.parseInt(min.getText().toString())));
                bundle.putInt("max", (Integer.parseInt(max.getText().toString())));
                fragment.setArguments(bundle);
                transact.replace(R.id.content_main, fragment).addToBackStack(null).commit();
            }
        });

        ((Button) view.findViewById(R.id.show_all_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transact = getActivity().getFragmentManager().beginTransaction();
                ResultFragment fragment = new ResultFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("all", true);
                fragment.setArguments(bundle);

                transact.replace(R.id.content_main, fragment).addToBackStack(null).commit();
            }
        });

        ((Button) view.findViewById(R.id.show_favorites_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transact = getActivity().getFragmentManager().beginTransaction();
                ResultFragment fragment = new ResultFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("favorite",true);
                fragment.setArguments(bundle);

                transact.replace(R.id.content_main, fragment).addToBackStack(null).commit();
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
