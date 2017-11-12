package com.example.danilo.danilo_pfc.controllers;


import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.example.danilo.danilo_pfc.dataBase.CriarBanco;


import static com.example.danilo.danilo_pfc.Utils.Util.JSON_LETRAS;
import static com.example.danilo.danilo_pfc.Utils.Util.QUANTITY;
import static com.example.danilo.danilo_pfc.Utils.Util.TABELA_LETRAS;
import static com.example.danilo.danilo_pfc.Utils.Util.getProperty;

/**
 * Created by Danilo on 11/11/2017.
 */

/**
 * @class Classe de controle de acesso ao banco de dados.
*/
public class Controller
{
    private SQLiteDatabase db;
    private CriarBanco banco;


    public Controller(Context context){
        banco = new CriarBanco(context);
    }

    /**
     * Método responsavel por gravar dados no banco de dados
     * @param jsonLetra - json representando classe ApplicationJson
     * @return quantidade de linhas inserida, em caso de erro @return será -1
     */
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

    /**
     * Metodo responsavel por popular tabela Letra do Banco de dados
     * @param context context aplication
     * @return retorna -1 em caso de falha e 0 em caso de sucesso.
     */
    public long populateTableLetra(Context context)
    {
        String jsonLetra;
        long result = 0L;
        for(int i = 0; i < QUANTITY;i++)
        {
            result = 0L;
            jsonLetra = getProperty(context,i);
            result = gravarTabelaLetra(jsonLetra);
            if (result == -1){
                return result;
            }
        }
        return 0;
    }

    /**
     * Método realiza uma listagem do banco de dados.
     * @return um Cursor, contendo dados (Json)
     */
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
