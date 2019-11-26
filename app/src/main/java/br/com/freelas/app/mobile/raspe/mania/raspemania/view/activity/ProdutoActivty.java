package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.annotation.Nullable;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.TextHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.ProdutoViewModel;



public class ProdutoActivty extends BaseActivity {

    private ProdutoViewModel mViewModel;
    private AppCompatButton btnSalvar;
    private TextInputEditText nomeProduto;
    private TextInputEditText valorPproduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        mViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);

        doBindings();

        btnSalvar = findViewById(R.id.btn_salvar);
        nomeProduto = findViewById(R.id.nome_produto);
        valorPproduto = findViewById(R.id.valor_produto);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!camposValidos()){
                   return;
                }
                mViewModel.save(produto());
            }
        });
    }

    private void doBindings(){
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();
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
        return new Produto(nomeProduto.getText().toString(), Long.parseLong(valorPproduto.getText().toString()));
    }

    private void observeSucess(){
        mViewModel.sucess.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
            finish();
            //Snackbar.make(R.layout.activity_produto, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
