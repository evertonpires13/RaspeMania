package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {

    public String chave;
    public long status;                 //1 ativo or 2 inativo
    public String observacao;

    @ServerTimestamp
    public Date dataUltimaAtualizacao;

}
