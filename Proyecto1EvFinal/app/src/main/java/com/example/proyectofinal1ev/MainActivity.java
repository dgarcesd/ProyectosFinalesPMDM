package com.example.proyectofinal1ev;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal1ev.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupMenuButtons();

    }

    private void setupMenuButtons() {
        binding.rellayJuegos.setOnClickListener(v -> {
            Intent intent = new Intent(this, JuegosActivity.class);
            startActivity(intent);
        });

        binding.rellayTiendas.setOnClickListener(v -> {
            Intent intent = new Intent(this, TiendasActivity.class);
            startActivity(intent);
        });


    }


}