package com.example.danilo.danilo_pfc.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Danilo on 12/11/2017.
 */

public class Util
{
    public static final String ID_LETRA_SELECIONADA = "letraSelecionada";
    public static final String JSON_LETRAS = "jsonLetras";
    public static final String TABELA_LETRAS = "letras";
    public static final boolean GEN_PAR_HABILITAR_BD = false;

    public static String getProperty(Context context,int key)
    {
        String keyValue = Integer.toString(key);
        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try
        {
            inputStream = assetManager.open("LetrasJson.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao tentar acessar arquivo de configuração.";//error case
        }
        return properties.getProperty(keyValue);
    }

}
