package com.example.loginandroid_29_09_2023.updateMovie;

import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.beans.User;

public interface ContractUpdateMovie {
    public interface View{
        public void successUpdate(Pelicula pelicula);
        void failureLogin(String err);
        // void failureLogin(MyException err);
    }
    public interface Presenter{
        // void login(String email, String pass);
        void changeName(Pelicula pelicula);
        // void login(ViewUser viewUser);
        // VIEW-ORM
        // BEANS-ENTITIES
        // MVP - MVVM
    }
    public interface Model{
        interface OnLoginUserListener{
            void onFinished(Pelicula pelicula);
            void onFailure(String err);
        }
        void updateAPI(Pelicula pelicula,
                      OnLoginUserListener onLoginUserListener);
    }
}
