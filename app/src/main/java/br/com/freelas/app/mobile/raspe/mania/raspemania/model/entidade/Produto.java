package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

public class Produto extends BaseModel {

    public String nome;
    public long valor;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
