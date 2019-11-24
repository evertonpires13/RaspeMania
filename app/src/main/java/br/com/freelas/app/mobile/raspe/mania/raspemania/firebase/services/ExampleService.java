package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericNode;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class ExampleService extends FirebaseRepository<ModelExample> {

    /*--------------------------------------------------------------------------------------------*/
    public ExampleService() {

        super(GenericNode.NODE_EXAMPLE, ModelExample.class);

    }
    /*--------------------------------------------------------------------------------------------*/
}
