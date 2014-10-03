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
 * Created by bkallaus on 9/28/14.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null, false);

        setAdapters(view);

        return view;
    }

    public void setAdapters(View view){
        final Spinner minPlayers = (Spinner) view.findViewById(R.id.minPlayersSpinner);
        final Spinner maxPlayers = (Spinner) view.findViewById(R.id.maxPlayersSpinner);
        Spinner lengthOfGame = (Spinner) view.findViewById(R.id.lengthOfGame);
        Button findGame = (Button) view.findViewById(R.id.submit_button);

        ArrayList<Integer> numPlayers = new ArrayList<Integer>();
        for (int i = 0; i < 11; i++)
            numPlayers.add(i);

        ArrayList<Double> gameLength = new ArrayList<Double>();
        for (double i = 0.0; i < 11; i+=0.5)
            gameLength.add(i);

        minPlayers.setAdapter(new myIntegerAdapter(numPlayers));
        maxPlayers.setAdapter(new myIntegerAdapter(numPlayers));
        lengthOfGame.setAdapter(new myDoubleAdapter(gameLength));

        findGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((Integer)minPlayers.getSelectedItem())>((Integer)maxPlayers.getSelectedItem())) {
                    Toast.makeText(getActivity(),"min greater than max players",Toast.LENGTH_SHORT).show();
                    return;
                }
                FragmentTransaction transact =  getActivity().getFragmentManager().beginTransaction();
                ResultFragment fragment = new ResultFragment();
                Bundle bundle = new Bundle();

                String[] result = {"Risk","Monopoly","Connect Four"};
                bundle.putStringArray("results",result);

                fragment.setArguments(bundle);
                transact.replace(R.id.content_main, fragment);
                transact.addToBackStack(null);
                transact.commit();

            }
        });
    }

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

    class myDoubleAdapter extends BaseAdapter{
        private ArrayList<Double> adapterDouble;
        public myDoubleAdapter(ArrayList<Double> adapterText){
            this.adapterDouble = adapterText;
        }

        @Override
        public int getCount() {
            return adapterDouble.size();
        }

        @Override
        public Object getItem(int i) {
            return adapterDouble.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView text = new TextView(getActivity());
            text.setTextSize(24);
            text.setText(adapterDouble.get(i)+"");
            return text;
        }
    }

}
