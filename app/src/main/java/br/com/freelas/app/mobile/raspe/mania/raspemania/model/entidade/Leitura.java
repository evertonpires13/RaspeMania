package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import br.com.freelas.app.mobile.raspe.mania.raspemania.model.BaseModel;


public class Leitura extends BaseModel implements Serializable {

    public Locale local;             //salvar sem chave
    public Produto produto;         //salvar sem chave
    public int quantidadeVendida;
    public List<PremiacaoList> premiacaoList;
    public long hasReposicao;
    public int quantidadeReposicao;

    //total premiado = (premiacaoList.quantidadePremiada * premiacaoList.valorPremiado) + [n]
    //valor retirado = ((quantidadeVendida x produto.valor) - local.porcentagem) - total premiado
}
