package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;

public class Produto extends BaseModel implements Serializable {

    public String nome;
    public float valor;

    public Produto(){}

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

}
