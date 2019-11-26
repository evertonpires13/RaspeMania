package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services.GenericService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services.ColaboradorService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.old_activity.ColaboradorActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.old_componente.ColaboradorComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class ColaboradorViewModel extends BaseViewModel implements GenericInterface {

    /*--------------------------------------------------------------------------------------------*/
    private ColaboradorService service = new ColaboradorService(this);
    private ColaboradorComponente colaboradorComponente;
    private Context context;
    //   public Spinner spinnerColaborador;
    //  private int acao;
////    private Colaborador model;
    //   private ListView lista;
    //  private List<Colaborador> list;


    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorViewModel() {

    }

    /*--------------------------------------------------------------------------------------------*/
    public ColaboradorViewModel(Context context, ColaboradorComponente colaboradorComponente) {

        this.colaboradorComponente = colaboradorComponente;
        this.context = context;

    }

    /*--------------------------------------------------------------------------------------------*/
    public void save(Colaborador model) {

        service.save(model);

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessWindow(String node) {

        if (node.equals(GenericService.RETORNO_LOAD_KEY)){
            colaboradorComponente.model = service.dado;
            setarDados();
        } else{
            if (node.equals(GenericService.RETORNO_SAVE)){
                Toast.makeText(context, "Colaborador salva com sucesso", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessList(String node) {

        ArrayAdapter<Colaborador> colaboradorArrayAdapter;
        colaboradorComponente.modelList = service.dados;
        colaboradorArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, colaboradorComponente.modelList);

        if (colaboradorComponente.spinnerColaborador != null) {
            colaboradorComponente.spinnerColaborador.setAdapter(colaboradorArrayAdapter);
        }

        if (colaboradorComponente.lista != null) {
            colaboradorComponente.lista.setAdapter(colaboradorArrayAdapter);
            colaboradorComponente.lista.setOnItemClickListener(clickLista);

        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void error(String mensagem) {

        Toast.makeText(context, "Erro ao  salva Colaborador", Toast.LENGTH_SHORT).show();
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
    AdapterView.OnItemClickListener clickLista = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Colaborador model = colaboradorComponente.modelList.get(i);
            Intent intent = new Intent(context, ColaboradorActivity.class);
            intent.putExtra("chave", model.chave);
            context.startActivity(intent);

        }
    };
    /*--------------------------------------------------------------------------------------------*/
    private void setarDados(){
        colaboradorComponente.editApelido.setText(colaboradorComponente.model.apelido);
        colaboradorComponente.editNome.setText(colaboradorComponente.model.nome);
        colaboradorComponente.editSenha.setText(colaboradorComponente.model.senha);
        colaboradorComponente.spinnerPerfil.setSelection( ( (int) colaboradorComponente.model.perfil) );
    }
    /*--------------------------------------------------------------------------------------------*/
}
