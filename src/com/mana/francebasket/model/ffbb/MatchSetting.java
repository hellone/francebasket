package com.mana.francebasket.model.ffbb;

public class MatchSetting {

	String id;
	String groupId;
	String teamName;



	public MatchSetting(String id, String groupId, String teamName) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.teamName = teamName;
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
