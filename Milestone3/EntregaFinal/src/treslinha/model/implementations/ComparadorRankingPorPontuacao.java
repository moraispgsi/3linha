package treslinha.model.implementations;

import treslinha.utils.RegistoRanking;
import tads.interfaces.Comparador;

/**
 * Representa um comparador de ranking
 *
 * @author Ricardo José Horta Morais
 */
public class ComparadorRankingPorPontuacao implements Comparador<RegistoRanking> {

    @Override
    public boolean comparar(RegistoRanking esquerdo, RegistoRanking direito) {
        return esquerdo.getPontuacao() < direito.getPontuacao();
    }
    
    /**
     * Devolve o comparador de ranking por pontuação
     * @return comparador de ranking por pontuação
     */
    public static ComparadorRankingPorPontuacao getComparador() {
        return new ComparadorRankingPorPontuacao();
    }

}
