package com.example.loginandroid_29_09_2023.utils;

import com.example.loginandroid_29_09_2023.login_user.model.data.MyData;
import com.example.loginandroid_29_09_2023.lstMov.data.DataMovies;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

  public static final String URL = "http://10.0.2.2:3000/"; // Para emuladores de Android, debemos utilizar esta IP
  // public static final String URL = "http://192.168.1.100:3000/"; // Para dispositivos físicos (si probamos en nuestro móvil en la misma red WIFI que el PC)

  @Headers({
          "Accept: application/json",
          "Content-Type: application/json"
  })

  @POST("/login")
  Call<MyData> loginUser(@Body MyData loginData);

  @GET("/movies")
  Call<DataMovies> getAllMovies();


  @PUT("/peliculas/{id}")
  Call<DataMovies> updateMovieTitle(@Path("id") int id, @Body DataMovies movieData);
  //Los parámetros se llaman igual que en servidor para poder conectarnos al mismo
}
