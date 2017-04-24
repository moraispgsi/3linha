/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.model.implementations;

import engine.implementations.EstrategiaPontuacaoCorrida;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testa a estrategia de pontuação base
 *
 * @author Ricardo José Horta Morais
 */
public class EstrategiaPontuacaoCorridaTest {

    public EstrategiaPontuacaoCorridaTest() {
    }

    /**
     * Test of calcularPontuacao method, of class EstrategiaPontuacaoCorrida.
     */
    @Test
    public void testCalcularPontuacao() {
        System.out.println("calcularPontuacao");

        EstrategiaPontuacaoCorrida estrategia = new EstrategiaPontuacaoCorrida();

        assertEquals(1998, estrategia.calcularPontuacao(100, 20));
        assertEquals(1080, estrategia.calcularPontuacao(8, 5));

    }

}
