package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.relatorio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class RelatorioViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public RelatorioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("RelatorioViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}