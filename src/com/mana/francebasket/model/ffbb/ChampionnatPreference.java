package com.mana.francebasket.model.ffbb;

public class ChampionnatPreference {
	
String id = "";
String groupId = "";
String championnatName = "";
String championnatLibelle = "";



public ChampionnatPreference(String id, String groupId, String championnatName, String championnatLibelle) {
	super();
	this.id = id;
	this.groupId = groupId;
	this.championnatName = championnatName;
	this.championnatLibelle = championnatLibelle;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getGroupId() {
	return groupId;
}
public void setGroupId(String groupId) {
	this.groupId = groupId;
}
public String getChampionnatName() {
	return championnatName;
}
public void setChampionnatName(String championnatName) {
	this.championnatName = championnatName;
}

public String getChampionnatLibelle() {
	return championnatLibelle;
}

public void setChampionnatLibelle(String championnatLibelle) {
	this.championnatLibelle = championnatLibelle;
}

}
