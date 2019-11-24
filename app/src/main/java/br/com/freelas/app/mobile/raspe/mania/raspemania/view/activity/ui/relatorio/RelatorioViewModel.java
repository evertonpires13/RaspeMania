package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.relatorio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RelatorioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RelatorioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("RelatorioViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}