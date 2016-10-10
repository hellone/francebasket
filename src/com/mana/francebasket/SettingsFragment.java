package com.mana.francebasket;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mana.francebasket.adapter.SettingsAdapter;
import com.mana.francebasket.model.ffbb.ChampionnatPreference;
import com.mana.francebasket.model.ffbb.ChampionnatsSettings;
import com.mana.francebasket.service.ViewManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.ListView;


public class SettingsFragment extends Fragment {

	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Name = "nameKey"; 
	SharedPreferences sharedpreferences;
	ChampionnatsSettings settings;

	private ListView list;
	private SettingsAdapter settingsAdapter;

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) {  

		return inflater.inflate(R.layout.custom_list_layout, container, false);  
	}  


	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);  

		list = (ListView) getActivity().findViewById(R.id.custom_list);

		//addSettings();

		getDatas();

	}  


	private void getDatas() {
		new AsyncTask<Void, Void, ChampionnatsSettings>() {

			@Override
			protected ChampionnatsSettings doInBackground(Void... params) {

				//RÃ©cuperation des preference, des settings utilisateurs
				sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

				String value = sharedpreferences.getString("list", null);

				GsonBuilder gsonb = new GsonBuilder();
				Gson gson = gsonb.create();
				settings = gson.fromJson(value, ChampionnatsSettings.class);

				return settings;

			};


			protected void onPostExecute(final ChampionnatsSettings result) {
				
				if(result == null)
					ViewManager.openMessageView(getActivity(), "Vous n'avez pas encore sélectionné de championnat dans vos favoris");	
				else{
				settingsAdapter = new SettingsAdapter(getActivity().getBaseContext(), R.layout.fragment_settings, result);

				list.setAdapter(settingsAdapter);
				
				list.setOnItemClickListener(new OnItemClickListener() {  

					@Override 
					public void onItemClick(AdapterView<?> parent, View view,  
							int position, long id) {  
						
						ChampionnatPreference setting = result.getSettings().get(position);
						
						ViewManager.openScoreChampionnatView(getActivity(), setting.getId(), setting.getGroupId(), setting.getChampionnatName(), ChampionnatsFragment.championnatPere.toString());							
					}
				});
			}	
			};
		}.execute();
	}
 
	
	public void addSettings(){
		//	      String n  = name.getText().toString();

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		ChampionnatsSettings settings = new ChampionnatsSettings();
		String value = gson.toJson(settings);
		SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

		Editor editor = sharedpreferences.edit();

		editor.putString("list", value);

		editor.commit(); 

	}

}