package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.old_activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.old_entidade.Local;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.old_componente.LocalComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.old_componente.RotaComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.old_LocalViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.RotaViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.ViewModelFactory;

public class LocalActivity extends AppCompatActivity {

    /*--------------------------------------------------------------------------------------------*/
    private LocalComponente componentes = new LocalComponente();
    private RotaComponente componentesRota = new RotaComponente();

    //Create viewModel Local
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new old_LocalViewModel(this, componentes));
    private old_LocalViewModel viewModel;

    //Create viewModel Rota
    private ViewModelProvider.Factory viewModelFactoryRota = ViewModelFactory.createFor(new RotaViewModel(this, componentesRota));
    private RotaViewModel viewModelRota;

    /*--------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(old_LocalViewModel.class);
        viewModelRota = ViewModelProviders.of(this, viewModelFactoryRota).get(RotaViewModel.class);

        // Verificar se tem dados
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("chave") != null) {
            String chave = getIntent().getExtras().getString("chave");
            viewModel.load(chave);
        }

        componentes.editCodigo = findViewById(R.id.editCodigo);
        componentes.editEndereco = findViewById(R.id.editEndereco);
        componentes.editPercentual = findViewById(R.id.editPercentual);
        componentes.editEstoque = findViewById(R.id.editEstoque);

        //  componentes.spinnerRota = findViewById(R.id.spinnerRota);
        componentesRota.spinnerColaborador = findViewById(R.id.spinnerRota);
        viewModelRota.mountSpinner();

        componentes.mSalvar = findViewById(R.id.mSalvar);
        componentes.mSalvar.setOnClickListener(mSalvarClick);

        componentes.lista = findViewById(R.id.lista);
        viewModel.mountListView();
    }

    /*--------------------------------------------------------------------------------------------*/
    View.OnClickListener mSalvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (componentes.model == null) {
                componentes.model = new Local();
            }

            componentes.model.codigo = componentes.editCodigo.getText().toString();
            componentes.model.endereco = componentes.editEndereco.getText().toString();
            componentes.model.porcentagem = Integer.parseInt(componentes.editPercentual.getText().toString());
            componentes.model.estoque = Integer.parseInt(componentes.editEstoque.getText().toString());
            componentes.model.rota = (Rota) componentes.spinnerRota.getSelectedItem();

            viewModel.save(componentes.model);

        }
    };
    /*--------------------------------------------------------------------------------------------*/
}
