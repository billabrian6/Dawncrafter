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
	public void saveBuild(String shaper, String shaperlevel, String abilitylevels, String loadout, String items){
		//Save build here
		
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
