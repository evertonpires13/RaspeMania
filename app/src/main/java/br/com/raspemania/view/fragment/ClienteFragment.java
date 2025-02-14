package br.com.raspemania.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.helper.SpinnerHelper;
import br.com.raspemania.model.entidade.Cliente;
import br.com.raspemania.view.activity.ClienteActivity;
import br.com.raspemania.view.activity.LeituraActivity;
import br.com.raspemania.view.adapter.ClienteAdapter;
import br.com.raspemania.viewmodel.ClienteViewModel;

public class ClienteFragment extends BaseFragment {

    private Context context = getContext();
    private ClienteViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ClienteAdapter mAdapter;

    private AppCompatButton mNovoBtn;
    private AutoCompleteTextView textBusca;


    public static ClienteFragment newInstance() {
        return new ClienteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cliente, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getContext();
        mViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);
        doBindings();
        mViewModel.getAll();

        mNovoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ClienteActivity.class));
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNovoBtn = view.findViewById(R.id.btn_novo);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        textBusca = (AutoCompleteTextView) view.findViewById(R.id.textBusca);

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
        mViewModel.mList.observe(this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(List<Cliente> resultList) {
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

    private void prepareRecyclerView(final List<Cliente> clientes) {
        mAdapter = new ClienteAdapter(clientes, mViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(getContext(), R.layout.custom_autocomplete_list, R.id.text_view_list_item, clientes);
        textBusca.setAdapter(adapter);

        textBusca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<Cliente> clientesNew = new ArrayList<>();

                Cliente selected = (Cliente) adapterView.getAdapter().getItem(i);
                clientesNew.add(selected);

                mAdapter = new ClienteAdapter(clientesNew, mViewModel);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });

        textBusca.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (textBusca.getText().toString().length() == 0) {
                    mAdapter = new ClienteAdapter(clientes, mViewModel);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });


        hideProgressDialog();
    }
}
