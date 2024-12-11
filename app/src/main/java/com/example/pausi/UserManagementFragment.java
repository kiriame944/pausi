package com.example.pausi;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UserManagementFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private String correoActual = "kiriame04@gmail.com";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_management, container, false);

        databaseHelper = new DatabaseHelper(getContext());

        TextView tvUsuario = view.findViewById(R.id.tv_usuario);
        Button btnCargarUsuario = view.findViewById(R.id.btn_cargar_usuario);
        Button btnEditarUsuario = view.findViewById(R.id.btn_editar_usuario);
        Button btnEliminarUsuario = view.findViewById(R.id.btn_eliminar_usuario);

        // Acción para cargar los datos del usuario
        btnCargarUsuario.setOnClickListener(v -> {
            Cursor cursor = databaseHelper.obtenerUsuario(correoActual);
            if (cursor != null && cursor.moveToFirst()) {
                String infoUsuario = "Nombre: " + cursor.getString(cursor.getColumnIndexOrThrow("nombre")) + "\n" +
                        "Correo: " + cursor.getString(cursor.getColumnIndexOrThrow("correo"));
                tvUsuario.setText(infoUsuario);
                System.out.println("Usuario encontrado: " + infoUsuario);
            } else {
                tvUsuario.setText("No se encontró información del usuario.");
                System.out.println("No se encontró el usuario con correo: " + correoActual);
            }
            if (cursor != null) {
                cursor.close();
            }
        });

        // Acción para editar usuario (mínimo)
        btnEditarUsuario.setOnClickListener(v -> {
            if (correoActual != null) {
                boolean actualizado = databaseHelper.actualizarUsuario(correoActual, "Ameyali", "kiaame04@gmail.com", "holiwis");
                if (actualizado) {
                    Toast.makeText(getContext(), "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
                    correoActual = "kiaame04@gmail.com";
                } else {
                    Toast.makeText(getContext(), "Error al actualizar usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "No hay un usuario activo para editar.", Toast.LENGTH_SHORT).show();
            }
        });

        // Acción para eliminar usuario
        btnEliminarUsuario.setOnClickListener(v -> {
            if (correoActual != null) {
                boolean eliminado = databaseHelper.eliminarUsuario(correoActual);
                if (eliminado) {
                    Toast.makeText(getContext(), "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();
                    correoActual = null;
                } else {
                    Toast.makeText(getContext(), "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "No hay un usuario activo para eliminar.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
