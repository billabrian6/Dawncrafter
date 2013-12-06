package com.android.dawncrafter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//===============================================================================
//Project    : Android: Dawncrafter Theorycrafting Application               	=
//File Name  : MySqliteHelper.java												=
//File Type  : Class															=
//Authors    : Brian Green & Brandon Aikey										=
//Date       : 12/5/2013														=
//Description: Responsibe for creating the database								=
//===============================================================================

public class MySqliteHelper extends SQLiteOpenHelper {
	public static final String BUILD_ID = "id";
	public static final String BUILD_NAME = "build_name";
	public static final String BUILD_URL = "build_url";
	public static final String DATABASE_NAME = "builds.db";
	public static final String TABLE_NAME = "build";
	public static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " (" + BUILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ BUILD_NAME + " VARCHAR(25), " + BUILD_URL + " VARCHAR(255));";

	//Constructor
	public MySqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//Method creates the database
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (db == null) {
		}
		db.execSQL(DATABASE_CREATE);
	}

	//Method to drop
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS build");
		onCreate(db);
	}
}
