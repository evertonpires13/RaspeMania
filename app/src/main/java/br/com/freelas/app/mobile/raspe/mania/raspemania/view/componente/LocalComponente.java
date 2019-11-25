package br.com.freelas.app.mobile.raspe.mania.raspemania.view.componente;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import androidx.appcompat.widget.AppCompatButton;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Local;

public class LocalComponente {


    public  EditText editCodigo;
    public EditText editEndereco;
    public EditText editPercentual;
    public EditText editEstoque;
    public Spinner spinnerRota;
    public ListView lista;
    public AppCompatButton mSalvar;

    public Local model;
    public List<Local> modelList;


}
