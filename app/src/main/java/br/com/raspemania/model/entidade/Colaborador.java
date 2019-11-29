package br.com.raspemania.model.entidade;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

import br.com.raspemania.helper.ConstantHelper;
import br.com.raspemania.model.BaseModel;

public class Colaborador extends BaseModel implements Serializable {


    public FirebaseUser user;               //referencia para o login //not implemented
    public String nome;
    public String apelido;

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
