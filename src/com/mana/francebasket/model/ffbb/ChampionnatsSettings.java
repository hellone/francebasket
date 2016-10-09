package com.mana.francebasket.model.ffbb;

import java.util.ArrayList;

public class ChampionnatsSettings {
	
ArrayList<ChampionnatPreference> settings = new ArrayList<ChampionnatPreference>();

public ArrayList<ChampionnatPreference> getSettings() {
	return settings;
}

public void setSettings(ArrayList<ChampionnatPreference> settings) {
	this.settings = settings;
}

public void add(ChampionnatPreference setting) {
	settings.add(setting);
}

}
