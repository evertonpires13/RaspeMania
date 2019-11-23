package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class RotaService extends GenericService<Rota> {

    /*--------------------------------------------------------------------------------------------*/
    public RotaService(GenericInterface genericInterface) {

        super(GenericNode.NODE_ROTA, Rota.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
