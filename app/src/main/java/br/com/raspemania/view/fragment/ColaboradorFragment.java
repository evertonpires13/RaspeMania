package br.com.raspemania.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.model.entidade.Colaborador;
import br.com.raspemania.view.adapter.ColaboradorAdapter;
import br.com.raspemania.viewmodel.ColaboradorViewModel;

public class ColaboradorFragment extends BaseFragment {

    private Context context;// = getContext();
    private ColaboradorViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ColaboradorAdapter mAdapter;

    private TextInputEditText nome_colaborador;
    private AppCompatButton btn_novo;

    public static ColaboradorFragment newInstance() {
        return new ColaboradorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colaborador, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getContext();
        mViewModel = ViewModelProviders.of(this).get(ColaboradorViewModel.class);
        doBindings();
        mViewModel.getAll();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        btn_novo = (AppCompatButton) view.findViewById(R.id.btn_novo);
        btn_novo.setOnClickListener(botaoPesquisar);

        nome_colaborador = (TextInputEditText) view.findViewById(R.id.nome_colaborador);
       // nome_colaborador.setOnKeyListener(keyPesquisa);

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLista();
    }

    private void refreshLista() {
        mViewModel.getAll();
    }

    private void doBindings() {
        super.onStart();
        super.observeError(mViewModel);
        observeSucess();

        observeGetAll();
    }

    private void observeGetAll() {
        mViewModel.mList.observe(this, new Observer<List<Colaborador>>() {
            @Override
            public void onChanged(List<Colaborador> resultList) {
                prepareRecyclerView(resultList);
            }
        });
    }

    private void observeSucess() {
        mViewModel.sucess.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });
    }

    private void prepareRecyclerView(List<Colaborador> colaboradores) {
        mAdapter = new ColaboradorAdapter(colaboradores, mViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }


    private View.OnKeyListener keyPesquisa = new View.OnKeyListener() {

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {

            if ((nome_colaborador.length() % 3 == 0)) {
                mViewModel.getAll(nome_colaborador.getText().toString());
                //  Toast.makeText(context, "Multiplo", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
    };

    View.OnClickListener botaoPesquisar =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mViewModel.getAll(nome_colaborador.getText().toString());
        }
    };

}
