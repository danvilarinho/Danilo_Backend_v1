package com.example.danilo.danilo_pfc.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.danilo.danilo_pfc.Utils.Util.JSON_LETRAS;
import static com.example.danilo.danilo_pfc.Utils.Util.TABELA_LETRAS;

/**
 * Created by Danilo on 02/09/17.
 */

public class CriarBanco extends SQLiteOpenHelper
{
    private static final String NOME_BANCO = "banco276.db";
    private static final String ID = "_id";
    private static final int VERSAO = 1;

    public CriarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE"+TABELA_LETRAS+"("
                + ID + "integer primary key autoincrement,"
                + JSON_LETRAS + "text"
                +")";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA_LETRAS);
        onCreate(db);
    }

}
