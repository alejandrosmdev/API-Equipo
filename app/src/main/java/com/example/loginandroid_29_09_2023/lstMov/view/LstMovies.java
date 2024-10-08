package com.example.loginandroid_29_09_2023.lstMov.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginandroid_29_09_2023.R;
import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.lstMov.ContractListMovies;
import com.example.loginandroid_29_09_2023.lstMov.adapters.PeliculaAdapter;
import com.example.loginandroid_29_09_2023.lstMov.presenter.LstMoviesPresenter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class LstMovies extends AppCompatActivity implements ContractListMovies.View {

    private LstMoviesPresenter lstMoviesPresenter;
    private RecyclerView recyclerView;
    private PeliculaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_movies4);  // Asegúrate de que esta es la actividad correcta

        // Configuración de la barra de herramientas
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewPeliculas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Instanciar el Presenter
        lstMoviesPresenter = new LstMoviesPresenter(this);
        lstMoviesPresenter.lstMovies("");  // Puedes pasar un filtro si es necesario

        // Aquí puedes mostrar un mensaje de carga o progreso si lo deseas
    }

    @Override
    public void successMovies(ArrayList<Pelicula> lstPelicula) {
        // Muestra un mensaje con el título de la primera película (esto es solo un ejemplo)
        Toast.makeText(this, lstPelicula.get(0).getTitulo(), Toast.LENGTH_SHORT).show();

        // Configurar el adaptador para el RecyclerView
        adapter = new PeliculaAdapter(this, lstPelicula);
        adapter.setOnItemClickListener(new PeliculaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pelicula pelicula) {
                // Acción al hacer clic en una película
                Toast.makeText(LstMovies.this, pelicula.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void failureMovies(String err) {
        // Aquí manejas el error en caso de que falle la obtención de películas
        Toast.makeText(this, "Error al cargar las películas", Toast.LENGTH_SHORT).show();
    }
}
