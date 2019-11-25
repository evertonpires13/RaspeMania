package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRaspeMania;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;

public class ProdutoRepository extends FirebaseRepository<Produto> {

    static String TAG = "PPRODUTO_REPOSITORY";

    private String collection = CollectionHelper.COLLECTION_EXAMPLE;
    protected FirebaseFirestore db;
    public Produto object;

    /**
     * Constructor
     */

    public ProdutoRepository() {
        super(CollectionHelper.COLLECTION_EXAMPLE, Produto.class);
    }

    /**
     *
     * @param entity
     * @return Task<Void>
     * @throws Exception
     */
    public Task<Void> saveRefId(Produto entity) throws Exception {

        this.db = FirebaseRaspeMania.getDatabase();

        DocumentReference ref = db.collection(collection).document();
        String myId = ref.getId();

        this.object = entity;
        object.key = myId;

        return db.collection(collection).document(myId).set(object);
    }
}
