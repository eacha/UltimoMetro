package com.example.ultimometro;

import android.database.Cursor;
import android.graphics.Color;


public class Linea {
	
	private int id;
	private String name;
	private int color;
	private String start;
	private String end;
	private String express;
	
	public Linea(int id, String name, String color, String start, String end,
			String express) {
		super();
		this.id = id;
		this.name = name;
		this.color = Color.parseColor(color);
		this.start = start;
		this.end = end;
		this.express = express;
	}

	public Linea(Cursor cursor) {
		this(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
			 cursor.getString(3), cursor.getString(4), cursor.getString(5));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}	

	public String isExpress() {
		return express;
	}
	
	@Override
	public String toString() {
		return "Linea [id=" + id + ", name=" + name + ", color=" + color
				+ ", start=" + start + ", end=" + end + ", express=" + express
				+ "]";
	}
	
}
