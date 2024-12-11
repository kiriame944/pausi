package com.example.pausi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class TipsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        ListView lvConsejos = view.findViewById(R.id.lv_consejos);

        // Consejos de adopción
        String[] consejos = {
                "Asegúrate de tener tiempo y espacio para una mascota.",
                "Investiga sobre la especie y raza antes de adoptar.",
                "Considera adoptar de refugios y evitar criaderos.",
                "Visita al veterinario para un chequeo inicial.",
                "Prepárate para los gastos de cuidado y alimentación."
        };

        // Adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, consejos);
        lvConsejos.setAdapter(adapter);

        return view;
    }
}
