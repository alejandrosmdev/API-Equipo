package com.example.loginandroid_29_09_2023.utils;

import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.data.MyData;
import com.example.loginandroid_29_09_2023.lstMov.data.DataMovies;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

  @GET("movies")
  Call<DataMovies> getAllMovies();

  @POST("/login")
  Call<MyData> login(@Body User user);

  //Preguntar cosa clases "extra" de abajo a Alberto frente a las ya existentes en cada caso de uso
}