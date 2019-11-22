package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model;

import androidx.annotation.NonNull;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericModel;

public class ProdutoModel  extends GenericModel {

    public String nome;
    public String nomeConhecido;
    public Integer funcao;
    public String telefone;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
