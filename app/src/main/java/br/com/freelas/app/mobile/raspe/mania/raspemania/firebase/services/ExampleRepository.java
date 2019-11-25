package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;

public class ExampleRepository extends FirebaseRepository<ModelExample> {

    public ExampleRepository() {
        super(CollectionHelper.COLLECTION_EXAMPLE, ModelExample.class);
    }

}
