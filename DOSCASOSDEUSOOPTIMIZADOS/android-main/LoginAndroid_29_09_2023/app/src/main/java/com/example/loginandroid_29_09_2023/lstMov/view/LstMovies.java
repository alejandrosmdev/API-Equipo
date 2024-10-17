package com.example.loginandroid_29_09_2023.lstMov.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.loginandroid_29_09_2023.R;
import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.lstMov.ContractListMovies;
import com.example.loginandroid_29_09_2023.lstMov.adapters.PeliculaAdapter; // Corregido: faltaba el punto y coma
import com.example.loginandroid_29_09_2023.lstMov.presenter.LstMoviesPresenter;

import java.util.ArrayList;

public class LstMovies extends AppCompatActivity implements ContractListMovies.View {
    private RecyclerView recyclerView;
    private PeliculaAdapter adapter;
    private LstMoviesPresenter presenter;
    private ArrayList<Pelicula> peliculas;

    //Botón para pulsar y listar las películas
    private Button btnLstMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_movies4);

        //Asignar ID del botón del XML
        btnLstMovies = findViewById(R.id.btnLstMovies);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewPeliculas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de películas
        peliculas = new ArrayList<>();
        adapter = new PeliculaAdapter(this, peliculas);
        recyclerView.setAdapter(adapter);

        // Inicializar el presenter y cargar las películas
        presenter = new LstMoviesPresenter(this);


        //Event listener para no mostrar las películas hasta pulsar el botón
        btnLstMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.lstMovies("GET_MOVIES"); // Llamar a la función para cargar las películas
            }
        });
    }

    @Override
    public void successMovies(ArrayList<Pelicula> peliculas) {
        this.peliculas.clear(); // Limpiar la lista actual
        this.peliculas.addAll(peliculas); // Agregar las nuevas películas
        adapter.notifyDataSetChanged(); // Notificar al adaptador sobre los cambios
    }

    @Override
    public void failureMovies(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show(); // Mostrar mensaje de error
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu); // Inflar el menú (opcional)
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar selección de elementos del menú (opcional)
        int id = item.getItemId();
        //if (id == R.id.action_settings) {
            // Acción para el menú de configuración
            return true;
        //}
       // return super.onOptionsItemSelected(item);
    }
}
