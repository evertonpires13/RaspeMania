package br.com.raspemania.view.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.helper.TextHelper;
import br.com.raspemania.model.entidade.Estabelecimento;
import br.com.raspemania.model.entidade.Leitura;
import br.com.raspemania.model.entidade.PremiacaoList;
import br.com.raspemania.model.entidade.Produto;
import br.com.raspemania.viewmodel.EstabelecimentoViewModel;
import br.com.raspemania.viewmodel.LeituraViewModel;
import br.com.raspemania.viewmodel.ProdutoViewModel;

public class LeituraActivity extends BaseActivity {

    /*--------------------------------------------------------------------------------------------*/
    private ListView listviewPremiacao;
    private Spinner spinnerProduto;
    private Spinner spinnerLocal;
    private TextInputEditText textQuantidadeVendida;
    private TextInputEditText textQuantidadeReposicao;
    private AppCompatButton btnSalvar;
    private AppCompatButton btnAdicionar;

    private ProdutoViewModel mViewModelProduto;
    private EstabelecimentoViewModel mViewModelEstabelecimento;
    private LeituraViewModel mViewModelLeitura;
    private Leitura leitura;

    private AlertDialog alertDialog;
    private View view1;

    /*--------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);

        spinnerProduto = findViewById(R.id.spinnerProduto);
        spinnerLocal = findViewById(R.id.spinnerLocal);
        textQuantidadeVendida = findViewById(R.id.textQuantidadeVendida);
        textQuantidadeReposicao = findViewById(R.id.textQuantidadeReposicao);
        listviewPremiacao = findViewById(R.id.listviewPremiacao);
        btnSalvar = findViewById(R.id.btn_salvar);
        btnAdicionar = findViewById(R.id.btn_adicionar);

        mViewModelProduto = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        mViewModelEstabelecimento = ViewModelProviders.of(this).get(EstabelecimentoViewModel.class);
        mViewModelLeitura = ViewModelProviders.of(this).get(LeituraViewModel.class);

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

    /*-------------------------------------------------------------------------------------------*/
    private View.OnClickListener clickAdicionarPremiacao = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            LayoutInflater li = getLayoutInflater();

            view1 = li.inflate(R.layout.dialog_premiacao, null);
            view1.findViewById(R.id.btn_cancelar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            view1.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextInputEditText textQuantidade = view1.findViewById(R.id.textQuantidade);
                    TextInputEditText textValor = view1.findViewById(R.id.textValor);
                    PremiacaoList premiacaoList = new PremiacaoList();
                    premiacaoList.quantidadePremiada = Integer.parseInt(textQuantidade.getText().toString());
                    premiacaoList.valorPremiado = Long.parseLong(textValor.getText().toString());
                    leitura.premiacaoList.add(premiacaoList);

                    ArrayAdapter<PremiacaoList> adapter = new ArrayAdapter<PremiacaoList>(LeituraActivity.this, android.R.layout.simple_list_item_1, leitura.premiacaoList);
                    listviewPremiacao.setAdapter(adapter);

                    alertDialog.dismiss();
                }
            });

            AlertDialog.Builder builder = new AlertDialog.Builder(LeituraActivity.this);
            builder.setTitle("Adicionar Premiação");
            builder.setView(view1);
            alertDialog = builder.create();
            alertDialog.show();


        }
    };

    /*-------------------------------------------------------------------------------------------*/
    private Leitura leitura() {

        leitura.local = (Estabelecimento) spinnerLocal.getSelectedItem();
        leitura.produto = (Produto) spinnerProduto.getSelectedItem();
        leitura.hasReposicao = 0;

        leitura.quantidadeReposicao = Integer.parseInt(textQuantidadeReposicao.getText().toString());
        leitura.quantidadeVendida = Integer.parseInt(textQuantidadeVendida.getText().toString());

        return leitura;
    }
    /*-------------------------------------------------------------------------------------------*/

    private Boolean camposValidos() {

        if (TextHelper.isEmpty(textQuantidadeVendida.getText())) {
            textQuantidadeVendida.setError(getString(R.string.erro_rota_descricao));
            return false;
        }

        if (TextHelper.isEmpty(textQuantidadeReposicao.getText())) {
            textQuantidadeReposicao.setError(getString(R.string.erro_rota_descricao));
            return false;
        }

        return true;
    }
    /*-------------------------------------------------------------------------------------------*/

    @Override
    public void onResume() {
        super.onResume();

        mViewModelProduto.getAll();
        mViewModelEstabelecimento.getAll();


    }

    /*-------------------------------------------------------------------------------------------*/
    private void doBindings() {
        super.onStart();
        super.observeError(mViewModelLeitura);
        observeSucessProduto();
        observeSucessEstabelecimento();
    }
    /*-------------------------------------------------------------------------------------------*/

    private void observeSucessProduto() {

        mViewModelProduto.mList.observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> resultList) {
                ArrayAdapter<Produto> adapter = new ArrayAdapter<>(LeituraActivity.this, android.R.layout.simple_list_item_1, resultList);
                spinnerProduto.setAdapter(adapter);

            }
        });
    }
    /*-------------------------------------------------------------------------------------------*/

    private void observeSucessEstabelecimento() {

        mViewModelEstabelecimento.mList.observe(this, new Observer<List<Estabelecimento>>() {
            @Override
            public void onChanged(List<Estabelecimento> resultList) {
                ArrayAdapter<Estabelecimento> adapter = new ArrayAdapter<>(LeituraActivity.this, android.R.layout.simple_list_item_1, resultList);
                spinnerLocal.setAdapter(adapter);
            }
        });
    }
    /*-------------------------------------------------------------------------------------------*/
    private void observeSucess() {
        mViewModelLeitura.sucess.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                //Snackbar.make(view, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                finish();
            }
        });
    }
    /*-------------------------------------------------------------------------------------------*/
}
