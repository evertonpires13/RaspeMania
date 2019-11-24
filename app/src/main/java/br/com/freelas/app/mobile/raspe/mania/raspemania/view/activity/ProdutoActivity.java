package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.componente.ProdutoComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ProdutoViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ProdutoActivity extends AppCompatActivity {

    /*--------------------------------------------------------------------------------------------*/
    private ProdutoComponente produtoComponente = new ProdutoComponente();

    //Create viewModel
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new ProdutoViewModel(this, produtoComponente));
    private ProdutoViewModel viewModel;


    /*--------------------------------------------------------------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProdutoViewModel.class);

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
