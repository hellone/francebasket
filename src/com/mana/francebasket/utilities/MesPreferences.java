package com.mana.francebasket.utilities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mana.francebasket.model.ffbb.ChampionnatPreference;
import com.mana.francebasket.model.ffbb.ChampionnatsSettings;
import com.mana.francebasket.model.ffbb.MatchSetting;
import com.mana.francebasket.model.ffbb.MatchsSettings;

@SuppressLint("NewApi")
public class MesPreferences {
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Name = "nameKey"; 
	static SharedPreferences sharedpreferences;
	static ChampionnatsSettings settings;

	static MatchsSettings matchs; 


	public ArrayList<ChampionnatPreference> getChampionnatsFromPreference(Activity activity) {
		// TODO Auto-generated method stub
		sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

		String value = sharedpreferences.getString("list", null);

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		settings = gson.fromJson(value, ChampionnatsSettings.class);

		if(settings == null)
			return null;

		return settings.getSettings();	
	}

	public static void addChampionnatToPreference(Activity activity, String id, String groupId, String championnatName, String championnatLibelle){ 

		sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

		String value = sharedpreferences.getString("list", null);

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		settings = gson.fromJson(value, ChampionnatsSettings.class);

		if(settings == null)
			settings = new ChampionnatsSettings();

		settings.add(new ChampionnatPreference(id, groupId, championnatName, championnatLibelle));


		value = gson.toJson(settings);
		Editor editor = sharedpreferences.edit();

		editor.putString("list", value);
		//	      editor.putString(Name, n);

		editor.commit(); 
	} 

	public static void addMatchToPreference(Activity activity, String id, String groupId, String teamName){ 

		sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

		String value = sharedpreferences.getString("matchlist", null);

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		matchs = gson.fromJson(value, MatchsSettings.class);

		if(matchs == null)
			matchs = new MatchsSettings();

		matchs.add(new MatchSetting(id, groupId, teamName));


		value = gson.toJson(matchs);
		Editor editor = sharedpreferences.edit();

		editor.putString("matchlist", value);

		editor.commit(); 
	} 

	static public ArrayList<MatchSetting> getMatchsFromPreference(Activity activity) {
		// TODO Auto-generated method stub
		sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

		String value = sharedpreferences.getString("matchlist", null);

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		matchs = gson.fromJson(value, MatchsSettings.class);

		if(matchs == null)
			return null;

		return matchs.getMatchs();	
	}

}
