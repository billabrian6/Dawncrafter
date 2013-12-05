package com.android.dawncrafter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageButton;

import com.example.project.R;

public class MainActivity extends Activity {
	
	public void sendMessage(View view){
		Intent intent = new Intent(this, NewBuildActivity.class);
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
    	String build1 = getResources().getString(R.string.build1);
    	String build2  = getResources().getString(R.string.build2);
    	String build3 = getResources().getString(R.string.build3);
    	String build4 = getResources().getString(R.string.build4);
    	String build5 = getResources().getString(R.string.build5);

    	menu.add(0,1,0,build1);
    	menu.add(0,2,0,build2);
    	menu.add(0,3,0,build3);
    	menu.add(0,4,0,build4);
    	menu.add(0,5,0,build5);
	}
    
    public boolean onContextItemSelected(MenuItem item) {
        @SuppressWarnings("unused")
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == 1) {
        	Log.d("SAVE1", "WOO");
            return true;
        } else if (item.getItemId() == 2) {
        	Log.d("SAVE2", "YEAH");
            return true;
        } else {
        	return super.onContextItemSelected(item);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}