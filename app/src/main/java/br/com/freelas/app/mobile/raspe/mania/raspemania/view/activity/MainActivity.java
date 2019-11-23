package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.base.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton rota = findViewById(R.id.example_btn);

        rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ExampleActivity.class);
                startActivity(intent);
            }
        });
    }
}
