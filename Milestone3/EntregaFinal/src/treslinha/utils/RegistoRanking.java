package treslinha.utils;

import java.io.Serializable;
import java.util.Objects;
import engine.implementations.Jogador;

/**
 *
 * Representa um registo de ranking. Guarda o jogador e a sua pontuação
 *
 * @author Ricardo José Horta Morais
 */
public class RegistoRanking implements Serializable {

    private Jogador jogador;
    private int pontuacao;

    /**
     * Construtor
     *
     * @param jogador jogador
     * @param pontuacao pontuação
     */
    public RegistoRanking(Jogador jogador, int pontuacao) {
        this.jogador = jogador;
        this.pontuacao = pontuacao;
    }

    /**
     * Devolve o jogador
     *
     * @return jogador
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     * Devolve a pontuação
     *
     * @return pontuação
     */
    public int getPontuacao() {
        return pontuacao;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof RegistoRanking) {
            RegistoRanking registo = (RegistoRanking) obj;
            return jogador.equals(registo.getJogador());
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.jogador);
        return hash;
    }

}
