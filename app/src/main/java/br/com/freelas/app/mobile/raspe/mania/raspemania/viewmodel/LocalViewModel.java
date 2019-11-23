package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Local;

public class LocalViewModel extends GenericService<Local> {

    /*--------------------------------------------------------------------------------------------*/
    public LocalViewModel(GenericInterface genericInterface) {

        super(GenericNode.NODE_LOCAL, Local.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
