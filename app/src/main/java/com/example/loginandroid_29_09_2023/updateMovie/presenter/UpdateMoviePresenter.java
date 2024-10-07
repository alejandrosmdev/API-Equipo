package com.example.loginandroid_29_09_2023.updateMovie.presenter;

import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.updateMovie.ContractUpdateMovie;
import com.example.loginandroid_29_09_2023.updateMovie.model.LoginUserModel;

public class UpdateMoviePresenter implements ContractUpdateMovie.Presenter, ContractUpdateMovie.Model.OnLoginUserListener {

    private ContractUpdateMovie.View view;
    private ContractUpdateMovie.Model model;

    public UpdateMoviePresenter(ContractUpdateMovie.View view){
        this.view = view;
        model = new LoginUserModel(this);
    }
    @Override
    public void changeName(Pelicula pelicula) {
        model.updateAPI(pelicula, this);
    }

    @Override
    public void onFinished(Pelicula pelicula) {
        view.successUpdate(pelicula);
    }

    @Override
    public void onFailure(String err) {

    }
}
