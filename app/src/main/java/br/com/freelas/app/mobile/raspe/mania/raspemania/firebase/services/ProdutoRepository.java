package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.FirebaseRepository;
import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;

public class ProdutoRepository extends FirebaseRepository<Produto> {

    public ProdutoRepository() {
        super(CollectionHelper.COLLECTION_PRODUTO, Produto.class);
    }

}