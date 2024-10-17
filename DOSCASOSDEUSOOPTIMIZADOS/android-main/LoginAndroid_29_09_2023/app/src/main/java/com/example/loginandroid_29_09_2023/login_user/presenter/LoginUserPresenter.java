package com.example.loginandroid_29_09_2023.login_user.presenter;

import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.model.LoginUserModel;

public class LoginUserPresenter implements ContractLoginUser.Presenter, ContractLoginUser.Model.OnLoginUserListener {
    private ContractLoginUser.View view;
    private ContractLoginUser.Model model;

    public LoginUserPresenter(ContractLoginUser.View view) {
        this.view = view;
        model = new LoginUserModel();
    }

    @Override
    public void login(User user) {
        if (isValidInput(user)) {
            model.loginAPI(user, this);
        } else {
            view.failureLogin("Invalid email or password");
        }
    }

    private boolean isValidInput(User user) {
        return user != null && user.getEmail() != null && !user.getEmail().isEmpty()
                && user.getPassword() != null && !user.getPassword().isEmpty();
    }

    @Override
    public void onFinished(User user) {
        view.successLogin(user);
    }

    @Override
    public void onFailure(String error) {
        view.failureLogin(error);
    }
}