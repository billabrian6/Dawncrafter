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

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	public void saveBuild() {
		showDialog(null);
	}

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

	public void onPause() {
		super.onPause();
	}

	public void onResume() {
		super.onResume();
	}

	public void showDialog(View view) {
		FragmentManager manager = getFragmentManager();
		SaveDialog dialog = new SaveDialog();
		dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		dialog.show(manager, "SaveDialog");

	}

	@Override
	public void onDialogSave(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

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
		WebView wv = (WebView) findViewById(R.id.webview);
		wv.loadUrl("javascript:(function(){ saveBuild('" + name + "'); }())");
	}
}
