package com.mana.francebasket.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.mana.francebasket.model.ffbb.ChampionnatDetail;
import com.mana.francebasket.model.ffbb.Championnats;
import com.mana.francebasket.model.ffbb.Consts;
import com.mana.francebasket.model.ffbb.Match;
import com.mana.francebasket.model.ffbb.MatchSetting;
import com.mana.francebasket.model.ffbb.Responses;

import android.util.Log;

public class WebService {


	private String URL = "http://mobiles.ffbb.com/php/v1_0_3/";	

	Gson gson;

	public WebService() {
		gson = new Gson();
	}

	private InputStream sendRequest(URL url) throws Exception {

		try {
			// Ouverture de la connexion
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			// Connexion � l'url
			urlConnection.connect();

			// Si le serveur nous r�pond avec un code OK
			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return urlConnection.getInputStream();
			}
		} catch (Exception e) {
			////todo : Pb de connection
			throw new Exception(e.getCause().toString());
		}
		return null;

	}


	public ArrayList<Match> getMatchsMoissy() {
		ArrayList<Match> moissyMatchs = new ArrayList<Match>();

		moissyMatchs.add(searchMatchByTeamName("moissy", Consts.promoExcelRegionID, Consts.regionGroupePouleA));	
		moissyMatchs.add(searchMatchByTeamName("moissy", Consts.promoExcelDepartID,Consts.promoHonorGroupeCentre));
		moissyMatchs.add(searchMatchByTeamName("moissy", Consts.honorDepartID,Consts.honorGroupeSud));
		moissyMatchs.add(searchMatchByTeamName("moissy", Consts.honorDepartID,Consts.honorGroupeCentre));

		return moissyMatchs;

	}

	private Match searchMatchByTeamName(String teamName, String groupId, String pouleId) {

		ArrayList<Match> matchs = getDetailChampionnat(groupId,pouleId).getMatchs().getValues();

		for (Match match : matchs) {
			if(match.getHometeam().toLowerCase().contains(teamName.toLowerCase())||match.getVisitorteam().toLowerCase().contains(teamName.toLowerCase()))
			{
				match.setGroupId(groupId);
				match.setSubGroupId(pouleId);

				return match;
			}
		}
		return null;
	}


	public ChampionnatDetail getDetailChampionnat(String groupId, String subGroupId) {
		try {
			// Envoie de la requète
			URL = URL + "results.php?type=championship" + "&group=" + subGroupId +"&id=" + groupId;

			System.out.println(" URL  :  "  + URL);
			InputStream inputStream = sendRequest(new URL(URL));

			// V�rification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);

				// Return la liste d�s�rialis� par le moteur gson 
				return gson.fromJson(reader, ChampionnatDetail.class);
			}

		} catch (Exception e) {
			Log.e("WebService", "Impossible de rapatrier les données :(");
		}
		return null;
	}

	public ArrayList<Match> getMesMatchs() {
		ArrayList<Match> mesMatchs = new ArrayList<Match>();

		mesMatchs.add(searchMatchByTeamName("moissy", Consts.promoExcelRegionID, Consts.regionGroupePouleA));	
		mesMatchs.add(searchMatchByTeamName("moissy", Consts.promoExcelDepartID,Consts.promoHonorGroupeCentre));
		mesMatchs.add(searchMatchByTeamName("moissy", Consts.honorDepartID,Consts.honorGroupeSud));
		mesMatchs.add(searchMatchByTeamName("moissy", Consts.honorDepartID,Consts.honorGroupeCentre));

		return mesMatchs;
	}

	public ArrayList<Match> getMesMatchsFavori(ArrayList<MatchSetting> matchSettings) {
		ArrayList<Match> mesMatchs = new ArrayList<Match>();

		if (matchSettings == null)
			return mesMatchs;
				
		for (MatchSetting matchSetting : matchSettings) {
			mesMatchs.add(searchMatchByTeamName(matchSetting.getTeamName(), matchSetting.getId(), matchSetting.getGroupId()));	
		}

		return mesMatchs;
	}

	
	public Championnats getTopChampionnats() {
		try {
			// Envoie de la requete
			URL = URL + "topChampionships.php" +"?type=championship";

			System.out.println(" URL  :  "  + URL);

			InputStream inputStream = sendRequest(new URL(URL));

			// V�rification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);

				// Return la liste d�s�rialis� par le moteur gson 
				return gson.fromJson(reader, Championnats.class);
			}

		} catch (Exception e) {
			Log.e("WebService", "Impossible de rapatrier les données getChampionnats :(");
		}
		return null;
	}

	public Championnats getChampionnats(String url) {
		try {
			// Envoie de la requète
			URL = URL + url;
			InputStream inputStream = sendRequest(new URL(URL));

			// V�rification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);

				// Return la liste d�s�rialis� par le moteur gson 
				return gson.fromJson(reader, Championnats.class);
			}

		} catch (Exception e) {
			Log.e("WebService", "Impossible de rapatrier les données getChampionnats :(");
		}
		return null;
	}	

	public Responses getValues(String urlParameters) {
		try {
			// Envoie de la requète
			URL = URL + urlParameters;
			System.out.println("URL : " + URL);
			InputStream inputStream = sendRequest(new URL(URL));

			// V�rification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);

				// Return la liste d�s�rialis� par le moteur gson 
				return gson.fromJson(reader, Responses.class);
			}

		} catch (Exception e) {
			Log.e("WebService", "Impossible de rapatrier les données getChampionnats :(");
		}
		return null;
	}

	public ChampionnatDetail getChampionnatDetail(String urlParameters) {
		try {
			// Envoie de la requète
			URL = URL + urlParameters;
			System.out.println("URL : " + URL);
			InputStream inputStream = sendRequest(new URL(URL));

			// V�rification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);

				// Return la liste d�s�rialis� par le moteur gson 
				return gson.fromJson(reader, ChampionnatDetail.class);
			}

		} catch (Exception e) {
			Log.e("WebService", "Impossible de rapatrier les données getChampionnats :(");
		}
		return null;
	}

}
