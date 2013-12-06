package com.android.dawncrafter;

//===============================================================================
//Project    : Android: Dawncrafter Theorycrafting Application               	=
//File Name  : Build.java      												    =
//File Type  : Class    														=
//Authors    : Brian Green & Brandon Aikey			    						=
//Date       : 12/5/2013														=
//Description: Getters and setters for build object	                            =
//===============================================================================
public class Build {
	private String id;
	private String buildName;
	private String buildUrl;

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getBuildUrl() {
		return buildUrl;
	}

	public void setBuildUrl(String buildUrl) {
		this.buildUrl = buildUrl;
	}
}
