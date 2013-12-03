package com.android.dawncrafter;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
	Context mContext;
	
	JavaScriptInterface(Context c){
		mContext = c;
	}
	
	@JavascriptInterface
	public void saveBuild(String toast){
		//Save build here
		Log.d("Test", "Test");
		Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	}
}
