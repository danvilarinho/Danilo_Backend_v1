package com.example.danilo.danilo_pfc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.example.danilo.danilo_pfc.R;

import java.util.List;
import java.util.Locale;
/**
 * Created by Danilo on 03/01/2017.
 */



public class MenuActivity extends Activity implements TextToSpeech.OnInitListener {

    private ImageButton imageButtonBack;
    private ImageButton imageButtonNext;
    private ImageButton imageButtonOpt;
    private ImageButton imageButtonLetras;
    private ImageButton imageButtonSilabas;
    private ImageButton imageButtonSentencas;

    final public int LETRAS = 0, SILABAS = 1, SENTENCAS = 2;

    private RadioGroup radioGroupOpt;
    private RadioButton radioButtonLetras;
    private RadioButton radioButtonSilab;
    private RadioButton radioButtonSent;

    /*Sintese de voz*/
    private TextToSpeech tts;
	/*Final sintese de voz*/

    private int MEU_REQUEST_CODE = 0;
    private boolean boolAparelhoSuportaReconhecimentoDeVoz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        radioGroupOpt = (RadioGroup) findViewById(R.id.rg_opt);
        radioButtonLetras = (RadioButton)findViewById(R.id.rb_letras);
        radioButtonSilab = (RadioButton) findViewById(R.id.rb_silab);
        radioButtonSent = (RadioButton) findViewById(R.id.rb_sent);

        imageButtonBack = (ImageButton) findViewById(R.id.btn_back);
        imageButtonNext = (ImageButton) findViewById(R.id.btn_next);
        imageButtonOpt = (ImageButton) findViewById(R.id.btn_opt);
        imageButtonLetras = (ImageButton) findViewById(R.id.btn_letras);
        imageButtonSilabas = (ImageButton) findViewById(R.id.btn_silabas);
        imageButtonSentencas = (ImageButton) findViewById(R.id.btn_sentencas);


        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                    finish();
                }
            });

        imageButtonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, IconesActivity.class);

                if(radioGroupOpt.getCheckedRadioButtonId() == radioButtonLetras.getId()){

                    intent.putExtra("opt", "LETRAS");

                } else if(radioGroupOpt.getCheckedRadioButtonId() == radioButtonSilab.getId()){

                    intent.putExtra("opt", "SILABAS");

                } else if(radioGroupOpt.getCheckedRadioButtonId() == radioButtonSent.getId()){

                    intent.putExtra("opt", "SENTENCAS");

                }

                startActivity(intent);


            }


        });

        /*Sintese de Voz*/
        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.getDefault());

        imageButtonOpt = (ImageButton) findViewById(R.id.btn_opt);
        imageButtonOpt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                pronunciarTexto("Selecione uma das opções a seguir e clique no botão, para avançar.");//PRONUCIARIA O TEXTO

            }
        });

        imageButtonLetras = (ImageButton) findViewById(R.id.btn_letras);
        imageButtonLetras.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                pronunciarTexto("Vamos aprender sobre as letras.");//PRONUCIARIA O TEXTO

            }
        });

        imageButtonSilabas = (ImageButton) findViewById(R.id.btn_silabas);
        imageButtonSilabas.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                pronunciarTexto("Vamos aprender sobre as sílabas.");//PRONUCIARIA O TEXTO

            }
        });

        imageButtonSentencas = (ImageButton) findViewById(R.id.btn_sentencas);
        imageButtonSentencas.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                pronunciarTexto("Vamos aprender sobre as sentenças ou frases.");//PRONUCIARIA O TEXTO

            }
        });
        /*Sintese de Voz*/


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
