/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.engine.implementations;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testa as funções de um jogador
 *
 * @author Ricardo José Horta morais
 */
public class JogadorTest {

    /**
     * Test of equals method, of class Jogador.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Jogador jogador1 = new Jogador("Jogador1");
        Jogador jogador2 = new Jogador("Jogador2");
        Jogador jogador3 = new Jogador("Jogador1");

        boolean result = jogador1.equals(jogador2);
        assertEquals(false, result);

        result = jogador2.equals(jogador3);
        assertEquals(false, result);

        result = jogador1.equals(jogador3);
        assertEquals(true, result);

    }

    /**
     * Test of getNick method, of class Jogador.
     */
    @Test
    public void testGetNick() {
        System.out.println("equals");
        Jogador jogador1 = new Jogador("Jogador1");
        Jogador jogador2 = new Jogador("Jogador2");
        Jogador jogador3 = new Jogador("Jogador3");

        assertEquals("Jogador1", jogador1.getNick());
        assertEquals("Jogador2", jogador2.getNick());
        assertEquals("Jogador3", jogador3.getNick());

    }

}
