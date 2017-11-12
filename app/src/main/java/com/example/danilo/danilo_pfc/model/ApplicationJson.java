package com.example.danilo.danilo_pfc.model;

import android.graphics.Bitmap;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by Danilo on 26/02/17.
 */

/**
 * @class parse Json <-> Class
 * Qualquer novo nó adicionado no Json tem que ser criado aqui,
 * com mesmo name.
 * Não Esquecer dos get and settings e de adicionar o mesmo no contrutor da classe.
 */
public class ApplicationJson {

    private String letraMinuscula;
    private String letraMaiuscula;
    private String backgroundExercLetras;

    /**
     * Para uma melhor identificação/diferenciação o nome desse metódo ficou assim.
     * btn -> representa um botão
     * M -> representa uma letra MAIUSCULA
     * TL-> tela letras - LetasActivity
     */
    private String btnMTL;

    /**
     * Para uma melhor identificação/diferenciação o nome desse metódo ficou assim.
     * btn -> representa um botão
     * m -> representa uma letra minuscula
     * TL-> tela letras - LetasActivity
     */
    private String btnmTL;
    private String proExerLetras; //Pronuncia para ser executada na tela de exercicios.

    public ApplicationJson() {
    }

    public ApplicationJson(String letraMinuscula, String letraMaiuscula, String backgroundExercLetras, String btnMTL, String btnmTL, String proExerLetras) {
        this.letraMinuscula = letraMinuscula;
        this.letraMaiuscula = letraMaiuscula;
        this.backgroundExercLetras = backgroundExercLetras;
        this.btnMTL = btnMTL;
        this.btnmTL = btnmTL;
        this.proExerLetras = proExerLetras;
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

    public String getBackgroundExercLetras() { return backgroundExercLetras; }

    public void setBackgroundExercLetras(String backgroundExercLetras) {
        this.backgroundExercLetras = backgroundExercLetras;
    }

    public String getBtnMTL() {
        return btnMTL;
    }

    public void setBtnMTL(String btnMTL) {
        this.btnMTL = btnMTL;
    }

    public String getBtnmTL() {
        return btnmTL;
    }

    public void setBtnmTL(String btnmTL) {
        this.btnmTL = btnmTL;
    }

    public String getProExerLetras() {
        return proExerLetras;
    }

    public void setProExerLetras(String proExerLetras) {
        this.proExerLetras = proExerLetras;
    }
}
