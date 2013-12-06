package com.android.dawncrafter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.project.R;
//===============================================================================
//Project    : Android: Dawncrafter Theorycrafting Application               	=
//File Name  : NewBuildActivity.java											=
//File Type  : Activity														    =
//Authors    : Brian Green & Brandon Aikey									    =
//Date       : 12/5/2013														=
//Description: This activity displays and handles the build screen	            =
//===============================================================================

public class NewBuildActivity extends Activity implements
		SaveDialog.Communicator {
	
	//Method calls to show the save Dialog fragment
	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	public void saveBuild() {
		showDialog(null);
	}

	//Constructor starts the webiew and enables javascript interface
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String url = getIntent().getStringExtra("url");
		String name = getIntent().getStringExtra("name");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_build);
		WebView wv = (WebView) findViewById(R.id.webview);
		wv.clearCache(true);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.addJavascriptInterface(new JavaScriptInterface(this), "Android");
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setUseWideViewPort(true);

		if (url != null) {
			wv.loadUrl(url);
			setTitle(name);
		} else {
			wv.loadUrl("http://www.dawncrafter.com/");
		}

	}
	
	//Method onPause will be used in future development
	public void onPause() {
		super.onPause();
	}

	//Method onResume will be used in future development
	public void onResume() {
		super.onResume();
	}

	//Method showDialog instantiates SaveDialog fragment and displays it
	public void showDialog(View view) {
		FragmentManager manager = getFragmentManager();
		SaveDialog dialog = new SaveDialog();
		dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		dialog.show(manager, "SaveDialog");

	}

	//Method displays a toast message to let the user know the build was saved
	@Override
	public void onDialogSave(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	
	//Method that sets up the action bar
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	//Method inflates the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_build, menu);
		return true;
	}
	
	//Method handles selection of items in the options menu
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
	
	//Method loads saved builds to the webview
	@Override
	public void onBuildSave(String name) {
		WebView wv = (WebView) findViewById(R.id.webview);
		wv.loadUrl("javascript:(function(){ saveBuild('" + name + "'); }())");
	}
}
