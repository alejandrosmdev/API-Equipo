package com.example.loginandroid_29_09_2023.lstMov.data;

import com.example.loginandroid_29_09_2023.beans.Pelicula;
import java.util.List;

public class DataMovies {
    private List<Pelicula> lstPeliculas; // Lista de películas
    private String message; // Mensaje adicional, si lo necesitas

    // Constructor
    public DataMovies(List<Pelicula> lstPeliculas, String message) {
        this.lstPeliculas = lstPeliculas;
        this.message = message;
    }

    // Getters
    public List<Pelicula> getLstPeliculas() {
        return lstPeliculas;
    }

    public String getMessage() {
        return message;
    }

    // Puedes añadir más métodos según sea necesario
}

