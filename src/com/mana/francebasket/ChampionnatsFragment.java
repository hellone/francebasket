package com.mana.francebasket;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mana.francebasket.model.ffbb.Championnat;
import com.mana.francebasket.model.ffbb.Championnats;
import com.mana.francebasket.model.ffbb.Values;
import com.mana.francebasket.service.WebService;

/** 
 * 
 * @author easinfogeek.com 
 * 
 */ 
public class ChampionnatsFragment extends Fragment {  
	static ArrayList<String> championnatPere = new ArrayList<String>();

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) { 
		return inflater.inflate(R.layout.custom_list_layout, container, false);  
	}  


	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);   

		championnatPere.clear();;
		getDatas();

	}  

	private void getDatas() {
		new AsyncTask<Void, Void, ArrayList<Values>>() {

			@Override
			protected ArrayList<Values> doInBackground(Void... params) {
				WebService webService = new WebService();
				ArrayList<String> championnatNames = new ArrayList<String>();

				Championnats championnats = webService.getTopChampionnats();
				ArrayList<Values> listChampionnat = championnats.getValues();


				for (Values championnat : listChampionnat) {
					championnatNames.add(championnat.getName());
				}

				return listChampionnat;

			};


			protected void onPostExecute(final ArrayList<Values> result) {
				ListView listView = (ListView) getActivity().findViewById(R.id.custom_list);

				ArrayList<String> nameElements = new ArrayList<String>();

				for (Values championnat : result) {
					nameElements.add(championnat.getName());
					System.out.println(championnat.toString());
				}
				listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, nameElements));  

				listView.setOnItemClickListener(new OnItemClickListener() {  

					@Override 
					public void onItemClick(AdapterView<?> parent, View view,  
							int position, long id) {  
						//create a Fragment  
						Fragment detailFragment = new FragmentDetail();  

						Values championnat = result.get(position);
						System.out.println(championnat.toString());
						Bundle mBundle = new Bundle();  
						mBundle.putString("name", championnat.getName());
						mBundle.putString("id", championnat.getId());  
						mBundle.putString("type", championnat.getType());  
						detailFragment.setArguments(mBundle);  

						final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();  
						final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  

						//check if the device is landscape or portrait 
						Configuration configuration = getActivity().getResources().getConfiguration();  
						int ori = configuration.orientation;  

						fragmentTransaction.replace(R.id.frame_container, detailFragment);  

						if(ori == configuration.ORIENTATION_PORTRAIT){  
							fragmentTransaction.addToBackStack(null);  
						}  

						fragmentTransaction.commit();  


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