package com.mana.francebasket;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mana.francebasket.adapter.FavoriAdapter;
import com.mana.francebasket.model.ffbb.Match;
import com.mana.francebasket.model.ffbb.MatchSetting;
import com.mana.francebasket.service.WebService;
import com.mana.francebasket.utilities.MesPreferences;


@SuppressLint("ValidFragment")
public class FavoriFragment extends Fragment {

	private ListView classementList;
	private FavoriAdapter favoriAdapter;




	public FavoriFragment() {
		super();
	}


	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) {  

		return inflater.inflate(R.layout.custom_list_layout, container, false);  
	}  


	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);  

		classementList = (ListView) getActivity().findViewById(R.id.custom_list);

		getDatas();

	}  


	private void getDatas() {
		new AsyncTask<Void, Void, ArrayList<Match>>() {

			@Override
			protected ArrayList<Match> doInBackground(Void... params) {

				ArrayList<MatchSetting> matchSettings = MesPreferences.getMatchsFromPreference(getActivity());

				WebService webService = new WebService();
				ArrayList<Match> result = webService.getMesMatchsFavori(matchSettings);
				System.out.println("Resultat  :  " + result.toString());

				return result;

			};


			protected void onPostExecute(final ArrayList<Match> result) {

				if(result == null)
					return;
				favoriAdapter = new FavoriAdapter(getActivity().getBaseContext(), R.layout.fragment_scores_championnat, result);

				classementList.setAdapter(favoriAdapter);


			};

		}.execute();
	}



}