package com.example.ultimometro;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.ultimometro.model.Linea;

import java.util.ArrayList;

public class MenuLineaActivity extends ActionBarActivity {

	private ListView listLinea;
	private DBHelper database;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_linea);

        database = new DBHelper(this);
        ArrayList<Linea> dataLinea =  database.getArrayLineas();     
        listLinea = (ListView) findViewById(R.id.listLinea);
        
        android.support.v7.app.ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        AdapterLinea adapter = new AdapterLinea(this, dataLinea);
        listLinea.setAdapter(adapter);        
        listLinea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				int idLinea = ((Linea)a.getAdapter().getItem(position)).getId();
				
				Intent intent = new Intent(getApplicationContext(), EstacionActivity.class);
				intent.putExtra("ID", ""+idLinea);
				intent.putExtra("ESTACION", ""+0);
				startActivity(intent);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
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
            View rootView = inflater.inflate(R.layout.fragment_menu_linea, container, false);
            return rootView;
        }
    }
}
