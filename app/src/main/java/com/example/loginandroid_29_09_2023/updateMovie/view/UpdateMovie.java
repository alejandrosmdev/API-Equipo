package com.example.loginandroid_29_09_2023.updateMovie.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid_29_09_2023.R;
import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.updateMovie.ContractUpdateMovie;
import com.example.loginandroid_29_09_2023.updateMovie.presenter.UpdateMoviePresenter;

public class UpdateMovie extends AppCompatActivity implements ContractUpdateMovie.View{

    private EditText edtMovieId;
    private EditText edtNewMovieName;
    private Button btnChangeMovieName;

    private UpdateMoviePresenter presenter =
            new UpdateMoviePresenter(this);

    /* PATRÓN SINGLETON*/
    private static UpdateMovie mainActivity = null;
    public static UpdateMovie getInstance(){
        return mainActivity; //0x457845AF
    }
    /* FIN PATRÓN SINGLETON*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_m);
        mainActivity = this;
        initComponents();

    }
    private void initComponents(){
        edtMovieId = findViewById(R.id.edtMovieId);
        edtNewMovieName = findViewById(R.id.edtNewMovieName);
        btnChangeMovieName = findViewById(R.id.btnChangeMovieName);
        btnChangeMovieName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
                //sPeliculas.getDatosPeliculas();
                Pelicula pelicula = new Pelicula();
                pelicula.setId(Integer.parseInt(edtMovieId.getText().toString()));
                pelicula.setTitulo(edtNewMovieName.getText().toString());
                presenter.changeName(pelicula);
            }
        });
    }


    @Override
    public void successUpdate(Pelicula pelicula) {
        Toast.makeText(mainActivity, pelicula.getTitulo(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureLogin(String err) {

    }
}