package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;

public class ModelExample extends BaseModel {

    public String nome;
    public int idade;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
