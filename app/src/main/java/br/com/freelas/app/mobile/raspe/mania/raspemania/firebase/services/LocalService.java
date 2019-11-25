package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Local;

public class LocalService extends GenericService<Local> {

    /*--------------------------------------------------------------------------------------------*/
    public LocalService(GenericInterface genericInterface) {

        super(CollectionHelper.COLLECTION_LOCAL, Local.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
