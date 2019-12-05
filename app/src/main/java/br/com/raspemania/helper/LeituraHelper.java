package br.com.raspemania.helper;

import br.com.raspemania.model.entidade.Leitura;
import br.com.raspemania.model.entidade.PremiacaoList;

public class LeituraHelper {

    public static String getPremiacao (Leitura leitura) {
        long qtd = 0;
        float valor = 0;

        for(PremiacaoList premiacao : leitura.premiacaoList){
            qtd = qtd + premiacao.quantidadePremiada;
            valor = valor + (premiacao.valorPremiado*premiacao.quantidadePremiada);
        }
        return String.valueOf(qtd) + " /R$ " + String.valueOf(valor);
    }

    public static Float getTotalPremiado (Leitura leitura) {

        float valor = 0;

        for(PremiacaoList premiacao : leitura.premiacaoList){
            valor = valor + (premiacao.valorPremiado*premiacao.quantidadePremiada);
        }
        return valor;
    }

    public static String getValorRetirado(Leitura leitura) {
        float qtVendida = leitura.quantidadeVendida;
        float valorProduto = leitura.produto.valor;
        float comissao = leitura.local.porcentagem;
        float totalPremiado = getTotalPremiado(leitura);

        float valorRetirado = ((qtVendida*valorProduto)-((qtVendida*valorProduto)*(comissao/100F)))-totalPremiado;

        return "R$ "+ String.valueOf(valorRetirado);
    }

}
