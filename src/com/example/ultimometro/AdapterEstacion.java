package com.example.ultimometro;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ultimometro.model.Estacion;
import com.example.ultimometro.model.Linea;

import java.util.ArrayList;

public class AdapterEstacion extends ArrayAdapter<Estacion> {

	private Activity context;
	private ArrayList<Estacion> data;
	private Linea linea;
	
	public AdapterEstacion(Activity context, ArrayList<Estacion> estacion, Linea linea) {
		super(context, android.R.layout.simple_list_item_1, estacion);
		this.context = context;
		this.data = estacion;
		this.linea = linea;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.estacion_layout, null);
		
		TextView estacionName = (TextView) item.findViewById(R.id.estacionName);
        View line =  (View) item.findViewById(R.id.draw_line),
             station = (View) item.findViewById(R.id.draw_station);

       ((GradientDrawable)station.getBackground()).setStroke(4, linea.getColor());

        estacionName.setText(this.data.get(position).getName());
		line.setBackgroundColor(linea.getColor());
		
		return item;
	}

	public ArrayList<Estacion> getData() {
		return data;
	}
	
}
