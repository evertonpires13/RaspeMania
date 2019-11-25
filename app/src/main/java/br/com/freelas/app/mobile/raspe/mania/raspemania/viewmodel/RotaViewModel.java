package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.RotaService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.RotaActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.RotaComponente;

public class RotaViewModel extends BaseViewModel implements GenericInterface {

    /*--------------------------------------------------------------------------------------------*/
    private RotaService service = new RotaService(this);
    private Context context;
    private RotaComponente componenteRota;

    /*--------------------------------------------------------------------------------------------*/
    public RotaViewModel() {

    }

    /*--------------------------------------------------------------------------------------------*/
    public RotaViewModel(Context context, RotaComponente componenteRota) {

        this.componenteRota = componenteRota;
        this.context = context;

    }

    /*--------------------------------------------------------------------------------------------*/
    public void save(Rota model) {

        service.save(model);

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessWindow(String node) {

        if (node.equals(GenericService.RETORNO_SAVE)) {
            Toast.makeText(context, "Rota salva com sucesso", Toast.LENGTH_SHORT).show();
            componenteRota.model = service.dado;
        } else {
            if (node.equals(GenericService.RETORNO_LOAD_KEY)) {
                componenteRota.editNome.setText(service.dado.nome);
                componenteRota.model = service.dado;
            }
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessList(String node) {

        ArrayAdapter<Rota> arrayAdapter;
        componenteRota.modelList = service.dados;

        if (node.equals(GenericService.RETORNO_LOAD_ALL)) {

            arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, componenteRota.modelList);
            if (componenteRota.spinnerColaborador != null) {
                componenteRota.spinnerColaborador.setAdapter(arrayAdapter);
                Toast.makeText(context, "xxxxxxxxxxxxxxxxxxxxx : " +componenteRota.modelList.size(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "kkkkkkkkkkkkkkkkkkk", Toast.LENGTH_SHORT).show();
            }

            if (componenteRota.lista != null) {
                componenteRota.lista.setAdapter(arrayAdapter);
                componenteRota.lista.setOnItemClickListener(clickLista);
            }

        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void error(String mensagem) {

        Toast.makeText(context, "Erro ao  salva rota", Toast.LENGTH_SHORT).show();

    }

    /*--------------------------------------------------------------------------------------------*/
    public void mountSpinner() {

        service.load();

    }

    /*--------------------------------------------------------------------------------------------*/
    public void load(String chave) {

        service.load(chave);

    }

    /*--------------------------------------------------------------------------------------------*/
    public void mountListView() {

        service.load();

    }

    /*--------------------------------------------------------------------------------------------*/
    private AdapterView.OnItemClickListener clickLista = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Rota model = componenteRota.modelList.get(i);
            Intent intent = new Intent(context, RotaActivity.class);
            intent.putExtra("chave", model.chave);
            context.startActivity(intent);

        }
    };
    /*--------------------------------------------------------------------------------------------*/
}
