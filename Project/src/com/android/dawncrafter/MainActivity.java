package com.android.dawncrafter;

import java.util.ArrayList;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.example.project.R;
//===============================================================================
//	Project    : Android: Dawncrafter Theorycrafting Application               	=
//  File Name  : MainActivity.java												=
//  File Type  : Activity														=
//  Authors    : Brian Green & Brandon Aikey									=
//  Date       : 12/5/2013														=
//  Description: The main activity that starts application at the splash screen	=
//===============================================================================

public class MainActivity extends Activity implements EditDialog.Communicator {
	private ArrayList<Build> builds;

	public void newBuild(View view) {
		Intent intent = new Intent(this, NewBuildActivity.class);
		startActivity(intent);
	}

	public void editDialog(String name, String url) {
		FragmentManager manager = getFragmentManager();
		EditDialog dialog = new EditDialog();
		Bundle ofsticks = new Bundle();
		ofsticks.putString("name", name);
		ofsticks.putString("url", url);
		dialog.setArguments(ofsticks);
		dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		dialog.show(manager, "EditDialog");
	}

	public void loadBuild(String name, String url) {
		Intent intent = new Intent(this, NewBuildActivity.class);
		intent.putExtra("name", name);
		intent.putExtra("url", url);
		startActivity(intent);
	}

	public void onDialogSelect(String option, String name, String url) {
		if (option.equals("open")) {
			loadBuild(name, url);
		} else {
			BuildDataSource db = new BuildDataSource(getApplicationContext());
			db.deleteBuild(name);
		}
	}

	ImageButton button;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (ImageButton) findViewById(R.id.imageButton2);
		registerForContextMenu(button);
	}

	public void showSaves(View v) {
		openContextMenu(v);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		BuildDataSource db = new BuildDataSource(getApplicationContext());
		builds = db.getAllBuilds();

		for (int i = 0; i < builds.size(); i++) {
			menu.add(0, i + 1, 0, builds.get(i).getBuildName());
		}
	}

	public boolean onContextItemSelected(MenuItem item) {
		for (int i = 0; i < builds.size(); i++) {
			if (builds.get(i).getBuildName().equals(item.getTitle())) {
				editDialog(builds.get(i).getBuildName(), builds.get(i)
						.getBuildUrl());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}