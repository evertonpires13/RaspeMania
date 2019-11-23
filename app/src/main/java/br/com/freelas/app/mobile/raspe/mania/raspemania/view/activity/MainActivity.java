package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.base.BaseActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.GenericInterface;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements GenericInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public void sucessWindow(String node) {
        Toast.makeText(this, "sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucessList(String node) {
        Toast.makeText(this, "sucesso lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String mensagem) {
        Toast.makeText(this, "erro ", Toast.LENGTH_SHORT).show();
    }


}
