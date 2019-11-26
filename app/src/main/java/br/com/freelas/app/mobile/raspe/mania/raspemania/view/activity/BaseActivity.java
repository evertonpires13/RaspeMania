package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

    public class BaseActivity extends AppCompatActivity {

    protected void observeError(BaseViewModel viewModel){
        viewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                // Snackbar.make(view, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    /*protected void observeSucess(BaseViewModel viewModel){
        viewModel.sucess.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                // Snackbar.make(view, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }*/
}
