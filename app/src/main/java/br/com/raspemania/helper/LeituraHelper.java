package br.com.raspemania.helper;

import br.com.raspemania.model.entidade.Leitura;
import br.com.raspemania.model.entidade.PremiacaoList;

public class LeituraHelper {

    public static String getPremiacao (Leitura leitura) {
        long qtd = 0;
        Double valor = 0.0;

        for(PremiacaoList premiacao : leitura.premiacaoList){
            qtd = qtd + premiacao.quantidadePremiada;
            valor = valor + (premiacao.valorPremiado*premiacao.quantidadePremiada);
        }
        return String.valueOf(qtd) + " /R$ " + String.valueOf(valor);
    }

    public static Double getTotalPremiado (Leitura leitura) {

        Double valor = 0.0;

        for(PremiacaoList premiacao : leitura.premiacaoList){
            valor = valor + (premiacao.valorPremiado*premiacao.quantidadePremiada);
        }
        return valor;
    }

    public static String getValorRetirado(Leitura leitura) {
        int qtVendida = leitura.quantidadeVendida;
        Double valorProduto = leitura.produto.valor;
        Double comissao = leitura.cliente.porcentagem;
        Double totalPremiado = getTotalPremiado(leitura);

        Double valorRetirado = ((qtVendida*valorProduto)-((qtVendida*valorProduto)*(comissao/100D)))-totalPremiado;

        return "R$ "+ String.valueOf(valorRetirado);
    }

}
