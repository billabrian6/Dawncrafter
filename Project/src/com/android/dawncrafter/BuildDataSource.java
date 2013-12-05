package com.android.dawncrafter;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BuildDataSource {
	private SQLiteDatabase database;
	private MySqliteHelper dbHelper;
	@SuppressWarnings("static-access")
	private String[] allColumns = {dbHelper.BUILD_ID, dbHelper.BUILD_NAME, dbHelper.BUILD_URL};
	
	public BuildDataSource(Context context){
		dbHelper = new MySqliteHelper(context);
	}
	public void open() throws SQLException{
		database = dbHelper.getReadableDatabase();
	}
	public void close(){
		dbHelper.close();
	}
	public Build createBuild(String id, String buildName, String buildUrl) {
	    ContentValues values = new ContentValues();
	    values.put(MySqliteHelper.BUILD_ID, id);
	    values.put(MySqliteHelper.BUILD_NAME, buildName);
	    values.put(MySqliteHelper.BUILD_ID, buildUrl);

	    long insertId = database.insert(MySqliteHelper.TABLE_NAME, null, values);
	    Cursor cursor = database.query(MySqliteHelper.TABLE_NAME, allColumns, MySqliteHelper.BUILD_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Build newBuild = cursorToBuild(cursor);
	    cursor.close();
	    return newBuild;
	  }
	public void deleteBuild(Build build){
		String id = build.getID();
		Log.d("Delete from db", id);
		database.delete(MySqliteHelper.TABLE_NAME, MySqliteHelper.BUILD_ID + " = " + id, null);
	}
	public List<Build> getAllBuilds(){
		List<Build> builds = new ArrayList<Build>();
		Cursor cursor = database.query(MySqliteHelper.TABLE_NAME, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Build build = cursorToBuild(cursor);
			builds.add(build);
			cursor.moveToNext();
		}
		cursor.close();
		return builds;
	}
	
	
	private Build cursorToBuild(Cursor cursor){
		Build build = new Build();
		build.setID(cursor.getString(0));
		build.setBuildName(cursor.getString(1));
		build.setBuildUrl(cursor.getString(2));
		return build;
	}
	
}
