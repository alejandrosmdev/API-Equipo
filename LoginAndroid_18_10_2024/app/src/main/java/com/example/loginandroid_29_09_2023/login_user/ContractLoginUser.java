package com.example.loginandroid_29_09_2023.login_user;

import com.example.loginandroid_29_09_2023.beans.User;

public interface ContractLoginUser {
    interface View {
        void successLogin(User user);
        void failureLogin(String err);
    }

    interface Presenter {
        void login(User user);
    }

    interface Model {
        interface OnLoginUserListener {
            void onFinished(User user);
            void onFailure(String err);
        }

        void loginAPI(User user, OnLoginUserListener onLoginUserListener);
    }
}