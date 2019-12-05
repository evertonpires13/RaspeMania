package br.com.raspemania.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.helper.MoneyTextWatcher;
import br.com.raspemania.model.entidade.Estabelecimento;
import br.com.raspemania.model.entidade.Leitura;
import br.com.raspemania.model.entidade.PremiacaoList;
import br.com.raspemania.model.entidade.Produto;
import br.com.raspemania.view.adapter.PremiacaoAdapter;
import br.com.raspemania.viewmodel.EstabelecimentoViewModel;
import br.com.raspemania.viewmodel.LeituraViewModel;
import br.com.raspemania.viewmodel.ProdutoViewModel;

public class LeituraActivity extends BaseActivity {

    private Spinner spinnerProduto;
    private Spinner spinnerLocal;
    private TextInputEditText textQuantidadeVendida;
    private TextInputEditText textQuantidadeReposicao;
    private AppCompatButton btnSalvar;
    private AppCompatImageButton btnAdicionar;
    private AppCompatEditText textQuantidade;
    private AppCompatEditText textValor;

    private ProdutoViewModel mViewModelProduto;
    private EstabelecimentoViewModel mViewModelEstabelecimento;
    private LeituraViewModel mViewModelLeitura;
    private Leitura leitura;

    private RecyclerView mRecyclerView;
    private PremiacaoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);

        textQuantidade = findViewById(R.id.add_qtd_premiacao);
        textValor = findViewById(R.id.add_valor_premiacao);
        textValor.addTextChangedListener(new MoneyTextWatcher(textValor));

        spinnerProduto = findViewById(R.id.spinnerProduto);
        spinnerLocal = findViewById(R.id.spinnerLocal);
        textQuantidadeVendida = findViewById(R.id.textQuantidadeVendida);
        textQuantidadeReposicao = findViewById(R.id.textQuantidadeReposicao);
        btnSalvar = findViewById(R.id.btn_salvar);
        btnAdicionar = findViewById(R.id.btn_adicionar);

        mViewModelProduto = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        mViewModelEstabelecimento = ViewModelProviders.of(this).get(EstabelecimentoViewModel.class);
        mViewModelLeitura = ViewModelProviders.of(this).get(LeituraViewModel.class);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        doBindings();

        leitura = new Leitura();
        leitura.premiacaoList = new ArrayList<PremiacaoList>();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposValidos()) {
                    return;
                }
                Toast.makeText(LeituraActivity.this, "sssssss", Toast.LENGTH_SHORT).show();
                mViewModelLeitura.saveOrUpdate(leitura());
            }
        });

        btnAdicionar.setOnClickListener(clickAdicionarPremiacao);

    }

    private View.OnClickListener clickAdicionarPremiacao = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            PremiacaoList premiacaoList = new PremiacaoList();
            premiacaoList.quantidadePremiada = Integer.parseInt(textQuantidade.getText().toString());
            premiacaoList.valorPremiado = Float.parseFloat(textValor.getText().toString());
            leitura.premiacaoList.add(premiacaoList);

            prepareRecyclerView(leitura.premiacaoList);
        }
    };

    private Leitura leitura() {

        leitura.local = (Estabelecimento) spinnerLocal.getSelectedItem();
        leitura.produto = (Produto) spinnerProduto.getSelectedItem();
        leitura.quantidadeReposicao = Integer.parseInt(textQuantidadeReposicao.getText().toString());
        leitura.quantidadeVendida = Integer.parseInt(textQuantidadeVendida.getText().toString());

        return leitura;
    }

    private Boolean camposValidos() {

        if (TextUtils.isEmpty(textQuantidadeVendida.getText())) {
            textQuantidadeVendida.setError(getString(R.string.erro_leitura_qtd_vendide));
            return false;
        }

        if (TextUtils.isEmpty(textQuantidadeReposicao.getText())) {
            textQuantidadeReposicao.setError(getString(R.string.erro_leitura_reposicao));
            return false;
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModelProduto.getAll();
        mViewModelEstabelecimento.getAll();
    }

    private void doBindings() {
        super.onStart();
        super.observeError(mViewModelLeitura);
        observeSucessSpinnerProduto();
        observeSucessSpinnerEstabelecimento();
        observeSucess();
    }

    private void observeSucessSpinnerProduto() {

        mViewModelProduto.mList.observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> resultList) {
                ArrayAdapter<Produto> adapter = new ArrayAdapter<>(LeituraActivity.this, R.layout.item_spinner_default, resultList);
                spinnerProduto.setAdapter(adapter);

            }
        });
    }

    private void observeSucessSpinnerEstabelecimento() {

        mViewModelEstabelecimento.mList.observe(this, new Observer<List<Estabelecimento>>() {
            @Override
            public void onChanged(List<Estabelecimento> resultList) {
                ArrayAdapter<Estabelecimento> adapter = new ArrayAdapter<>(LeituraActivity.this, R.layout.item_spinner_default, resultList);
                spinnerLocal.setAdapter(adapter);
            }
        });
    }
    private void observeSucess(){
        mViewModelLeitura.sucess.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void prepareRecyclerView(List<PremiacaoList> premiacoes){
        mAdapter = new PremiacaoAdapter(premiacoes);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

}
