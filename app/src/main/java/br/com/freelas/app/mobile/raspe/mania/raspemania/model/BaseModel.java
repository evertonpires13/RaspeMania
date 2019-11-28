package br.com.freelas.app.mobile.raspe.mania.raspemania.model;

import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {

    public String key;
    public long status;                 //1 ativo or 2 inativo
    public String observacao;

    @ServerTimestamp
    public Date dataUltimaAtualizacao;

    //TODO apagar isso
    @Deprecated
    public String chave;

}
