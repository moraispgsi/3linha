/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.engine.implementations;

import pt.ips.pa.fase2.model.engine.interfaces.EstrategiaPontuacao;

/**
 * Estratégia de calculo da pontuação base
 *
 * @author Ricardo José Horta Morais
 */
public class EstrategiaPontuacaoBase implements EstrategiaPontuacao {

    private int MULTIPLICADOR = 20;

    @Override
    public int calcularPontuacao(int pecasDestruidas, float tempoDeJogo) {
        return MULTIPLICADOR * pecasDestruidas;
    }

}
