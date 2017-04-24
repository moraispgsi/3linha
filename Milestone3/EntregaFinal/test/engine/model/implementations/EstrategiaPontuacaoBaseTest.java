/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.model.implementations;

import engine.implementations.EstrategiaPontuacaoBase;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Testa a estrategia de pontuação base
 *
 * @author Ricardo José Horta Morais
 */
public class EstrategiaPontuacaoBaseTest {

    /**
     * Test of calcularPontuacao method, of class EstrategiaPontuacaoBase.
     */
    @Test
    public void testCalcularPontuacao() {
        System.out.println("calcularPontuacao");

        EstrategiaPontuacaoBase estrategia = new EstrategiaPontuacaoBase();

        for (int i = 0; i < 30; i++) {
            assertEquals(i * 20, estrategia.calcularPontuacao(i, 0));
        }

    }

}
