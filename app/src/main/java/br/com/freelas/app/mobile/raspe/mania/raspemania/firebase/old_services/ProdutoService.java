package br.com.freelas.app.mobile.raspe.mania.raspemania.firebase.old_services;

import br.com.freelas.app.mobile.raspe.mania.raspemania.helper.CollectionHelper;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;

public class ProdutoService extends GenericService<Produto> {

    /*--------------------------------------------------------------------------------------------*/
    public ProdutoService(GenericInterface genericInterface) {

        super(CollectionHelper.COLLECTION_PRODUTO, Produto.class, genericInterface);

    }
    /*--------------------------------------------------------------------------------------------*/
}
