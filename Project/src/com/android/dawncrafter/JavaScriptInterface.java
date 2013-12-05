package com.android.dawncrafter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.project.R;

public class JavaScriptInterface {
	Context mContext;
	
	JavaScriptInterface(Context c){
		mContext = c;
	}
	
	@JavascriptInterface
	public void saveBuild(String name, String shaper, String shaperlevel, String abilitylevels, String loadout, String items){
		String url = "www.dawncrafter.com/?";
		if (shaper == "") {
			Toast.makeText(mContext, "ERROR: You must select a shaper to be able to save!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		url += "shaper=" + shaper;
		url += "&shaperlevel=" + shaperlevel;
		url += "&abilitylevels=" + abilitylevels;
		url += "&loadout=" + loadout;
		url += "&items=" + items;
		
		BuildDataSource db = new BuildDataSource(mContext);
		db.createBuild(null, name, url);
		Toast.makeText(mContext, name + " was saved succesfully!", Toast.LENGTH_SHORT).show();
	}
	@JavascriptInterface
	public void playBuySound() {
		Log.d("HI", "Buy sound");
		MediaPlayer mp = MediaPlayer.create(mContext, R.raw.buy); 
		mp.start();
	}
	@JavascriptInterface
	public void playSellSound() {
		Log.d("HI", "Sell sound");
		MediaPlayer mp = MediaPlayer.create(mContext, R.raw.sell); 
		mp.start();
	}
}
