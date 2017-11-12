package com.example.danilo.danilo_pfc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.danilo.danilo_pfc.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Locale;

import static com.example.danilo.danilo_pfc.Utils.Util.GEN_PAR_HABILITAR_BD;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    /*Sintese de voz*/
    private TextToSpeech tts;
	/*Final sintese de voz*/

    private int MEU_REQUEST_CODE = 0;
    private ImageButton imageButtonNext;
    private ImageButton imageButtonFala;
    private boolean boolAparelhoSuportaReconhecimentoDeVoz;


    GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Condição abaixo para integração com banco de dados
         */
        if(GEN_PAR_HABILITAR_BD == true)
        {
            
        }
        else{

        }
        /**
         * Fim da condição de integração com banco de dados
         */

        imageButtonFala = (ImageButton) findViewById(R.id.btn_fala);
        imageButtonNext = (ImageButton) findViewById(R.id.btn_next);


        imageButtonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }


        });


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        boolAparelhoSuportaReconhecimentoDeVoz = verificandoSeOAparelhoCelularSuportaReconhecimentoDeVoz();
        /*Sintese de Voz*/
        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.getDefault());

        imageButtonFala = (ImageButton) findViewById(R.id.btn_fala);
        imageButtonFala.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                pronunciarTexto("Olá, seja bem-vindo ao, A,B,C. Toque no botão abaixo para iniciar.");//PRONUCIARIA O TEXTO

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

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
