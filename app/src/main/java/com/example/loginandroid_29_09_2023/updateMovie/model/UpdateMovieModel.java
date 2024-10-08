package com.example.loginandroid_29_09_2023.updateMovie.model;

import android.util.Log;

import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.updateMovie.ContractUpdateMovie;
import com.example.loginandroid_29_09_2023.updateMovie.model.data.MyData;
import com.example.loginandroid_29_09_2023.updateMovie.presenter.UpdateMoviePresenter;
import com.example.loginandroid_29_09_2023.utils.ApiService;
import com.example.loginandroid_29_09_2023.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMovieModel implements ContractUpdateMovie.Model {
    private static final String IP_BASE = "169.254.225.61:3000";
    private UpdateMoviePresenter presenter;
    public UpdateMovieModel(UpdateMoviePresenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void updateAPI(Pelicula pelicula, final OnUpdateMovieListener onUpdateMovieListener) {
        // Crear una instancia de ApiService
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/peliculas").
                create(ApiService.class);

// Realizar la solicitud al Servlet
        // Call<MyData> call = apiService.getMyData("1");
        Call<Void> call = apiService.actualizarTituloPelicula(pelicula.getId(), pelicula.getTitulo());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("Título de la película actualizado correctamente.");
                } else {
                    System.out.println("Error en la respuesta: " + response.code());
                }
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar errores de red o del servidor
                Log.e("Response Error", "Cuerpo de error: " + t.getMessage());
            }
        });
    }
    }

