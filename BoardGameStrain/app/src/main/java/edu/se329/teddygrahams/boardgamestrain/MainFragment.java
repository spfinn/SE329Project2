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
        Spinner numPlayers = (Spinner) view.findViewById(R.id.numPlayersSpinner);
        Spinner lengthOfGame = (Spinner) view.findViewById(R.id.lengthOfGame);
        Button findGame = (Button) view.findViewById(R.id.submit_button);

        ArrayList<String> shit = new ArrayList<String>();
        shit.add("The Boogey Man");
        shit.add("Duck Duck Grey Duck");
        shit.add("Solitaire");
        shit.add("Cliff Diving");
        numPlayers.setAdapter(new myTextAdapter(shit));
        lengthOfGame.setAdapter(new myTextAdapter(shit));

        findGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transact =  getActivity().getFragmentManager().beginTransaction();
                ResultFragment fragment = new ResultFragment();
                Bundle bundle = new Bundle();

                String[] result = {"Risk","Monopoly","Connect Cuatro"};
                bundle.putStringArray("results",result);

                fragment.setArguments(bundle);
                transact.replace(R.id.content_main, fragment);
                transact.addToBackStack(null);
                transact.commit();

            }
        });


    }


    class myTextAdapter extends BaseAdapter{
        private ArrayList<String> adapterText;
        public  myTextAdapter(ArrayList<String> adapterText){
            this.adapterText = adapterText;
        }

        @Override
        public int getCount() {
            return adapterText.size();
        }

        @Override
        public Object getItem(int i) {
            return adapterText.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView text = new TextView(getActivity());
            text.setTextSize(24);
            text.setText(adapterText.get(i));
            return text;
        }
    }

}
