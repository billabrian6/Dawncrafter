package com.android.dawncrafter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.project.R;

public class MainActivity extends Activity {
	private ArrayList<Build> builds;
	
	public void newBuild(View view){
		Intent intent = new Intent(this, NewBuildActivity.class);
		startActivity(intent);
	}
	
	public void loadBuild(String name, String url) {
		Intent intent = new Intent(this, NewBuildActivity.class);
		intent.putExtra("name", name);
		intent.putExtra("url", url);
		startActivity(intent);
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
    
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	BuildDataSource db = new BuildDataSource(getApplicationContext());
    	builds = db.getAllBuilds();
    	
    	for (int i = 0; i < builds.size(); i++) {
    		menu.add(0, i+1, 0, builds.get(i).getBuildName());
    	}
	}
    
    public boolean onContextItemSelected(MenuItem item) {
    	for (int i = 0; i < builds.size(); i++) {
    		if (builds.get(i).getBuildName().equals(item.getTitle())) {
    			loadBuild(builds.get(i).getBuildName(), builds.get(i).getBuildUrl());
    			return true;
    		}
    	}
    	return false;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}