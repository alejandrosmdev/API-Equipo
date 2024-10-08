package com.example.loginandroid_29_09_2023.updateMovie.model.data;

import com.example.loginandroid_29_09_2023.beans.Pelicula;

import java.util.ArrayList;

public class MyData {
    private String message;

    private ArrayList<Pelicula> lstMovies;


    public String getMessage() {
        return message;
    }
    public ArrayList<Pelicula> getLstUsers() {
        return lstMovies;
    }
    public void setLstUsers(ArrayList<Pelicula> lstUsers) {
        this.lstMovies = lstUsers;
    }
}

