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

public class LoginFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        databaseHelper = new DatabaseHelper(getContext());

        EditText etCorreo = view.findViewById(R.id.et_correo);
        EditText etContraseña = view.findViewById(R.id.et_contrasena);
        Button btnIniciarSesion = view.findViewById(R.id.btn_iniciar_sesion);

        btnIniciarSesion.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString();
            String contraseña = etContraseña.getText().toString();

            if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contraseña)) {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                boolean existe = databaseHelper.verificarUsuario(correo, contraseña);
                if (existe) {
                    Toast.makeText(getContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error: Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
