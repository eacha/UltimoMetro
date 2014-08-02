package com.example.ultimometro;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ultimometro.model.Linea;

public class AdapterLinea extends ArrayAdapter<Linea> {

	private Activity context;
	private ArrayList<Linea> data;
	
	public AdapterLinea(Activity context, ArrayList<Linea> lineas) {
		super(context, android.R.layout.simple_list_item_1, lineas);
		this.context = context;
		this.data = lineas;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.linea_layout, null);
		
		TextView lineaName = (TextView) item.findViewById(R.id.lineaName);
		lineaName.setText(this.data.get(position).getName());
		
		item.setBackgroundColor(this.data.get(position).getColor());
		
		return item;
	}

	public ArrayList<Linea> getData() {
		return data;
	}
	
}
