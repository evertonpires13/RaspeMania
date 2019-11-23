package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import java.io.Serializable;

public class BaseModel implements Serializable {

    public String chave;
    public long dataCadastro;
    public long status;                 //1 ativo or 2 inativo
    public String observacao;

}
