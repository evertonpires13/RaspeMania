package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.base.BaseActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ExampleViewModel;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ViewModelFactory;

public class ExampleActivity extends BaseActivity {

    //Create viewModel
    private ViewModelProvider.Factory viewModelFactory = ViewModelFactory.createFor(new ExampleViewModel());
    private ExampleViewModel viewModel;

    private AppCompatButton mSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExampleViewModel.class);

        mSalvar = findViewById(R.id.salvar_btn);

        mSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelExample model = new ModelExample();
                model.nome = "Daniele";

                //viewModel.save(model);

            }
        });
    }


}