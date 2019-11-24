package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.produto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class ProdutoViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public ProdutoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ProdutoViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}