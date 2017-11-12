package com.example.danilo.danilo_pfc.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.danilo.danilo_pfc.R;
import com.example.danilo.danilo_pfc.model.Letra;
import com.google.gson.Gson;

import static com.example.danilo.danilo_pfc.Utils.Util.ID_LETRA_SELECIONADA;
import static com.example.danilo.danilo_pfc.Utils.Util.getProperty;

public class ExercLetrasActivity extends AppCompatActivity
{
    private int idLetra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerc_letras);

        TextView letraTW = (TextView) findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        idLetra = extras.getInt(ID_LETRA_SELECIONADA);
        String json = getProperty(getApplicationContext(),idLetra);

        Letra letra = new Gson().fromJson(json,Letra.class);
        letraTW.setText("Repita a pronúnica         da letra "+letra.getLetraMaiuscula()+":");

        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
