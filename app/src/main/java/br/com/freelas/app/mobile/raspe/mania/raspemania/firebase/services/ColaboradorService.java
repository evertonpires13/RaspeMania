package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;

public class ColaboradorService extends GenericService<Colaborador> {

    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorService(GenericInterface genericInterface) {

        super(GenericNode.NODE_COLABORADOR, Colaborador.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
