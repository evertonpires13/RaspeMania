package br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ui.viewmodel;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import java.util.List;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ExampleService;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class ExampleViewModel extends BaseViewModel {

    static String TAG = "ExampleViewModel";

    private MutableLiveData<String> mSucess;
    private MutableLiveData<String> mError;
    private MutableLiveData<ModelExample> mExample;
    private MutableLiveData<List<ModelExample>> mExampleList;

    private MutableLiveData<Boolean> error;
    private MutableLiveData<Boolean> sucess;

    private ExampleService service = new ExampleService();

    public ExampleViewModel() {
        mSucess = new MutableLiveData<>();
        mSucess.setValue("funcionou");
    }

    public LiveData<String> save(ModelExample modelExample) {

        try {
            service.save(modelExample)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mSucess;
    }
}