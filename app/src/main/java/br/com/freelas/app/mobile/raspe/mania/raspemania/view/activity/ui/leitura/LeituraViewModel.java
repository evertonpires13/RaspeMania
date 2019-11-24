package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.leitura;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeituraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LeituraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("LeituraViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}