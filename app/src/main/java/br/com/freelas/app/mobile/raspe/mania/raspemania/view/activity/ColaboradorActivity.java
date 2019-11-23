package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ExampleViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.RotaViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ColaboradorActivity extends AppCompatActivity {


    /*--------------------------------------------------------------------------------------------*/
    private EditText editNome;
    private EditText editApelido;
    private EditText editSenha;
    private Spinner spinnerPerfil;
    private AppCompatButton mSalvar;

    //Create viewModel
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new ColaboradorViewModel(this));
    private ColaboradorViewModel viewModel;

    private Colaborador model;
    private String perfil[] = new String[]{"Administrador", "Colaborador"};

    /*--------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborador);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ColaboradorViewModel.class);

        // Verificar se tem dados
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("chave") != null) {
            // TODO - ler a rota cadastrada
        }

        editNome = findViewById(R.id.editNome);
        editApelido = findViewById(R.id.editApelido);
        editSenha = findViewById(R.id.editSenha);

        spinnerPerfil = findViewById(R.id.spinnerColaborador);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, perfil);
        spinnerPerfil.setAdapter(adapter);

        mSalvar = findViewById(R.id.mSalvar);
        mSalvar.setOnClickListener(mSalvarClick);

    }

    /*--------------------------------------------------------------------------------------------*/
    View.OnClickListener mSalvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (model == null) {
                model = new Colaborador();
            }

            model.nome = editNome.getText().toString();
            model.apelido = editApelido.getText().toString();
            model.senha = editSenha.getText().toString();
            model.perfil = spinnerPerfil.getSelectedItemPosition();

            viewModel.save(model);

        }
    };
    /*--------------------------------------------------------------------------------------------*/
}
