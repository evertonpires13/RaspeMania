package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;

public class Produto extends BaseModel {

    public String nome;
    public float valor;

    public Produto(){}

    public Produto(String nome, float valor){
        this.nome = nome;
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
