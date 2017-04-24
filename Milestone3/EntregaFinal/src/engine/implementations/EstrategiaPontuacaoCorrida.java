/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.implementations;

import engine.interfaces.EstrategiaPontuacao;

/**
 * Estratégia de calculo da pontuação corrida
 *
 * @author Ricardo José Horta Morais
 */
public class EstrategiaPontuacaoCorrida implements EstrategiaPontuacao {

    private int INICIAL = 1000;
    private int MULTIPLICADOR_PECAS = 10;
    private int MULTIPLICADOR_TEMPO = 1;
    private int DIVISOR_TEMPO = 10;

    @Override
    public int calcularPontuacao(int pecasDestruidas, float tempoDeJogo) {
        int pontuacao = INICIAL + pecasDestruidas * MULTIPLICADOR_PECAS - ((int) tempoDeJogo / DIVISOR_TEMPO) * MULTIPLICADOR_TEMPO;
        return pontuacao < 0 ? 0 : pontuacao;

    }

}
