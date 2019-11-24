package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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