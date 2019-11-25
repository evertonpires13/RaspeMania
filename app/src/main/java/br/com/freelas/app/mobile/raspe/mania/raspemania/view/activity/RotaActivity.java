package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.ColaboradorComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.RotaComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.RotaViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.view.View;

public class RotaActivity extends AppCompatActivity {

    /*--------------------------------------------------------------------------------------------*/
    private RotaComponente componentes = new RotaComponente();
    ColaboradorComponente colaboradorComponente = new ColaboradorComponente();
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new RotaViewModel(this, componentes));
    private ViewModelProvider.Factory viewColaboradorFactory = ViewModelFactory.createFor(new ColaboradorViewModel(this, colaboradorComponente));
    private RotaViewModel viewModel;
    private ColaboradorViewModel colaboradorViewModel;

    /*--------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RotaViewModel.class);
        colaboradorViewModel = ViewModelProviders.of(this, viewColaboradorFactory).get(ColaboradorViewModel.class);

        // Verificar se tem dados
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("chave") != null) {
            String chave = getIntent().getExtras().getString("chave");
            viewModel.load(chave);
        }

        componentes.editNome = findViewById(R.id.editNome);

       // componentes.spinnerColaborador = findViewById(R.id.spinnerColaborador);

        colaboradorComponente.spinnerColaborador = findViewById(R.id.spinnerColaborador);
        colaboradorViewModel.mountSpinner();

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
                componentes.model = new Rota();
            }

            componentes.model.nome = componentes.editNome.getText().toString();
            componentes.model.colaborador = (Colaborador) componentes.spinnerColaborador.getSelectedItem();

            viewModel.save(componentes.model);
        }
    };

    /*--------------------------------------------------------------------------------------------*/

}
