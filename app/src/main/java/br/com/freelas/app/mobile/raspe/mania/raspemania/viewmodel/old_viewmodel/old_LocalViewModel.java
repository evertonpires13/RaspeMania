package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services.LocalService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.old_entidade.Local;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.old_activity.LocalActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.old_componente.LocalComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class old_LocalViewModel extends BaseViewModel implements GenericInterface {

    /*--------------------------------------------------------------------------------------------*/
    private LocalService service = new LocalService(this);
    private LocalComponente componentes = new LocalComponente();
    private Context context;

    /*--------------------------------------------------------------------------------------------*/
    public old_LocalViewModel() {

    }

    /*--------------------------------------------------------------------------------------------*/
    public old_LocalViewModel(Context context, LocalComponente componentes) {

        this.componentes = componentes;
        this.context = context;


    }

    /*--------------------------------------------------------------------------------------------*/
    public void save(Local model) {

        service.save(model);

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessWindow(String node) {

        componentes.model = service.dado;
        if (node.equals(LocalService.RETORNO_SAVE)) {
            Toast.makeText(context, "Colaborador salva com sucesso", Toast.LENGTH_SHORT).show();
        } else {

        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessList(String node) {

        ArrayAdapter<Local> arrayAdapter;
        componentes.modelList = service.dados;
        if (node.equals(service.RETORNO_LOAD_ALL)) {

            arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, service.dados);
            componentes.lista.setAdapter(arrayAdapter);
            componentes.lista.setOnItemClickListener(clickLista);

        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void error(String mensagem) {

        Toast.makeText(context, "Erro ao  salva Colaborador", Toast.LENGTH_SHORT).show();
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

            Local model = componentes.modelList.get(i);
            Intent intent = new Intent(context, LocalActivity.class);
            intent.putExtra("chave", model.chave);
            context.startActivity(intent);

        }
    };
    /*--------------------------------------------------------------------------------------------*/

}
