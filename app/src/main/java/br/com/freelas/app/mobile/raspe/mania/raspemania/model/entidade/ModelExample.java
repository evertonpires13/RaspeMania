package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

public class ModelExample extends BaseModel {

    public String nome;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
