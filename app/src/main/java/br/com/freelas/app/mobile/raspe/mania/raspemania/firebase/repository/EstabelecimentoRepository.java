package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRaspeMania;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.ConstantHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Estabelecimento;

public class EstabelecimentoRepository extends BaseRepository<Estabelecimento> {

    static String TAG = "ESTABELECIMENTO_REPOSITORY";

    private String collection = CollectionHelper.COLLECTION_ESTABELECIMENTO;
    protected FirebaseFirestore db;
    public Estabelecimento object;

    /**
     * Constructor
     */

    public EstabelecimentoRepository() {
        super(CollectionHelper.COLLECTION_ESTABELECIMENTO, Estabelecimento.class);
    }

    /**
     *
     * @param entity
     * @return Task<Void>
     * @throws Exception
     */
    public Task<Void> saveRefId(Estabelecimento entity) throws Exception {

        this.db = FirebaseRaspeMania.getDatabase();

        DocumentReference ref = db.collection(collection).document();
        String myId = ref.getId();

        this.object = entity;
        object.key = myId;
        object.status = ConstantHelper.ATIVO;

        return db.collection(collection).document(myId).set(object);
    }
}
