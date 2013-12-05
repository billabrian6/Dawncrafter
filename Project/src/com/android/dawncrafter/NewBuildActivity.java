package com.android.dawncrafter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.project.R;


public class NewBuildActivity extends Activity implements SaveDialog.Communicator {

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	public void saveBuild() {
		showDialog(null);
	}
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_build);
		WebView wv = (WebView) findViewById(R.id.webview);
		wv.clearCache(true);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.addJavascriptInterface(new JavaScriptInterface(this),"Android");
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setUseWideViewPort(true);
		//wv.loadUrl("http://www.dawncrafter.com/");
		
		wv.loadUrl("http://www.dawncrafter.com/");
		
		//JavaScriptInterface js = new JavaScriptInterface(this);
		//js.saveBuild("FUCK");
		// Show the Up button in the action bar.
		// setupActionBar();
	}
	public void onPause(){
		super.onPause();
		//This code should temporarily save all data to resume the last session
	}
	public void onResume(){
		super.onResume();
		//This code should reinitialize the app with all the temporary stored data
	}
	public void showDialog(View view){
		FragmentManager manager = getFragmentManager();
		SaveDialog dialog = new SaveDialog();
		dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		dialog.show(manager, "SaveDialog");
		
	}
	@Override
	public void onDialogSave(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_build, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_save:
			saveBuild();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}
	@Override
	public void onBuildSave(String name) {
		Log.d("Test", name);	    
		
		WebView wv = (WebView) findViewById(R.id.webview);
		wv.loadUrl("javascript:(function(){ saveBuild('"+name+"'); }())");
	}
}
