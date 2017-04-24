package pt.ips.pa.fase2.controller.treslinha.implementations;

import pt.ips.pa.fase1.tads.interfaces.Comparador;

/**
 * Representa um comparador de ranking
 *
 * @author Ricardo José Horta Morais
 */
public class ComparadorRankingPorPontuacao implements Comparador<RegistoRanking> {

    private static ComparadorRankingPorPontuacao instancia;

    @Override
    public boolean comparar(RegistoRanking esquerdo, RegistoRanking direito) {
        return esquerdo.getPontuacao() < direito.getPontuacao();
    }

    public static ComparadorRankingPorPontuacao getComparador() {

        if (instancia == null) {
            instancia = new ComparadorRankingPorPontuacao();
        }

        return instancia;
    }

}
