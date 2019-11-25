package br.com.freelas.app.mobile.raspe.mania.raspemania.model.old_entidade;

import androidx.annotation.NonNull;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class Local extends BaseModel {

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
