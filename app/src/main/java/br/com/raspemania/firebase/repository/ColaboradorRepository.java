package br.com.raspemania.firebase.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import br.com.raspemania.firebase.FirebaseRaspeMania;
import br.com.raspemania.helper.CollectionHelper;
import br.com.raspemania.helper.ConstantHelper;
import br.com.raspemania.model.consulta.RelatorioConsulta;
import br.com.raspemania.model.entidade.Colaborador;

public class ColaboradorRepository extends BaseRepository<Colaborador> {

    static String TAG = "COLABORADOR_REPOSITORY";

    private String collection = CollectionHelper.COLLECTION_COLABORADOR;
    protected FirebaseFirestore db;
    public Colaborador object;

    /**
     * Constructor
     */

    public ColaboradorRepository() {
        super(CollectionHelper.COLLECTION_COLABORADOR, Colaborador.class);
    }

    /**
     * @param entity
     * @return Task<Void>
     * @throws Exception
     */
    public Task<Void> saveRefId(Colaborador entity) throws Exception {

        this.db = FirebaseRaspeMania.getDatabase();

        DocumentReference ref = db.collection(collection).document();
        String myId = ref.getId();

        this.object = entity;
        object.key = myId;
        object.status = ConstantHelper.ATIVO;
        object.perfil = ConstantHelper.PERFIL_COLABORADOR;
        object.excluido = ConstantHelper.NAO_EXCLUIDO;

        return db.collection(collection).document(myId).set(object);
    }

    public Task<QuerySnapshot> getAll(String apelido) throws Exception {

        this.db = FirebaseRaspeMania.getDatabase();

        Query query = db.collection(collection);
        query = query.whereGreaterThanOrEqualTo("apelido", apelido);
       // query = query.whereEqualTo("excluido", ConstantHelper.NAO_EXCLUIDO);

        return query.get();
    }


}
