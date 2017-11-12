package com.example.danilo.danilo_pfc.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.danilo.danilo_pfc.R;

/**
 * Created by Danilo on 23/01/2017.
 */

public class SilabasActivity extends AppCompatActivity {

    private int idLetra;
    private String opt;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silabas);


        /*setIdLetra(getIntent().getIntExtra("id", 1));

        ApplicationJson letra = ApplicationJson.findById(ApplicationJson.class, getIdLetra());

        Bitmap bitmap = BitmapFactory.decodeByteArray(letra.getTelaSilaba(), 0, letra.getTelaSilaba().length);


        BitmapDrawable bg = new BitmapDrawable(getResources(), bitmap);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_letras);
        layout.setBackground(bg);
*/

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

                Intent intent = new Intent(SilabasActivity.this, ExercSilabasActivity.class);
//                intent.putExtra("letra", getIdLetra());
                startActivity(intent);
            }
        });



    }

    public int getIdLetra() {
        return idLetra;
    }

    public void setIdLetra(int idLetra) {
        this.idLetra = idLetra;
    }
}
