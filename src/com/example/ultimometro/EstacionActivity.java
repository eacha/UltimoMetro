package com.example.ultimometro;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EstacionActivity extends ActionBarActivity {

	private TextView textoEstacion;
	private DBHelper database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estacion);
		
		Intent intent =  getIntent();
		textoEstacion = (TextView) findViewById(R.id.texto_estacion);
		database =  new DBHelper(this);
		
		int idLinea = Integer.parseInt(intent.getStringExtra("ID")),
			idEstacion = Integer.parseInt(intent.getStringExtra("ESTACION"));
		
		if (idEstacion == 0) {
			Linea linea = database.getLinea(idLinea);
			Estacion estacion = database.getFirstEstacion(linea);
			ArrayList<Horario> lista = database.getAllHorario(estacion);
			
			textoEstacion.setText(estacion.getName());
			this.createStartTables(linea, lista);
			this.createNightTables(linea, lista);		
		}
		else {
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estacion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void createStartTables(Linea linea, ArrayList<Horario> lista){
		
		TextView start_station = (TextView) findViewById(R.id.start),
				 end_station = (TextView) findViewById(R.id.end);
		
		int i = 1;
		String cell = "cell_";
		
		start_station.setText(linea.getBreakStart());
		end_station.setText(linea.getBreakEnd());
		
		for (Horario horario : lista) {
			int resID_open = getResources().getIdentifier(cell+i, "id", getPackageName()),
				resID_start = getResources().getIdentifier(cell+(i+1), "id", getPackageName()),
				resID_end = getResources().getIdentifier(cell+(i+2), "id", getPackageName());
			
			TextView open = (TextView) findViewById(resID_open),
					 start = (TextView) findViewById(resID_start),
					 end = (TextView) findViewById(resID_end);
			
			open.setText(horario.getOpen());
			start.setText(horario.getFirst_start());
			end.setText(horario.getFirst_end());
			
			i = i + 3;
		}
		
	}
	
	private void createNightTables(Linea linea, ArrayList<Horario> lista){
		
		TextView start_station = (TextView) findViewById(R.id.startNight),
				 end_station = (TextView) findViewById(R.id.endNight);
		
		int i = 1;
		String cell = "celln_";
		
		start_station.setText(linea.getBreakStart());
		end_station.setText(linea.getBreakEnd());
		
		for (Horario horario : lista) {
			int resID_close = getResources().getIdentifier(cell+i, "id", getPackageName()),
				resID_start = getResources().getIdentifier(cell+(i+1), "id", getPackageName()),
				resID_end = getResources().getIdentifier(cell+(i+2), "id", getPackageName());
			
			TextView close = (TextView) findViewById(resID_close),
					 start = (TextView) findViewById(resID_start),
					 end = (TextView) findViewById(resID_end);
			
			close.setText(horario.getClose());
			start.setText(horario.getLast_start());
			end.setText(horario.getLast_end());
			
			i = i + 3;
		}
		
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_estacion,
					container, false);
			return rootView;
		}
	}

}
