package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;

import java.util.List;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ExampleRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;

public class ExampleViewModel extends BaseViewModel {

    static String TAG = "ExampleViewModel";

    private ExampleRepository service = new ExampleRepository();

    public ExampleViewModel() {
        sucess = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public void save(ModelExample obj) {
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

    /**
     * Add a new document with a key
     * @param obj
     */
    public void saveRefId(ModelExample obj) {
        try {
            service.saveRefId(obj)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                            error.setValue(true);
                        }
                    });;
        } catch (Exception e) {
            e.printStackTrace();
            error.setValue(true);
        }
    }
}