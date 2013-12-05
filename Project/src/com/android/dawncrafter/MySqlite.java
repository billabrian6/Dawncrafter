package com.android.dawncrafter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlite extends SQLiteOpenHelper{
	public static final String BUILD_ID = "id";
	public static final String BUILD_NAME = "build_name";
	public static final String BUILD_URL = "build_url";
	public static final String DATABASE_NAME = "builds.db";
	public static final int DATABASE_VERSION = 1;
	
	public MySqlite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	private static final String DATABASE_CREATE = "CREATE TABLE build ("+BUILD_ID+" INTEGER NOT NULL,"+BUILD_NAME+" VARCHAR(25),"
			+ BUILD_URL +" VARCHAR(200),"
			+"PRIMARY KEY (id));";
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteOpenHelper.class.getName(), "Upgrading from version " + oldVersion + "to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS build");
		onCreate(db);
	}

}
