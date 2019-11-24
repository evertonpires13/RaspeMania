package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ExampleService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;

public class ExampleViewModel extends BaseViewModel implements GenericInterface {

    ExampleService service = new ExampleService();



    @Override
    public void sucessWindow(String node) {
        //TODO fazer algo
    }

    @Override
    public void sucessList(String node) {
        //TODO fazer algo
    }

    @Override
    public void error(String mensagem) {
        //TODO fazer algo
    }
}
