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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EstacionActivity extends ActionBarActivity {

	private TextView textoEstacion;
	private TableLayout tableLayout;
	private DBHelper database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estacion);
		
		Intent intent =  getIntent();
		textoEstacion = (TextView) findViewById(R.id.texto_estacion);
		//tableLayout = (TableLayout) findViewById(R.id.daytable);
		database =  new DBHelper(this);
		
		int idLinea = Integer.parseInt(intent.getStringExtra("ID"));
		Linea linea = database.getLinea(idLinea);
		Estacion estacion = database.getFirstEstacion(linea);
		ArrayList<Horario> lista = database.getAllHorario(estacion);
		
		textoEstacion.setText(estacion.getName());
		this.createStartTables(linea, lista);
				
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
		
		for (int i = 0; i < tableLayout.getChildCount(); i++) {
			TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
			if (i == 0) {
				//Cabecera de la tabla
				TextView first = new TextView(this);
				TextView end = new TextView(this);
				
				first.setText(linea.getStart());
				end.setText(linea.getEnd());
				
				tableRow.addView(first);
				tableRow.addView(end);
				
				continue;
			}
			
			Horario current = lista.get(i-1);
			TextView open, close, firstTrain;
			
			open =  new TextView(this);
			close =  new TextView(this);
			firstTrain =  new TextView(this);
			
			open.setText(current.getOpen());
			close.setText(current.getClose());
			firstTrain.setText(current.getFirst_start());
					
			tableRow.addView(open);
			tableRow.addView(close);
			tableRow.addView(firstTrain);
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
