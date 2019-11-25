package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.ProdutoComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_ProdutoViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.view.View;

public class old_ProdutoActivity extends AppCompatActivity {

    /*--------------------------------------------------------------------------------------------*/
    private ProdutoComponente produtoComponente = new ProdutoComponente();

    //Create viewModel
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new old_ProdutoViewModel(this, produtoComponente));
    private old_ProdutoViewModel viewModel;


    /*--------------------------------------------------------------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_produto);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(old_ProdutoViewModel.class);

        // Verificar se tem dados
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("chave") != null) {
            String chave = getIntent().getExtras().getString("chave");
            viewModel.load(chave);
        }


        produtoComponente.editNome = findViewById(R.id.editNome);
        produtoComponente.editValor = findViewById(R.id.editValor);

        produtoComponente.mSalvar = findViewById(R.id.mSalvar);
        produtoComponente.mSalvar.setOnClickListener(mSalvarClick);

        produtoComponente.lista = findViewById(R.id.lista);
        viewModel.mountListView();
    }

    /*--------------------------------------------------------------------------------------------*/
    View.OnClickListener mSalvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (produtoComponente.model == null) {
                produtoComponente.model = new Produto();
            }

            produtoComponente.model.nome = produtoComponente.editNome.getText().toString();
            produtoComponente.model.valor = Long.parseLong(produtoComponente.editValor.getText().toString());

            viewModel.save(produtoComponente.model);

        }
    };
    /*--------------------------------------------------------------------------------------------*/
}
