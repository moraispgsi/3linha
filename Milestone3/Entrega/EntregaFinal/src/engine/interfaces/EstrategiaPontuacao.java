/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.interfaces;

import java.io.Serializable;

/**
 * Representa uma estrategia de calculo de pontuação de um jogo
 *
 * @author Ricardo José Horta Morais
 */
public interface EstrategiaPontuacao extends Serializable {

    /**
     * Calcula a pontuação
     *
     * @param pecasDestruidas numero de peças destruidas
     * @param tempoDeJogo tempo em segundos do jogo
     * @return pontuação
     */
    public int calcularPontuacao(int pecasDestruidas, float tempoDeJogo);

}
