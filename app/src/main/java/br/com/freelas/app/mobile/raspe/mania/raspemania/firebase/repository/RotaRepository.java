package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRaspeMania;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;

public class RotaRepository extends BaseRepository<Rota> {

    static String TAG = "ROTA_REPOSITORY";

    private String collection = CollectionHelper.COLLECTION_ROTA;
    protected FirebaseFirestore db;
    public Rota object;

    /**
     * Constructor
     */

    public RotaRepository() {
        super(CollectionHelper.COLLECTION_ROTA, Rota.class);
    }

    /**
     *
     * @param entity
     * @return Task<Void>
     * @throws Exception
     */
    public Task<Void> saveRefId(Rota entity) throws Exception {

        this.db = FirebaseRaspeMania.getDatabase();

        DocumentReference ref = db.collection(collection).document();
        String myId = ref.getId();

        this.object = entity;
        object.key = myId;

        return db.collection(collection).document(myId).set(object);
    }
}
