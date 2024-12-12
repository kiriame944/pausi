package com.example.pausi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TipsFragment extends Fragment {

    private TextView tvConsejos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        tvConsejos = view.findViewById(R.id.tv_consejos);

        Bundle args = getArguments();
        if (args != null) {
            String petType = args.getString("pet_type", "General");
            tvConsejos.setText("Consejos para adoptar un " + petType + ":\n\n" +
                    "• Evalúa tu estilo de vida: Asegúrate de tener el tiempo, espacio y recursos necesarios para cuidar a un " + petType + ".\n" +
                    "• Infórmate sobre sus necesidades específicas: Cada tipo de mascota tiene requerimientos únicos, como alimentación, ejercicio y atención médica.\n" +
                    "• Proporciona un ambiente adecuado: Prepara un espacio cómodo y seguro para el " + petType + ".\n" +
                    "• Dedica tiempo a la socialización: Ayuda al " + petType + " a adaptarse a su nuevo entorno y fomenta una relación de confianza.\n" +
                    "• Comprométete a largo plazo: Adopta con la intención de ofrecerle un hogar estable y amoroso.");
        } else {
            tvConsejos.setText("Consejos generales sobre la adopción de mascotas:\n\n" +
                    "• Evalúa tu estilo de vida: Asegúrate de tener el tiempo, espacio y recursos necesarios para cuidar a una mascota. Considera factores como tu rutina diaria y el tipo de ambiente en tu hogar.\n\n" +
                    "• Considera la adopción responsable: Opta por adoptar en refugios o asociaciones responsables en lugar de comprar. Esto ayuda a dar un hogar a animales que lo necesitan.\n\n" +
                    "• Preparación del hogar: Asegúrate de tener un espacio seguro y cómodo para la mascota. Incluye elementos esenciales como cama, platos de comida, juguetes y un área designada para sus necesidades.\n\n" +
                    "• Compromiso a largo plazo: Una mascota es una responsabilidad que puede durar muchos años. Evalúa si estás dispuesto a asumir este compromiso a largo plazo.\n\n" +
                    "• Socialización y entrenamiento: Dedica tiempo para educar y socializar a tu mascota, especialmente si es joven o ha pasado por situaciones traumáticas.");
        }

        return view;
    }
}
