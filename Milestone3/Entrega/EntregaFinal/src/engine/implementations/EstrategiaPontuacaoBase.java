
package engine.implementations;

import engine.interfaces.EstrategiaPontuacao;

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
