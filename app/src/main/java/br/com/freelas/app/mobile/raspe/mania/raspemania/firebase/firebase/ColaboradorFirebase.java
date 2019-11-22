package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.firebase;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericFirebase;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model.ColaboradorModel;

public class ColaboradorFirebase extends GenericFirebase<ColaboradorModel> {

    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorFirebase(GenericInterface genericInterface) {

        super(GenericNode.NODE_COLABORADOR, ColaboradorModel.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
