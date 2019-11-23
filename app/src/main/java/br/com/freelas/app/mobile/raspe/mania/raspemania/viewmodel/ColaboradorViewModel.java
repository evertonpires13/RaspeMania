package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;

public class ColaboradorViewModel extends GenericService<Colaborador> {

    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorViewModel(GenericInterface genericInterface) {

        super(GenericNode.NODE_COLABORADOR, Colaborador.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
