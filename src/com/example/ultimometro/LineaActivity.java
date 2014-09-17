package com.example.ultimometro;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.ultimometro.model.DBHelper;
import com.example.ultimometro.model.Estacion;
import com.example.ultimometro.model.Linea;

import java.util.ArrayList;

public class LineaActivity extends ActionBarActivity {

	private DBHelper database;
	private ArrayList<Estacion> estacionList;
	private ListView listEstacion;
	private Linea linea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linea);
		
		Intent intent = getIntent();
		int idLinea = Integer.parseInt(intent.getStringExtra("ID"));
		
		database = new DBHelper(this);
		linea = database.getLinea(idLinea);
		estacionList = database.getArrayEstacion(linea);
		listEstacion = (ListView) findViewById(R.id.listLinea);

		android.support.v7.app.ActionBar bar = getSupportActionBar();
		bar.setTitle(linea.getName());
		bar.setBackgroundDrawable(new ColorDrawable(linea.getColor()));
		
		AdapterEstacion estacion = new AdapterEstacion(this, estacionList, linea);
		listEstacion.setAdapter(estacion);
		listEstacion.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				int idEstacion = ((Estacion)adapter.getAdapter().getItem(position)).getId();
				Intent intent = new Intent(getApplicationContext(), EstacionActivity.class);
				intent.putExtra("ID", linea.getId()+"");
				intent.putExtra("ESTACION", idEstacion+"");
				startActivity(intent);
				
			}
		
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.linea, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_linea,
					container, false);
			return rootView;
		}
	}
}
