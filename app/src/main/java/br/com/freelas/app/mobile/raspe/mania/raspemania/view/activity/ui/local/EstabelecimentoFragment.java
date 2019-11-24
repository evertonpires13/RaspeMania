package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.local;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.colaborador.ColaboradorFragment;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.colaborador.ColaboradorViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.leitura.LeituraViewModel;

public class EstabelecimentoFragment extends Fragment {

    private EstabelecimentoViewModel mViewModel;

    public static EstabelecimentoFragment newInstance() {
        return new EstabelecimentoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_estabelecimento, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EstabelecimentoViewModel.class);
        // TODO: Use the ViewModel

        final TextView textView = getView().findViewById(R.id.text_estabelecimento);

        mViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }

}
