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
    	menu.add(0,1,0,"Amarynth Build");
    	menu.add(0,2,0,"Desecrator Build");
    	menu.add(0,3,0,"Fenmore Build");
    	menu.add(0,4,0,"Mikella Build");
	}
    
    public boolean onContextItemSelected(MenuItem item) {
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