package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;

public class ColaboradorService extends GenericService<Colaborador> {

    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorService(GenericInterface genericInterface) {

        super(CollectionHelper.COLLECTION_COLABORADOR, Colaborador.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
