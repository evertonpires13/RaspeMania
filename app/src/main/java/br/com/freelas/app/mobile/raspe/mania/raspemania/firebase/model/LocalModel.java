package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model;

import androidx.annotation.NonNull;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericModel;

public class LocalModel  extends GenericModel {

    public String nome;

    public RotaModel rota;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
