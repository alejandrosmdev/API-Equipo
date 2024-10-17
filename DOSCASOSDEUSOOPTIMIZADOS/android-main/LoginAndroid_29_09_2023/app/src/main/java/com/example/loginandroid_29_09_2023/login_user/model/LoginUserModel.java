package com.example.loginandroid_29_09_2023.login_user.model;

import android.util.Log;

import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.data.MyData;
import com.example.loginandroid_29_09_2023.utils.ApiService;
import com.example.loginandroid_29_09_2023.utils.RetrofitCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements ContractLoginUser.Model {
    private static final String IP_BASE = "192.168.1.9:3000"; // Dirección base de la API

    @Override
    public void loginAPI(User user, final OnLoginUserListener onLoginUserListener) {
        // Se crea el servicio API a partir del cliente Retrofit
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/").create(ApiService.class);

        // Se crea un nuevo objeto User para la llamada a la API
        User loginUser = new User();
        loginUser.setEmail(user.getEmail()); // Establece el email del usuario
        loginUser.setPassword(user.getPassword()); // Establece la contraseña del usuario

        // Se realiza la llamada al método de inicio de sesión de la API
        Call<MyData> call = apiService.login(loginUser);

        // Se maneja la respuesta asíncrona de la API
        call.enqueue(new Callback<MyData>() {
            @Override
            public void onResponse(Call<MyData> call, Response<MyData> response) {
                // Se verifica si la respuesta fue exitosa
                if (response.isSuccessful()) {
                    MyData myData = response.body(); // Se obtiene el cuerpo de la respuesta
                    if (myData != null && myData.getUser() != null) {
                        // Usuario autenticado correctamente
                        onLoginUserListener.onFinished(myData.getUser());
                    } else {
                        // Respuesta válida, pero sin usuario
                        onLoginUserListener.onFailure("No se encontró el usuario en la respuesta");
                    }
                } else {
                    // Se manejan los errores HTTP
                    handleHttpError(response.code(), onLoginUserListener);
                }
            }

            @Override
            public void onFailure(Call<MyData> call, Throwable t) {
                // Se manejan los errores en la llamada
                handleNetworkError(t, onLoginUserListener);
            }
        });
    }

    // Método para manejar errores HTTP
    private void handleHttpError(int code, OnLoginUserListener listener) {
        switch (code) {
            case 400:
                listener.onFailure("Solicitud incorrecta: Formato de email o contraseña inválido");
                break;
            case 401:
                listener.onFailure("No autorizado: Email o contraseña incorrectos");
                break;
            case 404:
                listener.onFailure("No encontrado: Endpoint no disponible");
                break;
            case 500:
                listener.onFailure("Error interno del servidor: Por favor, inténtelo más tarde");
                break;
            default:
                listener.onFailure("Error inesperado: Código de estado " + code);
                break;
        }
    }

    // Método para manejar errores de red
    private void handleNetworkError(Throwable t, OnLoginUserListener listener) {
        if (t instanceof java.net.SocketTimeoutException) {
            listener.onFailure("Error: Tiempo de espera agotado. Verifique su conexión.");
        } else if (t instanceof java.net.UnknownHostException) {
            listener.onFailure("Error: No se puede conectar al servidor. Verifique su conexión a Internet.");
        } else {
            listener.onFailure("Error: " + t.getMessage());
        }
    }
}
