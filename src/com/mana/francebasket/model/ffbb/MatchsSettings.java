package com.mana.francebasket.model.ffbb;

import java.util.ArrayList;

public class MatchsSettings {
	
ArrayList<MatchSetting> matchs = new ArrayList<MatchSetting>();


public ArrayList<MatchSetting> getMatchs() {
	return matchs;
}


public void setMatchs(ArrayList<MatchSetting> matchs) {
	this.matchs = matchs;
}


public void add(MatchSetting match) {
	matchs.add(match);
}

}
