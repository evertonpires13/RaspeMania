package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRaspeMania;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.ModelExample;

public class ExampleRepository extends FirebaseRepository<ModelExample> {

        static String TAG = "EXAMPLE_REPOSITORY";

        private String collection = CollectionHelper.COLLECTION_EXAMPLE;
        protected FirebaseFirestore db;
        public ModelExample object;

        /**
         * Constructor
         */

        public ExampleRepository() {
            super(CollectionHelper.COLLECTION_EXAMPLE, ModelExample.class);
        }

        /**
         *
         * @param entity
         * @return Task<Void>
         * @throws Exception
         */
        public Task<Void> saveRefId(ModelExample entity) throws Exception {

            this.db = FirebaseRaspeMania.getDatabase();

            this.object = entity;

            DocumentReference ref = db.collection(collection).document();
            String myId = ref.getId();

            object.key = myId;

            return db.collection(collection).document(myId).set(object);
        }
}
