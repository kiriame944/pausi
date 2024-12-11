package com.example.pausi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SelectPetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_pet, container, false);

        RadioGroup rgMascotas = view.findViewById(R.id.rg_mascotas);
        Button btnSeleccionar = view.findViewById(R.id.btn_seleccionar_mascota);

        // Acción para el botón Seleccionar
        btnSeleccionar.setOnClickListener(v -> {
            int selectedId = rgMascotas.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(getContext(), "Por favor selecciona una mascota", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rbSeleccionado = view.findViewById(selectedId);
                String mascotaSeleccionada = rbSeleccionado.getText().toString();
                Toast.makeText(getContext(), "Mascota seleccionada: " + mascotaSeleccionada, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
