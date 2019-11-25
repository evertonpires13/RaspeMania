package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class RotaService extends GenericService<Rota> {

    /*--------------------------------------------------------------------------------------------*/
    public RotaService(GenericInterface genericInterface) {

        super(CollectionHelper.COLLECTION_ROTA, Rota.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
