package com.example.loginandroid_29_09_2023.login_user.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid_29_09_2023.MainActivity;
import com.example.loginandroid_29_09_2023.R;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.presenter.LoginUserPresenter;
import com.example.loginandroid_29_09_2023.lstMov.view.LstMovies;

public class LoginUserM extends AppCompatActivity implements ContractLoginUser.View {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private LoginUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_m);
        presenter = new LoginUserPresenter(this);
        initComponents();
    }

    private void initComponents() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(edtEmail.getText().toString(), edtPassword.getText().toString());
                presenter.login(user);
            }
        });
    }

    @Override
    public void successLogin(User user) {
        Toast.makeText(this, "Login successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
        Intent pasaraMovies = new Intent(getBaseContext(), LstMovies.class);
        startActivity(pasaraMovies);
    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(this, "Login failed: " + err, Toast.LENGTH_SHORT).show();
    }
}