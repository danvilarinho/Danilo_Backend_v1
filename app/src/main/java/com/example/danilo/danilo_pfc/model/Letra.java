package com.example.danilo.danilo_pfc.model;

import android.graphics.Bitmap;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by Danilo on 26/02/17.
 */


public class Letra {

    private String letraMinuscula;
    private String letraMaiuscula;

    public Letra() {
    }

    public Letra(String letraMinuscula, String letraMaiuscula) {
        this.letraMinuscula = letraMinuscula;
        this.letraMaiuscula = letraMaiuscula;
    }

    public String getLetraMinuscula() {
        return letraMinuscula;
    }

    public void setLetraMinuscula(String letraMinuscula) {
        this.letraMinuscula = letraMinuscula;
    }

    public String getLetraMaiuscula() {
        return letraMaiuscula;
    }

    public void setLetraMaiuscula(String letraMaiuscula) {
        this.letraMaiuscula = letraMaiuscula;
    }
}
