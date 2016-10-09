package com.mana.francebasket;


import com.mana.francebasket.adapter.ClassementAdapter;
import com.mana.francebasket.model.ffbb.ChampionnatDetail;
import com.mana.francebasket.service.WebService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class MesResultatsFragment extends Fragment {

	private ListView mList;
    private ClassementAdapter classementAdapter;
    
    private String idChampionnat = "";

    
    public MesResultatsFragment(String idChampionnat) {
		super();
		this.idChampionnat = idChampionnat;
	}
    
    
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) {  

		return inflater.inflate(R.layout.custom_list_layout, container, false);  
	}  


	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);  


		

	      mList = (ListView) getActivity().findViewById(R.id.custom_list);
	        
		getDatas();

	}  


	private void getDatas() {
		new AsyncTask<Void, Void, ChampionnatDetail>() {
			
			@Override
			protected ChampionnatDetail doInBackground(Void... params) {
			
				WebService webService = new WebService();
				ChampionnatDetail result = webService.getDetailChampionnat(idChampionnat, "");
				System.out.println("Resultat  :  " + result);

					return result;

			};


			protected void onPostExecute(final ChampionnatDetail result) {
		        classementAdapter = new ClassementAdapter(getActivity().getBaseContext(), R.layout.fragment_scores_championnat, result, "", "", getActivity());
		        
		        mList.setAdapter(classementAdapter);
			};

		}.execute();
	}

	
}