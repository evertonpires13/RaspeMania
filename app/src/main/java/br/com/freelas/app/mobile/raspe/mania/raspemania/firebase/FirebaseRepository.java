package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.BaseModel;

public abstract class FirebaseRepository<T> {

    static String TAG = "FIREBASE_REPOSITORY";

    private String collection;
    protected FirebaseFirestore db;
    public T object;
    private Class<T> classe;

    /**
     * Constructor
     * @param collection
     * @param classe
     */
    public FirebaseRepository(String collection, Class<T> classe) {
        this.db = FirebaseRaspeMania.getDatabase();
        this.collection = collection;
        this.classe = classe;
    }

    private void doBindings(BaseModel model){
        //model.dataUltimaAtualizacao = FieldValue.serverTimestamp();
    }

    /**
     * Add a new document with a generated id
     * @param entity
     * @return Task<DocumentReference>
     * @throws Exception
     */
    public Task<DocumentReference> save(T entity) throws Exception {
        this.object = entity;
        return db.collection(collection).add(object);
    }

    public Task<QuerySnapshot> getAll() throws Exception {
        return db.collection(collection).get();

    }

}
