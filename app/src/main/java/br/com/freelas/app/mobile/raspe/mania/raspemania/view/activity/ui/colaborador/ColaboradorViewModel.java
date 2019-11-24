package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.colaborador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ColaboradorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ColaboradorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ColaboradorViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}