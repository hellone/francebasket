package com.mana.francebasket;

import java.util.ArrayList;

import com.mana.francebasket.adapter.ClassementAdapter;
import com.mana.francebasket.adapter.TopChampionnatAdapter;
import com.mana.francebasket.model.ffbb.Championnats;
import com.mana.francebasket.model.ffbb.Values;
import com.mana.francebasket.service.ViewManager;
import com.mana.francebasket.service.WebService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/** 
 * 
 * @author easinfogeek.com 
 * 
 */ 
public class ChampionnatsFragment extends Fragment {  
	static ArrayList<String> championnatPere = new ArrayList<String>();
    private TopChampionnatAdapter topChampionnatAdapter;
	private ListView listView;

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) { 
		return inflater.inflate(R.layout.custom_list_layout, container, false);  
	}  


	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);   
		listView = (ListView) getActivity().findViewById(R.id.custom_list);
		championnatPere.clear();
		getDatas();

	}  

	private void getDatas() {
		new AsyncTask<Void, Void, ArrayList<Values>>() {

			@Override
			protected ArrayList<Values> doInBackground(Void... params) {
				WebService webService = new WebService();

				Championnats championnats = webService.getTopChampionnats();
				ArrayList<Values> listChampionnat = championnats.getValues();

				return listChampionnat;

			};


			protected void onPostExecute(final ArrayList<Values> result) {
			
				topChampionnatAdapter = new TopChampionnatAdapter(getActivity().getBaseContext(), R.layout.fragment_championnats, result);
		        
				listView.setAdapter(topChampionnatAdapter);
				

				listView.setOnItemClickListener(new OnItemClickListener() {  

					@Override 
					public void onItemClick(AdapterView<?> parent, View view,  
							int position, long id) {  
						//create a Fragment  
						
						Values championnat = result.get(position);
						System.out.println(championnat.toString());
						Bundle mBundle = new Bundle();  
						mBundle.putString("name", championnat.getName());
						mBundle.putString("id", championnat.getId());  
						mBundle.putString("type", championnat.getType());  
						
						ViewManager.openFragmentDetaillView(getActivity(), mBundle);
	

					}  
				}); 
			};

		}.execute();
	}


	/** 
	 *  
	 * @param msg 
	 */ 
	private void showTost(String msg){  
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();  
	}  

}  