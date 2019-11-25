package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;


public class BaseViewModel extends ViewModel {

    static String TAG = "BaseViewModel";

    //protected CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<String> error;
    public MutableLiveData<String> sucess;

    @Override
    protected void onCleared() {
        super.onCleared();
        //disposables.clear();
        Log.d(TAG, "onCleared");
    }
}
