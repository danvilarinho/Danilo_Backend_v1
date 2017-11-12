package com.example.danilo.danilo_pfc.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danilo.danilo_pfc.R;
import com.example.danilo.danilo_pfc.model.Letra;
import com.google.gson.Gson;

import static com.example.danilo.danilo_pfc.Utils.Util.ID_LETRA_SELECIONADA;
import static com.example.danilo.danilo_pfc.Utils.Util.getProperty;

/**
 * Created by Danilo on 23/01/2017.
 */

public class LetrasActivity extends AppCompatActivity {

    private int idLetra;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letras);
        TextView letraMinuscula = (TextView) findViewById(R.id.letra_minuscula);
        TextView letraMaiuscula = (TextView) findViewById(R.id.letra_maiuscula);

        Bundle extras = getIntent().getExtras();
        idLetra = extras.getInt(ID_LETRA_SELECIONADA);
        String json = getProperty(getApplicationContext(),idLetra);

        Letra letra = new Gson().fromJson(json,Letra.class);
        letraMaiuscula.setText(letra.getLetraMaiuscula());
        letraMinuscula.setText(letra.getLetraMinuscula());

        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ImageButton btnExercicios = (ImageButton) findViewById(R.id.btn_exercicios);
        btnExercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LetrasActivity.this, ExercLetrasActivity.class);
                intent.putExtra(ID_LETRA_SELECIONADA, idLetra);
                startActivity(intent);
            }
        });

    }

}
