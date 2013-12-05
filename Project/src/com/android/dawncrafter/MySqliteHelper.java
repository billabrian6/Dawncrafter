package com.android.dawncrafter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper{
	public static final String BUILD_ID = "id";
	public static final String BUILD_NAME = "build_name";
	public static final String BUILD_URL = "build_url";
	public static final String DATABASE_NAME = "builds.db";
	public static final String TABLE_NAME = "build";
	public static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "CREATE TABLE "+ TABLE_NAME + " ("+BUILD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BUILD_NAME+" VARCHAR(25), "
			+ BUILD_URL +" VARCHAR(200));";
	
	public MySqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (db == null) {
			Log.d("test", "oh shit");
		}
		Log.d("test", DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE);
		Log.d("test", "Made it");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteOpenHelper.class.getName(), "Upgrading from version " + oldVersion + "to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS build");
		onCreate(db);
	}
}
