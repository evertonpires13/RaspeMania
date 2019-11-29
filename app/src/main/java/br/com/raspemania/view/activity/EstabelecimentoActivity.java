package br.com.raspemania.view.activity;

import android.os.Bundle;
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
import br.com.raspemania.helper.TextHelper;
import br.com.raspemania.model.entidade.Estabelecimento;
import br.com.raspemania.model.entidade.Rota;
import br.com.raspemania.view.adapter.EstabelecimentoAdapter;
import br.com.raspemania.viewmodel.EstabelecimentoViewModel;
import br.com.raspemania.viewmodel.RotaViewModel;

public class EstabelecimentoActivity extends BaseActivity {

    private EstabelecimentoViewModel mViewModel;
    private RotaViewModel mViewModelRota;
    private AppCompatButton btnSalvar;
    private TextInputEditText estabelecimento_estoque;
    private TextInputEditText estabelecimento_endereco;
    private TextInputEditText estabelecimento_codigo;
    private TextInputEditText estabelecimento_porcentagem;
    private Spinner estabelecimento_rota;

    private Estabelecimento estabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        mViewModel = ViewModelProviders.of(this).get(EstabelecimentoViewModel.class);
        mViewModelRota = ViewModelProviders.of(this).get(RotaViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar);
        estabelecimento_estoque = findViewById(R.id.estabelecimento_estoque);
        estabelecimento_endereco = findViewById(R.id.estabelecimento_endereco);
        estabelecimento_codigo = findViewById(R.id.estabelecimento_codigo);
        estabelecimento_porcentagem = findViewById(R.id.estabelecimento_porcentagem);
        estabelecimento_rota = findViewById(R.id.estabelecimento_rota);

        estabelecimento = new Estabelecimento();

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(EstabelecimentoAdapter.TAG) != null) {
            bindCampos((Estabelecimento) getIntent().getExtras().getSerializable(EstabelecimentoAdapter.TAG));
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposValidos()) {
                    return;
                }
                mViewModel.saveOrUpdate(estabelecimento());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModelRota.getAll();

    }

    private void doBindings() {
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();
        observeGetAllRotas();
    }

    private void bindCampos(Estabelecimento itemLista) {
        this.estabelecimento = itemLista;
        estabelecimento_estoque.setText(itemLista.estoque + "");
        estabelecimento_endereco.setText(itemLista.endereco);
        estabelecimento_codigo.setText(itemLista.codigo);
        estabelecimento_porcentagem.setText(itemLista.porcentagem + "");
        //  private Spinner estabelecimento_rota;

    }

    private Boolean camposValidos() {
        if (TextHelper.isEmpty(estabelecimento_estoque.getText())) {
            estabelecimento_estoque.setError(getString(R.string.erro_estabelecimento_estoque));
            return false;
        }
        if (TextHelper.isEmpty(estabelecimento_endereco.getText())) {
            estabelecimento_endereco.setError(getString(R.string.erro_estabelecimento_endereco));
            return false;
        }
        if (TextHelper.isEmpty(estabelecimento_codigo.getText())) {
            estabelecimento_codigo.setError(getString(R.string.erro_estabelecimento_codigo));
            return false;
        }
        if (TextHelper.isEmpty(estabelecimento_porcentagem.getText())) {
            estabelecimento_porcentagem.setError(getString(R.string.erro_estabelecimento_porcentagem));
            return false;
        }
        return true;
    }

    private Estabelecimento estabelecimento() {
        estabelecimento.estoque = Integer.parseInt(estabelecimento_estoque.getText().toString());
        estabelecimento.porcentagem = Integer.parseInt(estabelecimento_porcentagem.getText().toString());
        estabelecimento.codigo = estabelecimento_codigo.getText().toString();
        estabelecimento.endereco = estabelecimento_endereco.getText().toString();
        estabelecimento.rota = (Rota) estabelecimento_rota.getSelectedItem();
        return estabelecimento;
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

    private void observeGetAllRotas() {

        mViewModelRota.mList.observe(this, new Observer<List<Rota>>() {
            @Override
            public void onChanged(List<Rota> resultList) {
                ArrayAdapter<Rota> adapter = new ArrayAdapter<>(EstabelecimentoActivity.this, android.R.layout.simple_list_item_1, resultList);
                estabelecimento_rota.setAdapter(adapter);
                // prepareRecyclerView(resultList);
            }
        });
    }


}
