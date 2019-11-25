package br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ExampleRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services.ProdutoRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.BaseViewModel;

public class ProdutoViewModel extends BaseViewModel {

    static String TAG = "Produto";

    private ProdutoRepository service = new ProdutoRepository();

    public MutableLiveData<Produto> mObject;
    public MutableLiveData<List<Produto>> mList;

    public ProdutoViewModel() {
        sucess = new MutableLiveData<>();
        error = new MutableLiveData<>();
        mList = new MutableLiveData<List<Produto>>();
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
                            error.setValue(true);

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            error.setValue(true);
        }
    }

    public void getAll() {
        try {
            service.getAll()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot querySnapshot) {
                            sucess.setValue(true);
                            mList.setValue(querySnapshot.toObjects(Produto.class));
                            Log.d(TAG, "Listou todos os produtos");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            error.setValue(true);
                            Log.w(TAG, "Error getting document", e);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            error.setValue(true);
        }
    }
}