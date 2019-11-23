package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ColaboradorService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;

public class ColaboradorViewModel extends BaseViewModel implements GenericInterface {

    /*--------------------------------------------------------------------------------------------*/
    private ColaboradorService service = new ColaboradorService(this);
    private Context context;
    public Spinner spinnerColaborador;
    private int acao;


    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorViewModel() {

    }

    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorViewModel(Context context) {
        this.context = context;

    }

    /*--------------------------------------------------------------------------------------------*/
    public void save(Colaborador model) {

        service.save(model);
        acao = 1;
    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessWindow(String node) {
        //TODO fazer algo
        switch (acao) {
            case 1:
                Toast.makeText(context, "Colaborador salva com sucesso", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessList(String node) {
        //TODO fazer algo
        switch (acao) {
            case 2:
                ArrayAdapter<Colaborador> colaboradorArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, service.dados);
                spinnerColaborador.setAdapter(colaboradorArrayAdapter);
                break;


        }
    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void error(String mensagem) {
        //TODO fazer algo
        Toast.makeText(context, "Erro ao  salva Colaborador", Toast.LENGTH_SHORT).show();
    }

    /*--------------------------------------------------------------------------------------------*/
    public void mountSpinner(Spinner spinnerColaborador) {

        this.spinnerColaborador = spinnerColaborador;
        acao = 2;
        service.load();

    }
    /*--------------------------------------------------------------------------------------------*/
}
