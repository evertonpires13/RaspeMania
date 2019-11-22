package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.firebase;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericFirebase;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model.LocalModel;

public class LocalFirebase extends GenericFirebase<LocalModel> {

    /*--------------------------------------------------------------------------------------------*/
    public LocalFirebase(GenericInterface genericInterface) {

        super(GenericNode.NODE_LOCAL, LocalModel.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
