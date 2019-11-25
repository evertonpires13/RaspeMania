package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class ColaboradorViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public ColaboradorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ColaboradorViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}