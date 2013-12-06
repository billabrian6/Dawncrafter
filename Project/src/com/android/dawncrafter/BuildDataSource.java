package com.android.dawncrafter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//===============================================================================
//Project    : Android: Dawncrafter Theorycrafting Application               	=
//File Name  : BuildDataSource.java												=
//File Type  : Class  					    									=
//Authors    : Brian Green & Brandon Aikey		    							=
//Date       : 12/5/2013														=
//Description: Responsible for CRUD operations (SQLite Database)               	=
//===============================================================================

public class BuildDataSource {
	private SQLiteDatabase database;
	private MySqliteHelper dbHelper;
	private String[] allColumns = { MySqliteHelper.BUILD_ID,
			MySqliteHelper.BUILD_NAME, MySqliteHelper.BUILD_URL };

	public BuildDataSource(Context context) {
		dbHelper = new MySqliteHelper(context);
	}

	public void close() {
		dbHelper.close();
	}

	public Build createBuild(String buildName, String buildUrl) {
		database = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MySqliteHelper.BUILD_NAME, buildName);
		values.put(MySqliteHelper.BUILD_URL, buildUrl);

		long insertId = database
				.insert(MySqliteHelper.TABLE_NAME, null, values);
		Cursor cursor = database.query(MySqliteHelper.TABLE_NAME, allColumns,
				MySqliteHelper.BUILD_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Build newBuild = cursorToBuild(cursor);
		cursor.close();
		return newBuild;
	}

	public void deleteBuild(String buildName) {
		database = dbHelper.getWritableDatabase();
		database.delete(MySqliteHelper.TABLE_NAME, MySqliteHelper.BUILD_NAME
				+ " = ?", new String[] { buildName });
	}

	public ArrayList<Build> getAllBuilds() {
		database = dbHelper.getReadableDatabase();
		ArrayList<Build> builds = new ArrayList<Build>();
		Cursor cursor = database.query(MySqliteHelper.TABLE_NAME, allColumns,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Build build = cursorToBuild(cursor);
			builds.add(build);
			cursor.moveToNext();
		}
		cursor.close();
		return builds;
	}

	private Build cursorToBuild(Cursor cursor) {
		Build build = new Build();
		build.setID(cursor.getString(0));
		build.setBuildName(cursor.getString(1));
		build.setBuildUrl(cursor.getString(2));
		return build;
	}

}
