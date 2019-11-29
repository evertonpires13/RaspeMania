package br.com.raspemania.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import br.com.raspemania.R;
import br.com.raspemania.helper.TextHelper;
import br.com.raspemania.model.entidade.Produto;
import br.com.raspemania.view.adapter.ProdutoAdapter;
import br.com.raspemania.viewmodel.ProdutoViewModel;



public class ProdutoActivity extends BaseActivity {

    private ProdutoViewModel mViewModel;
    private AppCompatButton btnSalvar;
    private TextInputEditText nomeProduto;
    private TextInputEditText valorPproduto;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        mViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar);
        nomeProduto = findViewById(R.id.nome_produto);
        valorPproduto = findViewById(R.id.valor_produto);
        //valorPproduto.addTextChangedListener(new MoneyTextWatcher(valorPproduto));

        produto = new Produto();

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(ProdutoAdapter.TAG) != null) {
            bindCampos((Produto) getIntent().getExtras().getSerializable(ProdutoAdapter.TAG));
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!camposValidos()){
                   return;
                }
                mViewModel.saveOrUpdate(produto());
            }
        });
    }

    private void doBindings(){
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();
    }

    private void bindCampos(Produto itemLista){
        this.produto = itemLista;
        nomeProduto.setText(itemLista.nome);
        valorPproduto.setText(Float.toString(itemLista.valor));
    }

    private Boolean camposValidos(){
        if(TextHelper.isEmpty(nomeProduto.getText())){
            nomeProduto.setError(getString(R.string.erro_nome_produto));
            return false;
        }
        if(TextHelper.isEmpty(valorPproduto.getText())){
            valorPproduto.setError(getString(R.string.erro_valor_produto));
            return false;
        }
        return true;
    }

    private Produto produto() {
        produto.nome = nomeProduto.getText().toString();
        produto.valor = Float.parseFloat(valorPproduto.getText().toString());
        return produto;
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
