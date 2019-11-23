package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.RotaService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class RotaViewModel extends BaseViewModel implements GenericInterface {

    RotaService service = new RotaService(this);
    private Context context;

    public RotaViewModel() {

    }

    public RotaViewModel(Context context) {
        this.context = context;
    }

    public void save(Rota model) {
        service.save(model);
    }

    @Override
    public void sucessWindow(String node) {
        //TODO fazer algo
        Toast.makeText(context, "Rota salva com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucessList(String node) {
        //TODO fazer algo
    }

    @Override
    public void error(String mensagem) {
        //TODO fazer algo
        Toast.makeText(context, "Erro ao  salva rota", Toast.LENGTH_SHORT).show();
    }
}
