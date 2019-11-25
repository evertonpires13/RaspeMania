package br.com.freelas.app.mobile.raspe.mania.raspemania.view.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente.ProdutoComponente;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.viewmodel.ProdutoViewModel;

public class ProdutoFragment extends BaseFragment {

    private ProdutoComponente produtoComponente = new ProdutoComponente();
    private ProdutoViewModel mViewModel;

    public static ProdutoFragment newInstance() {
        return new ProdutoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_produto, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        // TODO: Use the ViewModel

        doBindings();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        produtoComponente.editNome = view.findViewById(R.id.editNome);
        produtoComponente.editValor = view.findViewById(R.id.editValor);

        produtoComponente.mSalvar = view.findViewById(R.id.mSalvar);
        produtoComponente.mSalvar.setOnClickListener(mSalvarClick);

        produtoComponente.lista = view.findViewById(R.id.lista);

    }

    private void doBindings(){
        super.onStart();
        super.observeError(mViewModel);
        super.observeSucess(mViewModel);
    }

    View.OnClickListener mSalvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (produtoComponente.model == null) {
                produtoComponente.model = new Produto();
            }

            produtoComponente.model.nome = produtoComponente.editNome.getText().toString();
            produtoComponente.model.valor = Long.parseLong(produtoComponente.editValor.getText().toString());

            mViewModel.save(produtoComponente.model);
        }
    };

}
