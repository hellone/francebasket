package com.mana.francebasket.model.ffbb;

import java.util.ArrayList;

public class PreferenceTeam {
	
	public ArrayList<String> teamId = new ArrayList<String>();

	public ArrayList<String> getTeamId() {
		return teamId;
	}

	public void setTeamId(ArrayList<String> teamId) {
		this.teamId = teamId;
	}
	
	public void addTeam(String id) {
		teamId.add(id);
	}
	
	public void removeTeam(String id) {
		teamId.remove(id);
	}

}
