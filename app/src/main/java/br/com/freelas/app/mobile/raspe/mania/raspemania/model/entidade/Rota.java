package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;

public class Rota extends BaseModel {

    public String nome;
    public Colaborador colaborador;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
