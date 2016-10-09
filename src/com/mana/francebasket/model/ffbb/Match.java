package com.mana.francebasket.model.ffbb;

public class Match {
String formattedDate;
String time;
String hometeam;
String visitorteam;
String score;
String date;
String matchId;

/*
 *pour faire le lien avec le classement 
 */
String groupId="";

String subGroupId = "";
/**/

public String getFormattedDate() {
	return formattedDate;
}
public void setFormattedDate(String formattedDate) {
	this.formattedDate = formattedDate;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getHometeam() {
	return hometeam;
}
public void setHometeam(String hometeam) {
	this.hometeam = hometeam;
}
public String getVisitorteam() {
	return visitorteam;
}
public void setVisitorteam(String visitorteam) {
	this.visitorteam = visitorteam;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getMatchId() {
	return matchId;
}
public void setMatchId(String matchId) {
	this.matchId = matchId;
}

public String getGroupId() {
	return groupId;
}

public void setGroupId(String groupId) {
	this.groupId = groupId;
}

public String getSubGroupId() {
	return subGroupId;
}

public void setSubGroupId(String subGroupId) {
	this.subGroupId = subGroupId;
}
@Override
public String toString() {
	return "Match [formattedDate=" + formattedDate + ", time=" + time
			+ ", hometeam=" + hometeam + ", visitorteam=" + visitorteam
			+ ", score=" + score + ", date=" + date + ", matchId=" + matchId
			+ ", groupId=" + groupId + ", subGroupId=" + subGroupId + "]";
}



}
