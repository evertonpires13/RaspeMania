package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.firebase;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericFirebase;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model.RotaModel;

public class RotaFirebase extends GenericFirebase<RotaModel> {

    /*--------------------------------------------------------------------------------------------*/
    public RotaFirebase(GenericInterface genericInterface) {

        super(GenericNode.NODE_ROTA, RotaModel.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
