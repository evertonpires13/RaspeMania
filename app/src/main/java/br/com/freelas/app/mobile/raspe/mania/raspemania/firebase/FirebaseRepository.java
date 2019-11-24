package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.BaseModel;

public abstract class FirebaseRepository<T> {

    static String TAG = "FirebaseRepository";

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
        model.dataUltimaAtualizacao = FieldValue.serverTimestamp();
    }

    public Task<DocumentReference> save(T entity) throws Exception {
        this.object = entity;
        //doBindings((BaseModel) object);
        return db.collection(collection).add(object) ;
    }
}
