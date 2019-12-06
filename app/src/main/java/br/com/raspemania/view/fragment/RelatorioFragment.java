package br.com.raspemania.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProviders;

import br.com.raspemania.R;
import br.com.raspemania.view.activity.RelatorioActivity;
import br.com.raspemania.viewmodel.LeituraViewModel;

public class RelatorioFragment extends BaseFragment {

    private Context context = getContext();
    private LeituraViewModel mViewModel;
    private AppCompatButton mBuscar;

    public static LeituraFragment newInstance() {
        return new LeituraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        hideProgressDialog();
        return inflater.inflate(R.layout.fragment_relatorio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        hideProgressDialog();

        context = getContext();
        mViewModel = ViewModelProviders.of(this).get(LeituraViewModel.class);

        mBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RelatorioActivity.class));
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBuscar = view.findViewById(R.id.btnBuscar);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
