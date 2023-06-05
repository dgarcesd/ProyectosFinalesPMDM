package com.example.proyectofinal1ev;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal1ev.databinding.ActivityJuegosBinding;

public class JuegosActivity extends AppCompatActivity {
    ActivityJuegosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJuegosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupMenuButtons();
    }

    private void setupMenuButtons() {
        binding.rellayCarreras.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarrerasActivity.class);
            startActivity(intent);
        });
        binding.rellayDeporte.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeporteActivity.class);
            startActivity(intent);
        });
        binding.rellayDisparos.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisparosActivity.class);
            startActivity(intent);
        });
        binding.rellayAventura.setOnClickListener(v -> {
            Intent intent = new Intent(this, AventuraActivity.class);
            startActivity(intent);
        });

    }
}