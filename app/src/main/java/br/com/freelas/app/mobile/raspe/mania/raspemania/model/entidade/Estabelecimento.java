package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

import java.io.Serializable;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;

public class Estabelecimento extends BaseModel implements Serializable {

    public int estoque;
    public String endereco;
    public String codigo;
    public int porcentagem;                     //valor de comissão do lugar
    public Rota rota;

    @NonNull
    @Override
    public String toString() {
        return codigo;
    }
}
