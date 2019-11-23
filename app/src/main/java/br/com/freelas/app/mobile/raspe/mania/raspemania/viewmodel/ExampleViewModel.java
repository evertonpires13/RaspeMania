package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ExampleService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;

public class ExampleViewModel extends BaseViewModel implements GenericInterface {

    ExampleService service = new ExampleService(this);

    public void save(ModelExample model){
        service.save(model);
    }

    @Override
    public void sucessWindow(String node) {

    }

    @Override
    public void sucessList(String node) {

    }

    @Override
    public void error(String mensagem) {

    }
}
