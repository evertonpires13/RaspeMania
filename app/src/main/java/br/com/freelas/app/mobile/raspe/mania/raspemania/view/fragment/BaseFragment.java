package br.com.freelas.app.mobile.raspe.mania.raspemania.view.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class BaseFragment extends Fragment {

    protected void observeError(BaseViewModel viewModel){
        viewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
               // Snackbar.make(view, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
