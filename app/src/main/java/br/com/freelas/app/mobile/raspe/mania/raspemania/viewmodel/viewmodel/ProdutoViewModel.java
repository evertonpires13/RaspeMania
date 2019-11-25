package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ExampleRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ProdutoRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class ProdutoViewModel extends BaseViewModel {

    static String TAG = "Produto";

    private ProdutoRepository service = new ProdutoRepository();

    private MutableLiveData<ModelExample> mExample;
    private MutableLiveData<List<ModelExample>> mExampleList;

    public ProdutoViewModel() {
        sucess = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    /**
     * Save produto
     * @param obj
     * @return
     */
    public void save(Produto obj) {
        try {
            service.save(obj)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            sucess.setValue(true);
                            Log.d(TAG, "Produto written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                            error.setValue(true);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            error.setValue(true);
        }
    }
}