package com.example.danilo.danilo_pfc.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.danilo.danilo_pfc.R;
import com.example.danilo.danilo_pfc.model.ApplicationJson;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import static com.example.danilo.danilo_pfc.Utils.Util.ID_LETRA_SELECIONADA;
import static com.example.danilo.danilo_pfc.Utils.Util.getProperty;

public class ExercLetrasActivity extends AppCompatActivity implements TextToSpeech.OnInitListener
{
    private int idLetra;

    /*Sintese de voz*/
    private TextToSpeech tts;
	/*Final sintese de voz*/

    private int MEU_REQUEST_CODE = 0;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerc_letras);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_exerc_letras);
        TextView letraTW = (TextView) findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        idLetra = extras.getInt(ID_LETRA_SELECIONADA);
        String json = getProperty(getApplicationContext(),idLetra);

        final ApplicationJson applicationJson;
        applicationJson = new Gson().fromJson(json,ApplicationJson.class);

        letraTW.setText("Repita a pronúnica         da letra"+ applicationJson.getLetraMaiuscula()+":");

        int imageResource = getResources().getIdentifier(applicationJson.getBackgroundExercLetras(), null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        relativeLayout.setBackground(res);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageButton btnsintese = (ImageButton) findViewById(R.id.btn_opt);
        /*Sintese de Voz*/
        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.getDefault());

        btnsintese.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                pronunciarTexto(applicationJson.getProExerLetras());//PRONUCIARIA O TEXTO

            }
        });

    }



    public void reconhecimentoDeVozFicaEscutandoUsuarioPronunciarParaOReconhecimento(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now! (Fale Agora)");
        startActivityForResult(intent, MEU_REQUEST_CODE);
    }

    public boolean verificandoSeOAparelhoCelularSuportaReconhecimentoDeVoz(){
        String strTexto = "";
        boolean boolAparelhoSuportaReconhecimentoDeVoz = false;
        PackageManager pm = getPackageManager();
        Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        List<ResolveInfo> activities =
                pm.queryIntentActivities(it, 0);

        if (activities.size() != 0) {
            strTexto = "Seu aparelho têm suporte ao reconhecimento de voz.\nReconhecimento de Voz ativado!";
            boolAparelhoSuportaReconhecimentoDeVoz = true;

        } else {
            strTexto = "Seu aparelho não têm suporte ao reconhecimento de voz.\nReconhecimento de Voz desativado!";
            boolAparelhoSuportaReconhecimentoDeVoz = false;
        }

        return boolAparelhoSuportaReconhecimentoDeVoz;

    }


    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            //int result = tts.setLanguage(Locale.US);
            int result = tts.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Log.e("TTS", "This Language is not supported");
                //msgDeAlertaAoUsuario("msg","Linguagem não suportada");
            } else {
                //se eu ativar essa sintese, toda vez que roda o celular vai falar o resultado
                //ativarSinteseDeVoz();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    public void apenasPronunciarTexto(String text){
        pronunciarTexto(text);
    }
    //Serve para falar o que está escrito para o usuário
    private void ativarSinteseDeVoz() {
        // String text = editTextAreaExpressao.getText().toString() + "";
        //Caso estiver ativado a síntese de Voz
        String text = "OLÁ JOVEM";
        pronunciarTexto(text);
    }

    /*Verdadeiro */

    public void pronunciarTexto(String strTexto){
        if(strTexto.length() != 0){
            tts.speak(strTexto, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    public void resolverEFalarResultado(){
        ativarSinteseDeVoz();
    }

}
