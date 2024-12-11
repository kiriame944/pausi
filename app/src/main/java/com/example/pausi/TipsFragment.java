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
            tvConsejos.setText("Consejos para adoptar un " + petType);
        } else {
            tvConsejos.setText("Consejos generales de adopción.");
        }

        return view;
    }
}
