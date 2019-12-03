package br.com.raspemania.model.entidade;

import androidx.annotation.NonNull;

import java.io.Serializable;

import br.com.raspemania.helper.ConstantHelper;
import br.com.raspemania.model.BaseModel;

public class Colaborador extends BaseModel implements Serializable {


    public String senha;
    public String email;
    public String apelido;
    public String uid;

    /**
     * @see ConstantHelper
     */
    public long perfil;

    @NonNull
    @Override
    public String toString() {
        return apelido;
    }
}
