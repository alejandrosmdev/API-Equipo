package com.example.loginandroid_29_09_2023.lstMov.model;

import android.util.Log;
import com.example.loginandroid_29_09_2023.lstMov.ContractListMovies;
import com.example.loginandroid_29_09_2023.lstMov.data.DataMovies;
import com.example.loginandroid_29_09_2023.lstMov.presenter.LstMoviesPresenter;
import com.example.loginandroid_29_09_2023.utils.ApiService;
import com.example.loginandroid_29_09_2023.utils.RetrofitCliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstMoviesModel implements ContractListMovies.Model {
    private final LstMoviesPresenter presenter;
    String BASEURL = "http://192.168.1.9:3000";

    public LstMoviesModel(LstMoviesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void moviesAPI(String filtro, OnLstMoviesListener respuestaMovies) {
        ApiService apiService = RetrofitCliente.getClient(BASEURL).create(ApiService.class);
        Call<DataMovies> call = apiService.getAllMovies();

        call.enqueue(new Callback<DataMovies>() {
            @Override
            public void onResponse(Call<DataMovies> call, Response<DataMovies> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DataMovies dataMovies = response.body();
                    if (dataMovies.getLstPeliculas() != null && !dataMovies.getLstPeliculas().isEmpty()) {
                        respuestaMovies.onFinished(new ArrayList<>(dataMovies.getLstPeliculas()));
                    } else {
                        respuestaMovies.onFailure("No se encontraron películas.");
                    }
                } else {
                    respuestaMovies.onFailure("Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DataMovies> call, Throwable t) {
                Log.e("API Error", t.getMessage());
                respuestaMovies.onFailure("Error de red: " + t.getMessage());
            }
        });
    }
}