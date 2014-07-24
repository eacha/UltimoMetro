/**
 * 
 */
package com.example.ultimometro;

import android.database.Cursor;

/**
 * @author eduardo
 *
 */
public class Estacion {
	
	private int id;
	private String name;
	private int idLinea;
	private String position;
	
	public Estacion(int id, String name, int idLinea, String position) {
		super();
		this.id = id;
		this.name = name;
		this.idLinea = idLinea;
		this.position = position;
	}
	
	public Estacion(Cursor cursor) {
		this(cursor.getInt(0), cursor.getString(1),
			 cursor.getInt(2), cursor.getString(3));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getIdLinea() {
		return idLinea;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "Estacion [id=" + id + ", name=" + name + ", idLinea=" + idLinea
				+ ", position=" + position + "]";
	}
	
	
	
	

}
