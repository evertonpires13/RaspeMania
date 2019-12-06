package br.com.raspemania.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.model.entidade.Cliente;
import br.com.raspemania.model.entidade.Colaborador;
import br.com.raspemania.model.entidade.Rota;
import br.com.raspemania.view.activity.RelatorioActivity;
import br.com.raspemania.viewmodel.ClienteViewModel;
import br.com.raspemania.viewmodel.ColaboradorViewModel;
import br.com.raspemania.viewmodel.RotaViewModel;

public class RelatorioFragment extends BaseFragment {

    private Context context = getContext();
    //private LeituraViewModel mViewModel;
    private ClienteViewModel mViewModelCliente;
    private RotaViewModel mViewModelRota;
    private ColaboradorViewModel mViewModelColaborador;
    private AppCompatButton mBuscar;

    private Spinner spinnerCliente;
    private Spinner spinnerRota;
    private Spinner spinnerColaborador;

    public static LeituraFragment newInstance() {
        return new LeituraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_relatorio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        hideProgressDialog();

        context = getContext();

        //mViewModel = ViewModelProviders.of(this).get(LeituraViewModel.class);
        mViewModelCliente = ViewModelProviders.of(this).get(ClienteViewModel.class);
        mViewModelRota = ViewModelProviders.of(this).get(RotaViewModel.class);
        mViewModelColaborador = ViewModelProviders.of(this).get(ColaboradorViewModel.class);

        doBindings();

        mViewModelCliente.getAll();
        mViewModelRota.getAll();
        mViewModelColaborador.getAll();

        mBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RelatorioActivity.class));
            }
        });
    }

    private void doBindings() {
        super.onStart();
        super.observeError(mViewModelCliente);
        super.observeError(mViewModelRota);
        super.observeError(mViewModelColaborador);
        observeSucessSpinnerCliente();
        observeSucessSpinnerColaborador();
        observeSucessSpinnerRota();
    }

    private void observeSucessSpinnerCliente() {

        mViewModelCliente.mList.observe(this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(List<Cliente> resultList) {
                ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(context, R.layout.item_spinner_default, resultList);
                spinnerCliente.setAdapter(adapter);
            }
        });
    }

    private void observeSucessSpinnerRota() {

        mViewModelRota.mList.observe(this, new Observer<List<Rota>>() {
            @Override
            public void onChanged(List<Rota> resultList) {
                ArrayAdapter<Rota> adapter = new ArrayAdapter<>(context, R.layout.item_spinner_default, resultList);
                spinnerRota.setAdapter(adapter);
            }
        });
    }

    private void observeSucessSpinnerColaborador() {

        mViewModelColaborador.mList.observe(this, new Observer<List<Colaborador>>() {
            @Override
            public void onChanged(List<Colaborador> resultList) {
                ArrayAdapter<Colaborador> adapter = new ArrayAdapter<>(context, R.layout.item_spinner_default, resultList);
                spinnerColaborador.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBuscar = view.findViewById(R.id.btnBuscar);
        spinnerCliente = view.findViewById(R.id.spinnerCliente);
        spinnerColaborador = view.findViewById(R.id.spinnerColaborador);
        spinnerRota = view.findViewById(R.id.spinnerRota);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideProgressDialog();
    }
}
