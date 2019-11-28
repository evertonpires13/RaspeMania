package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.TextHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.adapter.ColaboradorAdapter;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;

public class ColaboradorActivity extends BaseActivity {

    private ColaboradorViewModel mViewModel;
    private AppCompatButton btnSalvar;

    private String[] perfil = new String[]{"Administrador", "Colaborador"};
    private TextInputEditText nome_colaborador;
    private TextInputEditText apelido_colaborador;
    private TextInputEditText email_colaborador;
    private TextInputEditText senha_colaborador;
    private Spinner perfil_colaborador;

    private Colaborador colaborador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborador2);

        mViewModel = ViewModelProviders.of(this).get(ColaboradorViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar);

        perfil_colaborador = findViewById(R.id.perfil_colaborador);
        nome_colaborador = findViewById(R.id.nome_colaborador);
        apelido_colaborador = findViewById(R.id.apelido_colaborador);
        email_colaborador = findViewById(R.id.email_colaborador);
        senha_colaborador = findViewById(R.id.senha_colaborador);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, perfil);
        perfil_colaborador.setAdapter(adapter);

        colaborador = new Colaborador();

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(ColaboradorAdapter.TAG) != null) {
            bindCampos((Colaborador) getIntent().getExtras().getSerializable(ColaboradorAdapter.TAG));
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposValidos()) {
                    return;
                }
                mViewModel.saveOrUpdate(colaborador());
            }
        });
    }

    private void doBindings() {
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();
    }

    private void bindCampos(Colaborador itemLista) {
        this.colaborador = itemLista;

        nome_colaborador.setText(itemLista.nome);
        apelido_colaborador.setText(itemLista.apelido);
        email_colaborador.setText(itemLista.email);
        senha_colaborador.setText(itemLista.senha);
        perfil_colaborador.setSelection((int) itemLista.perfil);

    }

    private Boolean camposValidos() {
        if (TextHelper.isEmpty(nome_colaborador.getText())) {
            nome_colaborador.setError(getString(R.string.erro_nome_colaborador));
            return false;
        }
        if (TextHelper.isEmpty(apelido_colaborador.getText())) {
            apelido_colaborador.setError(getString(R.string.erro_apelido_colaborador));
            return false;
        }
        if (TextHelper.isEmpty(email_colaborador.getText())) {
            email_colaborador.setError(getString(R.string.erro_email_colaborador));
            return false;
        }
        if (TextHelper.isEmpty(senha_colaborador.getText())) {
            senha_colaborador.setError(getString(R.string.erro_senha_colaborador));
            return false;
        }
        return true;
    }

    private Colaborador colaborador() {
        colaborador.nome = nome_colaborador.getText().toString();
        colaborador.apelido = apelido_colaborador.getText().toString();
        colaborador.email = email_colaborador.getText().toString();
        colaborador.senha = senha_colaborador.getText().toString();
        colaborador.perfil = perfil_colaborador.getSelectedItemPosition();
        return colaborador;
    }

    private void observeSucess() {
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
