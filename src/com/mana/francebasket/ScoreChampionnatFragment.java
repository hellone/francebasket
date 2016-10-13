package com.mana.francebasket;


import java.util.ArrayList;

import com.mana.francebasket.adapter.ClassementAdapter;
import com.mana.francebasket.model.ffbb.ChampionnatDetail;
import com.mana.francebasket.service.WebService;
import com.mana.francebasket.utilities.MesPreferences;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


@SuppressLint("ValidFragment")
public class ScoreChampionnatFragment extends Fragment {

	private ListView classementList;
    private ClassementAdapter classementAdapter;
    
    private String idChampionnat = "";
    private String idGroup = "";
    private String championnatName = "";
    private String championnatPere = "";
    
    public ScoreChampionnatFragment(String idChampionnat, String idGroup, String championnatName, String championnatPere) {
		super();
		this.idChampionnat = idChampionnat;
		this.idGroup = idGroup;
		this.championnatName = championnatName;
		this.championnatPere = championnatPere;
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
	      Button addChampionnatButton  = (Button) getActivity().findViewById(R.id.addChampionnatButton);
	      addChampionnatButton.setVisibility(View.VISIBLE);
	      addChampionnatButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MesPreferences.addChampionnatToPreference(getActivity(), idChampionnat, idGroup, championnatName, getChampionnatParentNames(ChampionnatsFragment.championnatPere));
				showToast("Ajout du Championnat id = " + idChampionnat + " dans les preferences");
			}
		});

	      
	      classementList.setClickable(false);
	        
		getDatas();

	}  


	private String getChampionnatParentNames(ArrayList<String> names){
		String result = "";
		 
			for (String name : names) {
				result = result + name + "\n";
			}
			
		return result;
	}
	private void getDatas() {
		new AsyncTask<Void, Void, ChampionnatDetail>() {
			
			@Override
			protected ChampionnatDetail doInBackground(Void... params) {
			
				WebService webService = new WebService();
				ChampionnatDetail result = webService.getDetailChampionnat(idChampionnat, idGroup);
				System.out.println("Resultat  :  " + result);

					return result;

			};


			protected void onPostExecute(final ChampionnatDetail result) {
				
				if(result == null)
					return;
		        classementAdapter = new ClassementAdapter(getActivity().getBaseContext(), R.layout.fragment_scores_championnat, result, idChampionnat, idGroup, getActivity());
		        
		        classementList.setAdapter(classementAdapter);
		        

			};

		}.execute();
	}

	private void showToast(String msg){  
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();  
	}  

}