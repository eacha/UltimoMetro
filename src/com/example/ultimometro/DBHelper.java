package com.example.ultimometro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
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
	
	public ArrayList<Linea> getArrayLineas(){
		ArrayList<Linea> listLinea = new ArrayList<Linea>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM linea", null);
		
		while(cursor.moveToNext()){
			Linea linea = new Linea(cursor);
			listLinea.add(linea);
		}
		
		db.close();
		return listLinea;
	}
	
	public ArrayList<Estacion> getArrayEstacion(Linea linea){
		ArrayList<Estacion> listEstacion = new ArrayList<Estacion>();
		SQLiteDatabase db = this.getReadableDatabase();
		String[] data = {linea.getId()+""};
		Cursor cursor = db.rawQuery("SELECT * FROM estacion WHERE linea=?", data);
		
		while(cursor.moveToNext()){
			Estacion estacion = new Estacion(cursor);
			listEstacion.add(estacion);
		}
		
		db.close();
		return listEstacion;		
		
	}
	
	public Linea getLinea(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] data = {""+id};
		Cursor cursor = db.rawQuery("SELECT * FROM linea WHERE id=?", data);
		
		cursor.moveToFirst();
		Linea linea = new Linea(cursor);
		
		db.close();
		return linea;
	}
	public Estacion getFirstEstacion(Linea linea) {
		return this.getEstacion(linea.getStart(), linea.getId());
	}
	
	public Estacion getEndEstacion(Linea linea) {
		return this.getEstacion(linea.getEnd(), linea.getId());
	}
	
	public Estacion getEstacion(String name, int idLinea) {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] data = {name, idLinea+""};
		Cursor cursor = db.rawQuery("SELECT * FROM estacion WHERE name=? and linea=?", data);
		
		cursor.moveToFirst();
		Estacion estacion =  new Estacion(cursor);
		
		db.close();
		return estacion;
	}
	
	public Estacion getEstacionById(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String[] data = {id+""};
		Cursor cursor = db.rawQuery("SELECT * FROM estacion WHERE id=?", data);
		
		cursor.moveToFirst();
		Estacion estacion =  new Estacion(cursor);
		
		db.close();
		return estacion;
	}
	
	public Horario getHorario(Estacion estacion, int tipo) {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] data = {estacion.getId()+"", tipo+""};
		Cursor cursor = db.rawQuery("SELECT * FROM horario WHERE estacion=? and type=?", data);
		
		cursor.moveToFirst();
		Horario horario =  new Horario(cursor);
		
		db.close();
		return horario;
	}
	
	public ArrayList<Horario> getAllHorario(Estacion estacion) {
		ArrayList<Horario> listHorario = new ArrayList<Horario>();
		SQLiteDatabase db = this.getReadableDatabase();
		String[] data = {estacion.getId()+""};
		Cursor cursor = db.rawQuery("SELECT * FROM horario WHERE estacion=?", data);
		
		while(cursor.moveToNext()){
			listHorario.add(new Horario(cursor));
		}
		
		db.close();
		return listHorario;
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
			Log.d(TAG, "Folder Created");
		}
		
	}

}
