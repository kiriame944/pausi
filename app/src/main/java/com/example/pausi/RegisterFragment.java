package com.example.pausi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        databaseHelper = new DatabaseHelper(getContext());

        EditText etNombre = view.findViewById(R.id.et_nombre);
        EditText etCorreo = view.findViewById(R.id.et_correo);
        EditText etContraseña = view.findViewById(R.id.et_contrasena);
        Button btnRegistrar = view.findViewById(R.id.btn_registrar);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String correo = etCorreo.getText().toString();
            String contraseña = etContraseña.getText().toString();

            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contraseña)) {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                boolean registrado = databaseHelper.registrarUsuario(nombre, correo, contraseña);
                if (registrado) {
                    Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etCorreo.setText("");
                    etContraseña.setText("");
                } else {
                    Toast.makeText(getContext(), "Error: El correo ya está registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
