package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.old_entidade.Local;

public class LocalService extends GenericService<Local> {

    /*--------------------------------------------------------------------------------------------*/
    public LocalService(GenericInterface genericInterface) {

        super(CollectionHelper.COLLECTION_ESTABELECIMENTO, Local.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
