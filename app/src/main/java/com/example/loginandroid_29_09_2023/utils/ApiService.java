package com.example.loginandroid_29_09_2023.utils;

import com.example.loginandroid_29_09_2023.login_user.model.data.MyData;
import com.example.loginandroid_29_09_2023.lstMov.data.DataMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

  public static final String URL = "http://192.168.43.180:8080/untitled_war_exploded/";

  @Headers({
          "Accept: application/json",
          "Content-Type: application/json"
  })

  // Método para iniciar sesión
  @FormUrlEncoded
  @POST("MyServlet")
  Call<MyData> login(@Field("ACTION") String action,
                     @Field("EMAIL") String email,
                     @Field("PASSWORD") String password);

  // Método para actualizar datos de usuario
  @FormUrlEncoded
  @POST("MyServlet")
  Call<MyData> updateUser(@Field("ACTION") String action,
                          @Field("EMAIL") String email,
                          @Field("NEW_PASSWORD") String newPassword);//El de ALbertillo

  // Método para listar películas
  @GET("MyServlet")
  Call<List<DataMovies>> listMovies(@Query("ACTION") String action);
}