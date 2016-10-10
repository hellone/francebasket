package com.mana.francebasket;

import java.util.ArrayList;

import com.mana.francebasket.adapter.TopChampionnatAdapter;
import com.mana.francebasket.model.ffbb.ChampionnatDetail;
import com.mana.francebasket.model.ffbb.Championnats;
import com.mana.francebasket.model.ffbb.ChampionnatsSettings;
import com.mana.francebasket.model.ffbb.Values;
import com.mana.francebasket.service.ViewManager;
import com.mana.francebasket.service.WebService;
import com.mana.francebasket.utilities.MesPreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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

/** 
 * 
 * @author easinfogeek.com 
 * 
 */ 
@SuppressLint("ShowToast")
public class FragmentDetail extends Fragment {  
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Name = "nameKey"; 
	SharedPreferences sharedpreferences;
	ChampionnatsSettings settings;
    private TopChampionnatAdapter championnatAdapter;
	private ListView listView;

	String groupIdMemory = "";

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) {   
		return inflater.inflate(R.layout.custom_list_layout, container, false);  
	}  

	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);  
		
		Bundle bundle = this.getArguments();
		String championnatName = bundle.getString("name");
		String id = bundle.getString("id");
		String groupId = bundle.getString("groupId");
		String type = bundle.getString("type");
		String idParents = bundle.getString("idParent");
		String papa = bundle.getString("papa");
		
		listView = (ListView) getActivity().findViewById(R.id.custom_list);
		
		if(ChampionnatsFragment.championnatPere.contains(championnatName)){
			ChampionnatsFragment.championnatPere.remove(ChampionnatsFragment.championnatPere.size() - 1);
		}
		else{
			ChampionnatsFragment.championnatPere.add(bundle.getString("name"));
		}
		

		System.out.println("onActivityCreated  :  " + championnatName + " id : "  + id +" type : "  +type + " groupId " + groupId +  "idParents : "  + idParents + "Championnat pere : " + ChampionnatsFragment.championnatPere);

		String url = "";

		if(groupId != null && !groupIdMemory.equals(groupId)){
			url = "results.php" +"?type=" + "championship" + "&id="+idParents+"&group="+groupId+"&subcompetition=";
			groupIdMemory = groupId;
			getDatas(url, idParents, groupId, championnatName);
			return;
		}
		if(groupId != null && groupIdMemory.equals(groupId)){
			url = "results.php" +"?type=" + "championship" + "&id="+idParents+"&group="+groupId+"&subcompetition=";
			getDatas(url, idParents, groupId, championnatName);
			return;
		}
		if(id != null && papa != null && papa.equals("Departements")){
			url = "areaCompetitions.php?type=championship&id=" + id;
			getChampionnatsDatas(url, id, "Departement");
			return;				
		}
		if(id != null && papa != null && papa.equals("Regions")){
			url = "areaCompetitions.php?type=championship&id=" + id;
			getChampionnatsDatas(url, id, "Region");
			return;				
		}		
		if( id == null && championnatName.equals("CHAMPIONNATS REGIONAUX"))
			{
			url = "leagues.php?type=championship";
			getChampionnatsDatas(url, id, "Regions");
			return;
			}
		
		else if( id == null && championnatName.equals("CHAMPIONNATS DEPARTEMENTAUX"))
			{
			url = "areas.php?type=championship";
			getChampionnatsDatas(url, id, "Departements");
			return;
			}
		
		else if(idParents!=null)
			{
			url = "results.php" +"?type=" + "championship" + "&id="+idParents+"&group="+id+"&subcompetition=";
			getDatas(url, id, groupId, championnatName);
			return;
			}
		else
			{
			// 
			url = "results.php" +"?type=" + "championship" + "&id="+id+"&group=&subcompetition=";
			getDatas(url, id, groupId, championnatName);
			return;
			}

	}  



	private void getChampionnatsDatas(final String url, final String id, final String papa) {
		new AsyncTask<Void, Void, ArrayList<Values>>() {

			@Override
			protected ArrayList<Values> doInBackground(Void... params) {
				WebService webService = new WebService();
				
				Championnats championnats = webService.getChampionnats(url);
				ArrayList<Values> listChampionnat = championnats.getValues();

				return listChampionnat;

			};


			protected void onPostExecute(final ArrayList<Values> result) {
				championnatAdapter = new TopChampionnatAdapter(getActivity().getBaseContext(), R.layout.fragment_championnats, result);
		        
				listView.setAdapter(championnatAdapter);			
		           
		        listView.setOnItemClickListener(new OnItemClickListener() {  
		   
		            @Override 
		            public void onItemClick(AdapterView<?> parent, View view,  
		                    int position, long id) {  
		                Values championnat = result.get(position);  
		                System.out.println(championnat.toString());
		                System.out.println( "OnItemClick Bundle put : name : "  +  championnat.getName() + "  ID   :  " +  championnat.getId() + "    TYPE  :  " + championnat.getType() + "   PAPA  :  " + papa );
		                
		                Bundle mBundle = new Bundle();  
		                mBundle.putString("name", championnat.getName());
		                mBundle.putString("id", championnat.getId());  
		                mBundle.putString("type", championnat.getType());
		                mBundle.putString("papa", papa);
		                
						ViewManager.openFragmentDetaillView(getActivity(), mBundle);;	
		                   
		            }  
		        }); 
			};

		}.execute();
	}

	private void getDatas(final String url, final String id, final String groupId, final String championnatName) {
		new AsyncTask<Void, Void, ChampionnatDetail>() {
			String idParent = "";
			@Override
			protected ChampionnatDetail doInBackground(Void... params) {
				WebService webService = new WebService();
				//					ArrayList<Values> values = new ArrayList<Values>();


				ChampionnatDetail result = webService.getChampionnatDetail(url);
				System.out.println("Resultat  :  " + result);

					return result;
			};


			protected void onPostExecute(final ChampionnatDetail result) {
				
				if(groupIdMemory.equals(groupId)){
					
//					System.out.println(" Sauvegarde championnat : " + championnatName + "  pere  : " + ChampionnatsFragment.championnatPere);
//					MesPreferences.addChampionnatToPreference(getActivity(), id, groupId, championnatName, ChampionnatsFragment.championnatPere.toString());
//					showToast("Ajout du Championnat id = " + id + " dans les preferences");
//					
					ViewManager.openScoreChampionnatView(getActivity(), id, groupId, championnatName, ChampionnatsFragment.championnatPere.toString());	
				}
				//Si plus d'un Groupe, j'affiche la liste des groupes, sinon j'affiche les r�sulats
				else if(result.getGroups().getValues().size() > 1 )
				{
				idParent = id;
				
				championnatAdapter = new TopChampionnatAdapter(getActivity().getBaseContext(), R.layout.fragment_championnats, result.getGroups().getValues());
		        
				listView.setAdapter(championnatAdapter);	
				
				listView.setOnItemClickListener(new OnItemClickListener() {  

					@Override 
					public void onItemClick(AdapterView<?> parent, View view,  
							int position, long id) {  
						//create a Fragment  
						Values value = result.getGroups().getValues().get(position);
						System.out.println(value.toString());
						
						Bundle mBundle = new Bundle();  
						mBundle.putString("name", value.getName());
						mBundle.putString("groupId", value.getId());  
						mBundle.putString("type", value.getType());
						mBundle.putString("idParent", idParent); 
						
						System.out.println( "Groupid : " + value.getId() + "idPArent : " + idParent + "GroupFIELD : " + value.getGroupField() );
						
						ViewManager.openFragmentDetaillView(getActivity(), mBundle);;	
	
					}

				}); 
			}
				else{
//					MaDialogBox dialog = new MaDialogBox();
//					dialog.show(getActivity().getSupportFragmentManager(), "1");
//					System.out.println(" Sauvegarde championnat : " + championnatName + "  pere  : " + ChampionnatsFragment.championnatPere);
//					MesPreferences.addChampionnatToPreference(getActivity(), id, "", championnatName, ChampionnatsFragment.championnatPere.toString());
//					showToast("Ajout du Championnat id = " + id + " dans les preferences");
					ViewManager.openScoreChampionnatView(getActivity(), id, "", championnatName, ChampionnatsFragment.championnatPere.toString());
				}
		}

		}.execute();
	}
	
	/** 
	 *  
	 * @param msg 
	 */ 
	private void showToast(String msg){  
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();  
	}  


}  
