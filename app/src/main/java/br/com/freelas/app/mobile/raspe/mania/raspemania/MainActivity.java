package br.com.freelas.app.mobile.raspe.mania.raspemania;

import androidx.appcompat.app.AppCompatActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.firebase.LocalFirebase;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic.GenericInterface;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.model.LocalModel;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GenericInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LocalModel localModel = new LocalModel();
        localModel.nome = "testando";

        LocalFirebase localFirebase = new LocalFirebase(this);
        localFirebase.save(localModel);
       // Toast.makeText(this, "dsfsdfsdfsd", Toast.LENGTH_SHORT).show();
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
