package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstabelecimentoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EstabelecimentoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("EstabelecimentoViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}