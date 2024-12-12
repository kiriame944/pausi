package com.example.pausi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectPetFragment extends Fragment {

    private RadioGroup rgMascotas;
    private Button btnIrATips;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_pet, container, false);

        rgMascotas = view.findViewById(R.id.rg_mascotas);
        btnIrATips = view.findViewById(R.id.btn_ir_a_tips);

        btnIrATips.setOnClickListener(v -> {
            int selectedId = rgMascotas.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(getContext(), "Por favor selecciona una mascota", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedPet = view.findViewById(selectedId);
            String petType = selectedPet.getText().toString();

            TipsFragment tipsFragment = new TipsFragment();
            Bundle args = new Bundle();
            args.putString("pet_type", petType);
            tipsFragment.setArguments(args);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, tipsFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
