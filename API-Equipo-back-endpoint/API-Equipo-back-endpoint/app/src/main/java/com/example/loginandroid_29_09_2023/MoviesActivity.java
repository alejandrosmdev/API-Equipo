package com.example.loginandroid_29_09_2023;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid_29_09_2023.lstMov.view.LstMovies;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_movies4);

        // Configurar el botón para listar películas
        Button btnListMovies = findViewById(R.id.btnListMovies);

        btnListMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en el botón: lanzamos una nueva actividad
                Intent intent = new Intent(MoviesActivity.this, LstMovies.class);
                startActivity(intent);
            }
        });
    }
}