package br.com.raspemania.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.model.entidade.Colaborador;
import br.com.raspemania.model.entidade.Rota;
import br.com.raspemania.view.adapter.RotaAdapter;
import br.com.raspemania.viewmodel.ColaboradorViewModel;
import br.com.raspemania.viewmodel.RotaViewModel;

public class RotaActivity extends BaseActivity {

    private RotaViewModel mViewModel;
    private AppCompatButton btnSalvar;
    private TextInputEditText rota_descricao;
    private Spinner rota_colaborador;
    private Rota rota;
    private ColaboradorViewModel mViewModelColaborador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        mViewModel = ViewModelProviders.of(this).get(RotaViewModel.class);
        mViewModelColaborador = ViewModelProviders.of(this).get(ColaboradorViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar);
        rota_descricao = findViewById(R.id.rota_descricao);
        rota_colaborador = findViewById(R.id.rota_colaborador);

        rota = new Rota();

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(RotaAdapter.TAG) != null) {
            bindCampos((Rota) getIntent().getExtras().getSerializable(RotaAdapter.TAG));
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposValidos()) {
                    return;
                }
                mViewModel.saveOrUpdate(rota());
            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModelColaborador.getAll();

    }

    private void doBindings() {
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();
        observeGetAllColaborador();
    }

    private void bindCampos(Rota itemLista) {
        this.rota = itemLista;
        rota_descricao.setText(itemLista.nome);
        //rota_colaborador.setSelection(itemLista.);setText(Float.toString(itemLista.valor));
    }

    private Boolean camposValidos() {
        if (TextUtils.isEmpty(rota_descricao.getText())) {
            rota_descricao.setError(getString(R.string.erro_rota_descricao));
            return false;
        }
        return true;
    }

    private Rota rota() {
        rota.nome = rota_descricao.getText().toString();
        rota.colaborador = (Colaborador) rota_colaborador.getSelectedItem();
        return rota;
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


    private void observeGetAllColaborador() {

        mViewModelColaborador.mList.observe(this, new Observer<List<Colaborador>>() {
            @Override
            public void onChanged(List<Colaborador> resultList) {
                ArrayAdapter<Colaborador> adapter = new ArrayAdapter<>(RotaActivity.this, android.R.layout.simple_list_item_1, resultList);
                rota_colaborador.setAdapter(adapter);
                // prepareRecyclerView(resultList);
            }
        });
    }


}
