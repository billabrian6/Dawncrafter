package com.android.dawncrafter;

import android.content.Context;
import android.media.MediaPlayer;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.project.R;
//===============================================================================
//Project    : Android: Dawncrafter Theorycrafting Application               	=
//File Name  : JavaScriptInterface.java											=
//File Type  : Class 															=
//Authors    : Brian Green & Brandon Aikey										=
//Date       : 12/5/2013														=
//Description: Responsible for all communication with website					=
//===============================================================================

public class JavaScriptInterface {
	Context mContext;

	JavaScriptInterface(Context c) {
		mContext = c;
	}

	//Method to save the build displayed on the screen
	@JavascriptInterface
	public void saveBuild(String name, String shaper, String shaperlevel,
			String abilitylevels, String loadout, String items) {
		String url = "http://www.dawncrafter.com/?";
		if (shaper.equals("")) {
			Toast.makeText(mContext,
					"ERROR: You must select a shaper to be able to save!",
					Toast.LENGTH_LONG).show();
			return;
		}

		url += "shaper=" + shaper;
		url += "&shaperlevel=" + shaperlevel;
		url += "&abilitylevels=" + abilitylevels;
		url += "&loadout=" + loadout;
		url += "&items=" + items;

		BuildDataSource db = new BuildDataSource(mContext);
		db.createBuild(name, url);
		Toast.makeText(mContext, name + " was saved succesfully!",
				Toast.LENGTH_SHORT).show();
	}
	
	//Method plays a sound when buying an item
	@JavascriptInterface
	public void playBuySound() {
		MediaPlayer mp = MediaPlayer.create(mContext, R.raw.buy);
		mp.start();
	}

	//Method plays a sound when selling an item
	@JavascriptInterface
	public void playSellSound() {
		MediaPlayer mp = MediaPlayer.create(mContext, R.raw.sell);
		mp.start();
	}
}
