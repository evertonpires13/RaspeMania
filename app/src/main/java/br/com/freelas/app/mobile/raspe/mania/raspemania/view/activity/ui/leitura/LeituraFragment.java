package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.leitura;

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

public class LeituraFragment extends Fragment {

    private LeituraViewModel mViewModel;

    public static LeituraFragment newInstance() {
        return new LeituraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leitura, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LeituraViewModel.class);
        // TODO: Use the ViewModel

        final TextView textView = getView().findViewById(R.id.text_leitura);

        mViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }

}
