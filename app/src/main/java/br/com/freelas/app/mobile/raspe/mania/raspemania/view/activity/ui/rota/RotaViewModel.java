package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.rota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class RotaViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public RotaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("RotaViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}