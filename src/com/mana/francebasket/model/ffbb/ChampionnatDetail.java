package com.mana.francebasket.model.ffbb;

public class ChampionnatDetail {

	SubCompetitions subCompetitions;
	
	Groupes groups;
	
	Days days;

	int currentDay;
	
	Matchs matchs;
	
	Standings standings;
	
	public SubCompetitions getSubCompetitions() {
		return subCompetitions;
	}

	public void setSubCompetitions(SubCompetitions subCompetitions) {
		this.subCompetitions = subCompetitions;
	}

	public Groupes getGroups() {
		return groups;
	}

	public void setGroups(Groupes groups) {
		this.groups = groups;
	}

	public Days getDays() {
		return days;
	}

	public void setDays(Days days) {
		this.days = days;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public Matchs getMatchs() {
		return matchs;
	}

	public void setMatchs(Matchs matchs) {
		this.matchs = matchs;
	}

	public Standings getStandings() {
		return standings;
	}

	public void setStandings(Standings standings) {
		this.standings = standings;
	}

	@Override
	public String toString() {
		return "ChampionnatDetail [subCompetitions=" + subCompetitions
				+ ", groups=" + groups + ", days=" + days + ", currentDay="
				+ currentDay + ", matchs=" + matchs + ", standings="
				+ standings + "]";
	}
	
	
}
