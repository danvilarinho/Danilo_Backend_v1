package com.example.danilo.danilo_pfc.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.example.danilo.danilo_pfc.dataBase.CriarBanco;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import static com.example.danilo.danilo_pfc.Utils.Util.JSON_LETRAS;
import static com.example.danilo.danilo_pfc.Utils.Util.TABELA_LETRAS;

/**
 * Created by Danilo on 11/11/2017.
 */

public class Controller
{
    private SQLiteDatabase db;
    private CriarBanco banco;
    private static int QUANTITY = 26;

    public Controller(Context context){
        banco = new CriarBanco(context);
    }


    public long gravarTabelaLetra(String jsonLetra){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(JSON_LETRAS, jsonLetra);


        resultado = db.insert(TABELA_LETRAS, null, valores);
        db.close();

        return resultado;
    }

    public long populateTableLetra()
    {

        Properties props=new Properties();
        InputStream inputStream =
                this.getClass().getClassLoader().getResourceAsStream("/app/src/main/java/LetrasJson.properties");
        try {
            props.load(inputStream);
        } catch (IOException e) {
            return -5;//Caso nao consiga achar properties
        }
        String jsonLetra;
        long result = 0L;
        for(int i = 0; i < QUANTITY;i++)
        {
            result = 0L;
            jsonLetra = props.getProperty(Integer.toString(i));
            result = gravarTabelaLetra(jsonLetra);
            if (result == -1){
                return result;
            }
        }
        return result;
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {JSON_LETRAS};
        db = banco.getReadableDatabase();
        cursor = db.query(TABELA_LETRAS, campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
