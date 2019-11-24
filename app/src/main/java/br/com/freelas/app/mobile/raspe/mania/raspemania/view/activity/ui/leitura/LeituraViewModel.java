package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.leitura;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class LeituraViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public LeituraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("LeituraViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}