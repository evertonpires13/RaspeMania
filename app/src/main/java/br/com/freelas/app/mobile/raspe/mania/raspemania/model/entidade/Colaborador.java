package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import androidx.annotation.NonNull;

import java.io.Serializable;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;

public class Colaborador extends BaseModel implements Serializable {

    public String nome;
    public String apelido;
    public String senha;
    public long perfil;                     //1 adm or 2 colaborador

    @NonNull
    @Override
    public String toString() {
        return apelido;
    }
}
