package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}