package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.ColaboradorComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.ColaboradorViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ColaboradorActivity extends AppCompatActivity {

    /*--------------------------------------------------------------------------------------------*/
    ColaboradorComponente colaboradorComponente = new ColaboradorComponente();

    //Create viewModel
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new ColaboradorViewModel(this, colaboradorComponente));
    private ColaboradorViewModel viewModel;

    private String perfil[] = new String[]{"Administrador", "Colaborador"};

    /*--------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborador);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ColaboradorViewModel.class);

        colaboradorComponente.editNome = findViewById(R.id.editNome);
        colaboradorComponente.editApelido = findViewById(R.id.editApelido);
        colaboradorComponente.editSenha = findViewById(R.id.editSenha);

        colaboradorComponente.spinnerPerfil = findViewById(R.id.spinnerColaborador);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, perfil);
        colaboradorComponente.spinnerPerfil.setAdapter(adapter);

        colaboradorComponente.mSalvar = findViewById(R.id.mSalvar);
        colaboradorComponente.mSalvar.setOnClickListener(mSalvarClick);

        // Verificar se tem dados
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("chave") != null) {
            String chave = getIntent().getExtras().getString("chave");
            viewModel.load(chave );
        }

        colaboradorComponente.lista = findViewById(R.id.lista);
        viewModel.mountListView();

    }

    /*--------------------------------------------------------------------------------------------*/

   private View.OnClickListener mSalvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (colaboradorComponente.model == null) {
                colaboradorComponente.model = new Colaborador();
            }

            colaboradorComponente.model.nome = colaboradorComponente.editNome.getText().toString();
            colaboradorComponente.model.apelido = colaboradorComponente.editApelido.getText().toString();
            colaboradorComponente.model.senha = colaboradorComponente.editSenha.getText().toString();
            colaboradorComponente.model.perfil = colaboradorComponente.spinnerPerfil.getSelectedItemPosition();

            viewModel.save(colaboradorComponente.model);

        }
    };
    /*--------------------------------------------------------------------------------------------*/
}
