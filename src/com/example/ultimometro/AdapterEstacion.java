package com.example.ultimometro;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterEstacion extends ArrayAdapter<Estacion> {

	private Activity context;
	private ArrayList<Estacion> data;
	
	public AdapterEstacion(Activity context, ArrayList<Estacion> estacion) {
		super(context, android.R.layout.simple_list_item_1, estacion);
		this.context = context;
		this.data = estacion;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.estacion_layout, null);
		
		TextView estacionName = (TextView) item.findViewById(R.id.estacionName);
		estacionName.setText(this.data.get(position).getName());
		
		//item.setBackgroundColor(this.data.get(position).getColor());
		
		return item;
	}

	public ArrayList<Estacion> getData() {
		return data;
	}
	
}
