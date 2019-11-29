package br.com.raspemania.firebase.old_services;


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

import br.com.raspemania.firebase.FirebaseRaspeMania;
import br.com.raspemania.model.BaseModel;

public abstract class GenericService<T> {

    /*--------------------------------------------------------------------------------------------*/
    public static final String RETORNO_LOAD_ALL = "loadALL";
    public static final String RETORNO_LOAD_KEY = "loadKey";
    public static final String RETORNO_SAVE = "save";
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
    public GenericService(String node, Class<T> classe, GenericInterface genericInterface) {

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

            BaseModel model = (BaseModel) entity;
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
    public void load() {

        try {

            db.collection(node)
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
                            genericInterface.sucessList(RETORNO_LOAD_ALL);

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            genericInterface.error("Erro ao retornar os dados : " + e.toString());
        }

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
                                genericInterface.sucessWindow(RETORNO_LOAD_KEY);

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

                        BaseModel model = (BaseModel) dado;
                        model.chave = documentReference.getId();
                        Calendar calendar = Calendar.getInstance();
                        //model.dataCadastro = calendar.getTimeInMillis();

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

        BaseModel model = (BaseModel) entity;
        this.dado = entity;
        db.collection(node).document(model.chave)
                .set(entity)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dado = entity;
                        genericInterface.sucessWindow(RETORNO_SAVE);
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
            BaseModel model = (BaseModel) entity;
            delete(model.chave);
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    public void delete(T dados) {

        BaseModel model = (BaseModel) dados;
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
