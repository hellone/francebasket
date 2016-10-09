package com.mana.francebasket;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mana.francebasket.adapter.ClassementAdapter;
import com.mana.francebasket.model.ffbb.ChampionnatDetail;
import com.mana.francebasket.service.WebService;


@SuppressLint("ValidFragment")
public class ScoreChampionnatFragment extends Fragment {

	private ListView classementList;
    private ClassementAdapter classementAdapter;
    
    private String idChampionnat = "";
    private String idGroup = "";

    
    public ScoreChampionnatFragment(String idChampionnat, String idGroup) {
		super();
		this.idChampionnat = idChampionnat;
		this.idGroup = idGroup;
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
	      classementList.setClickable(false);
	        
		getDatas();

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

	
}