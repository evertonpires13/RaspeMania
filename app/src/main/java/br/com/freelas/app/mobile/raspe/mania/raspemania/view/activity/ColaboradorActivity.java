package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.TextHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.adapter.ColaboradorAdapter;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;

public class ColaboradorActivity extends BaseActivity {

    private ColaboradorViewModel mViewModel;
    private AppCompatButton btnSalvar;
    private TextInputEditText mNome;
    private TextInputEditText mApelido;
    private Colaborador mColaborador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborador);

        mViewModel = ViewModelProviders.of(this).get(ColaboradorViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar_colaborador);
        mNome = findViewById(R.id.nome_colaborador);
        mApelido = findViewById(R.id.apelido_colaborador);

        mColaborador = new Colaborador();

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(ColaboradorAdapter.TAG) != null) {
            bindCampos((Colaborador) getIntent().getExtras().getSerializable(ColaboradorAdapter.TAG));
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!camposValidos()){
                    return;
                }
                mViewModel.saveOrUpdate(colaborador());
            }
        });
    }

    private void doBindings(){
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();
    }

    private void bindCampos(Colaborador itemLista){
        this.mColaborador = itemLista;
        mNome.setText(itemLista.nome);
        mApelido.setText(itemLista.apelido);
    }

    private Boolean camposValidos(){
        if(TextHelper.isEmpty(mNome.getText())){
            mNome.setError(getString(R.string.erro_nome_colaborador));
            return false;
        }
        if(TextHelper.isEmpty(mApelido.getText())){
            mApelido.setError(getString(R.string.erro_apelido_colaborador));
            return false;
        }
        return true;
    }

    private Colaborador colaborador() {
        mColaborador.nome = mNome.getText().toString();
        mColaborador.apelido = mApelido.getText().toString();
        return mColaborador;
    }

    private void observeSucess(){
        mViewModel.sucess.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                //Snackbar.make(view, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                finish();
            }
        });
    }
}