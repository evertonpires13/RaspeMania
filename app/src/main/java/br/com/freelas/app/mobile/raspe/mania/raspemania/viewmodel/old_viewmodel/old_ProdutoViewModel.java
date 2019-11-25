package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services.ProdutoService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.old_ProdutoActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.ProdutoComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class old_ProdutoViewModel extends BaseViewModel implements GenericInterface {

    /*--------------------------------------------------------------------------------------------*/
    private ProdutoService service = new ProdutoService(this);
    private Context context;
    private ProdutoComponente produtoComponente;


    /*--------------------------------------------------------------------------------------------*/
    public old_ProdutoViewModel() {

    }

    /*--------------------------------------------------------------------------------------------*/
    public old_ProdutoViewModel(Context context, ProdutoComponente produtoComponente) {

        this.context = context;
        this.produtoComponente = produtoComponente;

    }

    /*--------------------------------------------------------------------------------------------*/
    public void save(Produto model) {

        service.save(model);

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessWindow(String node) {

        if (node.equals("loadKey")) {
            produtoComponente.model = service.dado;
            setarDados();
        } else {
            if (node.equals("save")) {
                Toast.makeText(context, "Produto salvo", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void sucessList(String node) {

        ArrayAdapter<Produto> arrayAdapter;
        produtoComponente.modelList = service.dados;

        if (node.equals("loadALL")) {
            produtoComponente.modelList = service.dados;
            arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, produtoComponente.modelList);
            produtoComponente.lista.setAdapter(arrayAdapter);
            produtoComponente.lista.setOnItemClickListener(clickLista);
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void error(String mensagem) {

        Toast.makeText(context, "Erro ao  salva produto", Toast.LENGTH_SHORT).show();
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

            Produto model = produtoComponente.modelList.get(i);
            Intent intent = new Intent(context, old_ProdutoActivity.class);
            intent.putExtra("chave", model.chave);
            context.startActivity(intent);

        }
    };

    /*--------------------------------------------------------------------------------------------*/
    private void setarDados() {

        produtoComponente.editNome.setText(produtoComponente.model.nome);
        produtoComponente.editValor.setText(produtoComponente.model.valor + "");

    }
    /*--------------------------------------------------------------------------------------------*/

}
