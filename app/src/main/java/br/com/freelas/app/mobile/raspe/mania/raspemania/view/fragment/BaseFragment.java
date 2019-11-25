package br.com.freelas.app.mobile.raspe.mania.raspemania.view.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class BaseFragment extends Fragment {

    protected void observeError(BaseViewModel viewModel){
        viewModel.error.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean b) {
                Toast.makeText(getContext(), "ERRO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void observeSucess(BaseViewModel viewModel){
        viewModel.sucess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean b) {
                Toast.makeText(getContext(), "SUCESSO", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
