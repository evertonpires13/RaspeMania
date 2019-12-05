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
import br.com.raspemania.model.entidade.Estabelecimento;
import br.com.raspemania.model.entidade.Rota;
import br.com.raspemania.view.adapter.EstabelecimentoAdapter;
import br.com.raspemania.viewmodel.EstabelecimentoViewModel;
import br.com.raspemania.viewmodel.RotaViewModel;

public class EstabelecimentoActivity extends BaseActivity {

    private EstabelecimentoViewModel mViewModel;
    private RotaViewModel mViewModelRota;
    private AppCompatButton btnSalvar;
    private TextInputEditText mEstabelecimentoEstoque;
    private TextInputEditText mEstabelecimentoEndereco;
    private TextInputEditText mEstabelecimentoCodigo;
    private TextInputEditText mEstabelecimentoPorcentagem;
    private Spinner mEstabelecimentoRota;
    private Spinner mStatus;
    private ArrayAdapter<Rota> adapter;
    private Estabelecimento mEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        mViewModel = ViewModelProviders.of(this).get(EstabelecimentoViewModel.class);
        mViewModelRota = ViewModelProviders.of(this).get(RotaViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar);
        mEstabelecimentoEstoque = findViewById(R.id.estabelecimento_estoque);
        mEstabelecimentoEndereco = findViewById(R.id.estabelecimento_endereco);
        mEstabelecimentoCodigo = findViewById(R.id.estabelecimento_codigo);
        mEstabelecimentoPorcentagem = findViewById(R.id.estabelecimento_porcentagem);
        mEstabelecimentoRota = findViewById(R.id.estabelecimento_rota);
        mStatus = findViewById(R.id.estabelecimento_status);

        super.spinnerStatus(mStatus);

        mEstabelecimento = new Estabelecimento();

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
        this.mEstabelecimento = itemLista;
        mEstabelecimentoEstoque.setText(itemLista.estoque + "");
        mEstabelecimentoEndereco.setText(itemLista.endereco);
        mEstabelecimentoCodigo.setText(itemLista.codigo);
        mEstabelecimentoPorcentagem.setText(itemLista.porcentagem + "");
        mStatus.setSelection(super.setSpinner(itemLista.status));
        //  private Spinner estabelecimento_rota;

    }

    private Boolean camposValidos() {
        if (TextUtils.isEmpty(mEstabelecimentoEstoque.getText())) {
            mEstabelecimentoEstoque.setError(getString(R.string.erro_estabelecimento_estoque));
            return false;
        }
        if (TextUtils.isEmpty(mEstabelecimentoEndereco.getText())) {
            mEstabelecimentoEndereco.setError(getString(R.string.erro_estabelecimento_endereco));
            return false;
        }
        if (TextUtils.isEmpty(mEstabelecimentoCodigo.getText())) {
            mEstabelecimentoCodigo.setError(getString(R.string.erro_estabelecimento_codigo));
            return false;
        }
        if (TextUtils.isEmpty(mEstabelecimentoPorcentagem.getText())) {
            mEstabelecimentoPorcentagem.setError(getString(R.string.erro_estabelecimento_porcentagem));
            return false;
        }
        return true;
    }

    private Estabelecimento estabelecimento() {
        mEstabelecimento.estoque = Integer.parseInt(mEstabelecimentoEstoque.getText().toString());
        mEstabelecimento.porcentagem = Integer.parseInt(mEstabelecimentoPorcentagem.getText().toString());
        mEstabelecimento.codigo = mEstabelecimentoCodigo.getText().toString();
        mEstabelecimento.endereco = mEstabelecimentoEndereco.getText().toString();
        mEstabelecimento.rota = (Rota) mEstabelecimentoRota.getSelectedItem();
        mEstabelecimento.status = super.getStatusSpinner((String) mStatus.getSelectedItem());
        return mEstabelecimento;
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

    public void bindSpinner(Rota rota) {
        for(int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).nome.equalsIgnoreCase(rota.nome)) {
                mEstabelecimentoRota.setSelection(i);
                break;
            }
        }
    }

    private void observeGetAllRotas() {

        mViewModelRota.mList.observe(this, new Observer<List<Rota>>() {
            @Override
            public void onChanged(List<Rota> resultList) {
                adapter = new ArrayAdapter<>(EstabelecimentoActivity.this, R.layout.item_spinner_default, resultList);
                mEstabelecimentoRota.setAdapter(adapter);
                if(mEstabelecimento.rota != null) bindSpinner(mEstabelecimento.rota);
            }
        });
    }


}
