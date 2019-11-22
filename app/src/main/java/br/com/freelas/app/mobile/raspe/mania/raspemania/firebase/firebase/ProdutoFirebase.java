package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.firebase;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericFirebase;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model.ProdutoModel;

public class ProdutoFirebase extends GenericFirebase<ProdutoModel> {

    /*--------------------------------------------------------------------------------------------*/
    public ProdutoFirebase(GenericInterface genericInterface) {

        super(GenericNode.NODE_COLABORADOR, ProdutoModel.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
