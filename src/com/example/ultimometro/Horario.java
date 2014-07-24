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
	private int open;
	private int close;
	private int first_start;
	private int last_start;
	private int first_end;
	private int last_end;
	
	public Horario(int id, int estacion, int type, int open, int close,
			int first_start, int last_start, int first_end, int last_end) {
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
			 cursor.getInt(3), cursor.getInt(4), cursor.getInt(5),
			 cursor.getInt(6), cursor.getInt(7), cursor.getInt(8));
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

	public int getOpen() {
		return open;
	}

	public int getClose() {
		return close;
	}

	public int getFirst_start() {
		return first_start;
	}

	public int getLast_start() {
		return last_start;
	}

	public int getFirst_end() {
		return first_end;
	}

	public int getLast_end() {
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
