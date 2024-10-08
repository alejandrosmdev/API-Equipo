package com.example.loginandroid_29_09_2023.utils;

import com.example.loginandroid_29_09_2023.login_user.model.data.MyData;
import com.example.loginandroid_29_09_2023.lstMov.data.DataMovies;
import com.example.loginandroid_29_09_2023.beans.User; // Asegúrate de importar tu clase User

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

  public static final String URL = "http://192.168.104.70:3000/"; // IP del servidor

  @Headers({
          "Accept: application/json",
          "Content-Type: application/json"
  })

  // Método para iniciar sesión
  @POST("MyServlet")
  Call<MyData> login(@Body User user); // Envío del objeto User como JSON

  // Método para actualizar datos de usuario
  @POST("MyServlet")
  Call<MyData> updateUser(@Body User user); // Por ahora nada 

  // Método para listar películas
  @GET("MyServlet")
  Call<DataMovies> movies(@Query("ACTION") String action);
}
