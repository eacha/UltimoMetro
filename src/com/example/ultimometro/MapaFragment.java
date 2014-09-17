package com.example.ultimometro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MapaFragment extends Fragment {

    private static final String TAG = "UltimoMetro::MapaFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.mapa);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setOnTouchListener(new Touch());
        }
    }

}
