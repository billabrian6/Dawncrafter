package com.android.dawncrafter;

public class Build {
	private String id;
	private String buildName;
	private String buildUrl;
	
	public String getID(){
		return id;
	}
	public void setID(String id){
		this.id = id;
	}
	public String getBuildName(){
		return buildName;
	}
	public void setBuildName(String buildName){
		this.buildName = buildName;
	}
	public String getBuildUrl(){
		return buildUrl;
	}
	public void setBuildUrl(String buildUrl){
		this.buildUrl = buildUrl;
	}
}
