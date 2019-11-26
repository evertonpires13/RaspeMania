package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("Raspe Mania");
    }

    public LiveData<String> getText() {
        return mText;
    }
}