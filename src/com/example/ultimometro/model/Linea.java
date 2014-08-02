package com.example.ultimometro.model;

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
	
	public String getBreakStart(){
		return this.twoLine(this.start);
	}
	
	public String getBreakEnd(){
		return this.twoLine(this.end);
	}
	
	private String twoLine(String word) {
		String[] words =  word.split(" ");
		int largo = 0,
			wordLength = word.length()/2;
		String res = "";
		boolean salto = true;
		if (words.length > 1){
			for (String string : words) {
				if ((largo+1+string.length() > wordLength) && salto ) {
					res += ("\n"+string);
					salto = false;
				}
				else if(res.equals("")) {
					 res = string;
				}
				else {
					res += (" "+ string); 
				}
				largo = res.length();
			}
			return res;
		}
		else
			return word;
	}
	
	@Override
	public String toString() {
		return "Linea [id=" + id + ", name=" + name + ", color=" + color
				+ ", start=" + start + ", end=" + end + ", express=" + express
				+ "]";
	}
	
}
