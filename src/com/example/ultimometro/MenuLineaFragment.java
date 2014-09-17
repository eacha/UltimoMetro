package com.example.ultimometro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.ultimometro.model.DBHelper;
import com.example.ultimometro.model.Linea;

import java.util.ArrayList;

public class MenuLineaFragment extends Fragment {

    private FragmentActivity fragmentActivity;
	//private ListView listLinea;
	//private DBHelper database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentActivity = getActivity();
        return inflater.inflate(R.layout.fragment_menu_linea, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view =  getView();

        DBHelper database = new DBHelper(fragmentActivity);
        ArrayList<Linea> dataLinea =  database.getArrayLineas();
        assert view != null;
        ListView listLinea = (ListView) view.findViewById(R.id.listLinea);

        /* Action Bar */

        AdapterLinea adapter = new AdapterLinea(fragmentActivity, dataLinea);
        listLinea.setAdapter(adapter);
        listLinea.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                int idLinea = ((Linea)a.getAdapter().getItem(position)).getId();

                Intent intent = new Intent(fragmentActivity.getApplicationContext(), EstacionActivity.class);
                intent.putExtra("ID", ""+idLinea);
                intent.putExtra("ESTACION", ""+0);
                startActivity(intent);
            }
        });

    }

}
