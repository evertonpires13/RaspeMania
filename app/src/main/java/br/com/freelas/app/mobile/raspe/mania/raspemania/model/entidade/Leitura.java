package br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade;

import java.util.List;

public class Leitura extends BaseModel {

    public Local local;             //salvar sem chave
    public Produto produto;         //salvar sem chave
    public int quantidadeVendida;
    public List<PremiacaoList> premiacaoList;
    public long hasReposicao;
    public int quantidadeReposicao;

    //total premiado = (premiacaoList.quantidadePremiada * premiacaoList.valorPremiado) + [n]
    //valor retirado = ((quantidadeVendida x produto.valor) - local.porcentagem) - total premiado
}
