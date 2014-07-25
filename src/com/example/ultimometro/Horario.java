/**
 * 
 */
package com.example.ultimometro;

import android.database.Cursor;

/**
 * @author eduardo
 *
 */
public class Horario {
	
	private int id;
	private int estacion;
	private int type;
	private String open;
	private String close;
	private String first_start;
	private String last_start;
	private String first_end;
	private String last_end;
	
	public Horario(int id, int estacion, int type, String open, String close,
			String first_start, String last_start, String first_end, String last_end) {
		super();
		this.id = id;
		this.estacion = estacion;
		this.type = type;
		this.open = open;
		this.close = close;
		this.first_start = first_start;
		this.last_start = last_start;
		this.first_end = first_end;
		this.last_end = last_end;
	}
	
	public Horario(Cursor cursor) {
		this(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
			 cursor.getString(3), cursor.getString(4), cursor.getString(5),
			 cursor.getString(6), cursor.getString(7), cursor.getString(8));
	}

	public int getId() {
		return id;
	}

	public int getEstacion() {
		return estacion;
	}

	public int getType() {
		return type;
	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public String getFirst_start() {
		return first_start;
	}

	public String getLast_start() {
		return last_start;
	}

	public String getFirst_end() {
		return first_end;
	}

	public String getLast_end() {
		return last_end;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", estacion=" + estacion + ", type="
				+ type + ", open=" + open + ", close=" + close
				+ ", first_start=" + first_start + ", last_start=" + last_start
				+ ", first_end=" + first_end + ", last_end=" + last_end + "]";
	}
	
	
	
	
	
	

}
