package br.com.freelas.app.mobile.raspe.mania.raspemania.view.old_componente;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class RotaComponente {

    public EditText editNome;
    public Spinner spinnerColaborador;
    public AppCompatButton mSalvar;
    public ListView lista;

    public Rota model;
    public List<Rota> modelList;
}
