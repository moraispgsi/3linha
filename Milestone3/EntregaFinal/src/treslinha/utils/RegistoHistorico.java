package treslinha.utils;

import java.io.Serializable;

/**
 * Representa um registo de histórico. Guarda dados sobre um jogo decorrido.
 *
 * @author Ricardo José Horta Morais
 */
public class RegistoHistorico implements Serializable {

    private int pontuacaoObtida;
    private int segundosDeJogo;
    private String tipoDeJogo;
    private int numeroPecasDestruidas;
    private int numeroRondasJogadas;

    /**
     * Construtor
     *
     * @param pontuacaoObtida pontuação obtida no jogo
     * @param segundosDeJogo segundos de jogo
     * @param tipoDeJogo tipo de jogo
     * @param numeroPecasDestruidas numero de peças destruidas
     * @param numeroRondasJogadas numero de rondas jogadas
     */
    public RegistoHistorico(int pontuacaoObtida, int segundosDeJogo, String tipoDeJogo, int numeroPecasDestruidas, int numeroRondasJogadas) {
        this.pontuacaoObtida = pontuacaoObtida;
        this.segundosDeJogo = segundosDeJogo;
        this.tipoDeJogo = tipoDeJogo;
        this.numeroPecasDestruidas = numeroPecasDestruidas;
        this.numeroRondasJogadas = numeroRondasJogadas;
    }

    /**
     * Devolve a pontuação obtida
     *
     * @return pontuação obtida
     */
    public int getPontuacaoObtida() {
        return pontuacaoObtida;
    }

    /**
     * Devolve os segundos de jogo
     *
     * @return segundos de jogo
     */
    public int getSegundosDeJogo() {
        return segundosDeJogo;
    }

    /**
     * Devolve o tipo de jogo
     *
     * @return tipo de jogo em formato string
     */
    public String getTipoDeJogo() {
        return tipoDeJogo;
    }

    /**
     * Devolve o número de peças destruidas
     *
     * @return número de peças destruidas
     */
    public int getNumeroPecasDestruidas() {
        return numeroPecasDestruidas;
    }

    /**
     * Devolve o número de rondas jogadas
     *
     * @return número de rondas jogadas
     */
    public int getNumeroRondasJogadas() {
        return numeroRondasJogadas;
    }

}
