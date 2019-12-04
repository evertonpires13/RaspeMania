package br.com.raspemania.firebase.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import br.com.raspemania.firebase.FirebaseRaspeMania;

public abstract class BaseRepository<T> {

    static String TAG = "FIREBASE_REPOSITORY";

    private String collection;
    protected FirebaseFirestore db;
    //public T object;
    private Class<T> clazz;

    /**
     * Constructor
     * @param collection
     * @param clazz
     */
    public BaseRepository(String collection, Class<T> clazz) {
        this.db = FirebaseRaspeMania.getDatabase();
        this.collection = collection;
        this.clazz = clazz;
    }

    /**
     * Get all documents by collection
     * @return
     * @throws Exception
     */
    public Task<QuerySnapshot> getAll() throws Exception {
        return db.collection(collection).get();

    }

    /**
     * Get documents by email
     * @return
     * @throws Exception
     */
    public Task<QuerySnapshot> getByEmail(String email) throws Exception {
        db.collection(collection)
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return null;
    }

    /**
     * Update document existent
     * @param entity
     * @param key
     * @return
     */
    public Task<Void> update(final T entity, final String key) {
        return db.collection(collection).document(key).set(entity, SetOptions.merge());
    }

    /**
     * Delete a document
     * @param key
     * @return
     */
    public Task<Void> delete(final String key) {
        return db.collection(collection).document(key).delete();
    }


}
