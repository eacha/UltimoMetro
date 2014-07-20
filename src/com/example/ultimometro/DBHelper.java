package com.example.ultimometro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper {

	private static final String TAG = "DBHelper";
	private static final String DB_NAME = "database.db";

	private Context context;

	public DBHelper(Context context) {
		this.context = context;
	}
	/**
	 * Create database if not exist
	 * @return Read SQLiteDatabase
	 */
	public SQLiteDatabase getReadableDatabase() {
		File dbFile = this.createDatabase();
		return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
	}
	
	/**
	 * Create database if not exist
	 * @return Read/Write SQLiteDatabase
	 */
	public SQLiteDatabase getWritableDatabase() {
		File dbFile = this.createDatabase();
		return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
	}	

	/**
	 * Create the database from assets file
	 * @return database file
	 */
	private File createDatabase() {
		this.dbFolder();
		File dbFile = context.getDatabasePath(DB_NAME);

		if (!dbFile.exists()) {
			try {
				copyDatabase(dbFile);
			} catch (IOException e) {
				throw new RuntimeException("Error creating source database", e);
			}
		}
		return dbFile;
	}
	
	/**
	 * Copy a database file from assets folder to /data/data/package_name/databases
	 * @param dbFile database file
	 * @throws IOException
	 */
	private void copyDatabase(File dbFile) throws IOException {
		InputStream is = context.getAssets().open(DB_NAME);
		OutputStream os = new FileOutputStream(dbFile);

		byte[] buffer = new byte[1024];
		while (is.read(buffer) > 0) {
			os.write(buffer);
		}

		os.flush();
		os.close();
		is.close();
	}
	
	/**
	 * Create folder databases in the path /data/data/package_name if not exist
	 */
	private void dbFolder() {
		String path = this.context.getCacheDir().getParent() + "/databases";
		File file = new File(path);
		
		if (!file.exists()) {
			Log.d(TAG, "Creating Folder");
			file.mkdirs();
		}
		Log.d(TAG, "Folder Created");
	}

}
