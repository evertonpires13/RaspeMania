package br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;

public class ColaboradorComponente {

    public EditText editNome;
    public EditText editApelido;
    public EditText editSenha;
    public Spinner spinnerPerfil;
    public Spinner spinnerColaborador;
    public ListView lista;
    public AppCompatButton mSalvar;

    public Colaborador model;
    public List<Colaborador> modelList;

}
