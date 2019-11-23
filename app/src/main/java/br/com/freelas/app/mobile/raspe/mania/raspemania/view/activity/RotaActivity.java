package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ExampleViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.RotaViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ViewModelFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class RotaActivity extends AppCompatActivity {

    /*--------------------------------------------------------------------------------------------*/
    private EditText editNome;
    private Spinner spinnerColaborador;
    private AppCompatButton mSalvar;

    private Rota model;

    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new RotaViewModel(this));
    private ViewModelProvider.Factory viewColaboradorFactory = ViewModelFactory.createFor(new ColaboradorViewModel(this));
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
        if (getIntent()!=null && getIntent().getExtras()!=null && getIntent().getExtras().getString("chave")!=null){
            // TODO - ler a rota cadastrada
        }

        editNome = findViewById(R.id.editNome);
        spinnerColaborador = findViewById(R.id.spinnerColaborador);
        Log.e("ddd", spinnerColaborador.toString());
       // colaboradorViewModel.spinnerColaborador = spinnerColaborador;
        colaboradorViewModel.mountSpinner(spinnerColaborador);

        mSalvar = findViewById(R.id.mSalvar);
        mSalvar.setOnClickListener(mSalvarClick);


    }
    /*--------------------------------------------------------------------------------------------*/
    View.OnClickListener mSalvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (model==null){
                model=new Rota();
            }

            model.nome = editNome.getText().toString();
            model.colaborador =(Colaborador) spinnerColaborador.getSelectedItem();

            viewModel.save(model);
        }
    };
    /*--------------------------------------------------------------------------------------------*/
}
