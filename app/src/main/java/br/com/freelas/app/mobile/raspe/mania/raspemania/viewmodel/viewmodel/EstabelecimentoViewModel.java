package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class EstabelecimentoViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public EstabelecimentoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("EstabelecimentoViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}