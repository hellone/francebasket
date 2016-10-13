package com.mana.francebasket.service;

import com.mana.francebasket.FavoriFragment;
import com.mana.francebasket.FragmentDetail;
import com.mana.francebasket.MessageFragment;
import com.mana.francebasket.R;
import com.mana.francebasket.ScoreChampionnatFragment;
import com.mana.francebasket.model.ffbb.Values;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ViewManager {

	
	public static void openScoreChampionnatView(Activity activity, final String id, final String groupId, String championnatName, String championnatPere) {
		Fragment scoreChampionnatFragment = new ScoreChampionnatFragment(id, groupId, championnatName, championnatPere);  
		FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();  
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  

		//check if the device is landscape or portrait 
		Configuration configuration = activity.getResources().getConfiguration();  
		int ori = configuration.orientation;  

		fragmentTransaction.replace(R.id.frame_container, scoreChampionnatFragment);  

		if(ori == configuration.ORIENTATION_PORTRAIT){  
			fragmentTransaction.addToBackStack(null);  
		}  

		fragmentTransaction.commit();
	}
	
	public static void openFragmentDetaillView(Activity activity, Bundle mBundle) {
		Fragment detailFragment = new FragmentDetail();  
		detailFragment.setArguments(mBundle);  
		FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();    
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  

		//check if the device is landscape or portrait 
		Configuration configuration = activity.getResources().getConfiguration();  
		int ori = configuration.orientation;  

		fragmentTransaction.replace(R.id.frame_container, detailFragment);  

		if(ori == configuration.ORIENTATION_PORTRAIT){  
			fragmentTransaction.addToBackStack(null);  
		}  

		fragmentTransaction.commit();
	}

	public static void openMessageView(FragmentActivity activity, String message) {
		
		Fragment MessageFragment = new MessageFragment(message);  
		FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();  
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  

		//check if the device is landscape or portrait 
		Configuration configuration = activity.getResources().getConfiguration();  
		int ori = configuration.orientation;  

		fragmentTransaction.replace(R.id.frame_container, MessageFragment);  

		if(ori == configuration.ORIENTATION_PORTRAIT){  
			fragmentTransaction.addToBackStack(null);  
		}  

		fragmentTransaction.commit();
		
		
	}  
	
	public static void openFavorisView(FragmentActivity activity) {
		
		Fragment Favori = new FavoriFragment();  
		FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();  
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  

		//check if the device is landscape or portrait 
		Configuration configuration = activity.getResources().getConfiguration();  
		int ori = configuration.orientation;  

		fragmentTransaction.replace(R.id.frame_container, Favori);  

		if(ori == configuration.ORIENTATION_PORTRAIT){  
			fragmentTransaction.addToBackStack(null);  
		}  

		fragmentTransaction.commit();
		
		
	}  

	
}
