package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.generic;


import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;

public abstract class GenericFirebase<T> {

    /*--------------------------------------------------------------------------------------------*/
    private String node;
    protected FirebaseFirestore db;
    public T dado;
    private Boolean retornoB;
    public List<T> dados;
    protected GenericInterface genericInterface;
    private Class<T> classe;
    public String chaveDelete;

    /*--------------------------------------------------------------------------------------------*/

    /**
     * @param node
     */
    public GenericFirebase(String node, Class<T> classe, GenericInterface genericInterface) {

        this.node = node;
        this.db = FirebaseRaspeMania.getDatabase();
        this.classe = classe;
        this.genericInterface = genericInterface;

    }

    /*--------------------------------------------------------------------------------------------*/

    /**
     * @param entity
     * @return
     */
    public void save(T entity) {

        try {

            GenericModel model = (GenericModel) entity;
            if (model.chave == null || model.equals("")) {
                news(entity);
            } else {
                update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            genericInterface.error("Erro ao salvar. Favor verificar : " + e.toString());
        }

    }
    /*--------------------------------------------------------------------------------------------*/

    /**
     * @param campo
     * @param valor
     * @return
     */
    public void load(String campo, String valor) {
        load(campo, valor, null);
    }

    /*--------------------------------------------------------------------------------------------*/

    /**
     * @param chave
     * @return
     */
    public void load(String chave) {

        try {

            DocumentReference docRef = db.collection(node).document(chave);
            docRef.get()
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //erro
                            genericInterface.error("Erro ao retornar os dados : " + e.toString());
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            //sucesso
                            if (documentSnapshot.exists()) {
                                dado = documentSnapshot.toObject(classe);
                                //Log.e("Dados", " tem dados sim " + dado.toString());
                                genericInterface.sucessWindow(node);
                            } else {
                                genericInterface.error("documento não existe");
                            }

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            genericInterface.error("Erro ao retornar os dados : " + e.toString());
        }

    }

    /*--------------------------------------------------------------------------------------------*/

    /**
     * @param entity
     */
    private void news(T entity) {


        this.dado = entity;
        db.collection(node)
                .add(entity)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        GenericModel model = (GenericModel) dado;
                        model.chave = documentReference.getId();
                        Calendar calendar = Calendar.getInstance();
                        model.dataCadastro = calendar.getTimeInMillis();

                        update(dado);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        genericInterface.error("Erro ao retornar os dados : " + e.toString());

                    }
                });
    }

    /*--------------------------------------------------------------------------------------------*/

    /**
     * @param entity
     */
    private void update(final T entity) {

        GenericModel model = (GenericModel) entity;
        this.dado = entity;
        db.collection(node).document(model.chave)
                .set(entity)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dado = entity;
                        genericInterface.sucessWindow(node);
                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Log.w(TAG, "Error writing document", e);
                        genericInterface.error("Erro ao retornar os dados : " + e.toString());
                    }
                });


    }

    /*--------------------------------------------------------------------------------------------*/
    public void delete(final String chave) {

        db.collection(node)
                .document(chave)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        chaveDelete = chave;
                        genericInterface.sucessWindow(node);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        genericInterface.error("Erro ao excluir os dados : " + e.toString());
                    }
                });
    }

    /*--------------------------------------------------------------------------------------------*/
    public void load(String campo1, String valor1, String campo2, String valor2, String campo3, String valor3, String orderBy) {

        try {

            if (orderBy == null) {
                orderBy = "chave";
            }

            db.collection(node)
                    .whereEqualTo(campo1, valor1)
                    .whereEqualTo(campo2, valor2)
                    .whereEqualTo(campo3, valor3)
                    //     .orderBy(orderBy)
                    .get()
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            genericInterface.error("Erro ao ler. Favor verificar : " + e.toString());
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {

                            dados = documentSnapshots.toObjects(classe);
                            genericInterface.sucessList(node);

                        }
                    });


        } catch (Exception e) {
            genericInterface.error(e.toString());
            e.printStackTrace();
            genericInterface.error("Erro ao ler : " + e.toString());

        }

    }

    /*--------------------------------------------------------------------------------------------*/
    public void load(String campo1, Object valor1, String campo2, Object valor2, String orderBy) {

        try {

            CollectionReference citiesRef = db.collection(node);

            citiesRef.whereEqualTo(campo1, valor1)
                    .whereEqualTo(campo2, valor2)
                    .get()
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            genericInterface.error(e.toString());

                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {

                            if (documentSnapshots != null) {
                                Log.e("Qtd", documentSnapshots.size() + "");
                            }
                            dados = documentSnapshots.toObjects(classe);
                            genericInterface.sucessList(node);

                        }
                    });




/*
            if (orderBy == null) {
                orderBy = "chave";
            }

            db.collection(node)
                    .whereEqualTo(campo1, valor1)
                    .whereEqualTo(campo2, valor2)
                    //     .orderBy(orderBy)
                    .get()
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            genericInterface.error("Erro ao ler. Favor verificar : " + e.toString());
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {

                            dados = documentSnapshots.toObjects(classe);
                            genericInterface.sucessList(node);

                        }
                    });


           */


        } catch (Exception e) {
            genericInterface.error(e.toString());
            e.printStackTrace();
            genericInterface.error("Erro ao ler : " + e.toString());

        }

    }
    /*--------------------------------------------------------------------------------------------*/


    /**
     * @param campo
     * @param valor
     * @param orderBy
     */
    public void load(String campo, Object valor, String orderBy) {


        try {

            if (orderBy == null) {
                orderBy = "chave";
            }

            db.collection(node)
                    .whereEqualTo(campo, valor)
                    //     .orderBy(orderBy)
                    .get()
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            genericInterface.error("Erro ao ler. Favor verificar : " + e.toString());
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {

                            dados = documentSnapshots.toObjects(classe);
                            genericInterface.sucessList(node);

                        }
                    });


        } catch (Exception e) {
            genericInterface.error(e.toString());
            e.printStackTrace();
            genericInterface.error("Erro ao ler : " + e.toString());

        }

    }

    /*--------------------------------------------------------------------------------------------*/
    public void save(List<T> dados) {

        for (T entity : dados) {
            save(entity);
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    public void delete(List<T> dados) {

        for (T entity : dados) {
            GenericModel model = (GenericModel) entity;
            delete(model.chave);
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    public void delete(T dados) {

        GenericModel model = (GenericModel) dados;
        delete(model.chave);

    }

    /*--------------------------------------------------------------------------------------------*/
    public void update(String chave, String campo1, Object valor1, String campo2, Object valor2) {

        db.collection(node).document(chave)
                .update(
                        campo1, valor1
                        , campo2, valor2
                ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                genericInterface.error("Erro ao ler. Favor verificar : " + e.toString());
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                genericInterface.sucessWindow("Sucesso");
            }
        });

    }

    /*--------------------------------------------------------------------------------------------*/
    public void update(String chave, String campo1, Object valor1) {

        db.collection(node).document(chave)
                .update(
                        campo1, valor1
                ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                genericInterface.error("Erro ao ler. Favor verificar : " + e.toString());
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                genericInterface.sucessWindow("Sucesso");
            }
        });

    }
    /*--------------------------------------------------------------------------------------------*/


}
