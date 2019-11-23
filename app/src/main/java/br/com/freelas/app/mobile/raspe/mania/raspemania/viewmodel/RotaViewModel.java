package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class RotaViewModel extends GenericService<Rota> {

    /*--------------------------------------------------------------------------------------------*/
    public RotaViewModel(GenericInterface genericInterface) {

        super(GenericNode.NODE_ROTA, Rota.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
