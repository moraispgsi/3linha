/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.model.implementations;

import treslinha.utils.EventoJogo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testa a classe EventoJogo
 *
 * @author Ricardo José Horta Morais
 */
public class EventoJogoTest {

    public EventoJogoTest() {
    }

    /**
     * Test of getEvento method, of class EventoJogo.
     */
    @Test
    public void testGetEvento() {
        System.out.println("getEvento");
        EventoJogo evento = new EventoJogo(EventoJogo.Tipo.JOGO_INICIADO, "Teste");

        assertEquals(EventoJogo.Tipo.JOGO_INICIADO, evento.getEvento());

        evento = new EventoJogo(EventoJogo.Tipo.JOGADA_RETROCEDIDA, "Teste");
        assertEquals(EventoJogo.Tipo.JOGADA_RETROCEDIDA, evento.getEvento());

        evento = new EventoJogo(EventoJogo.Tipo.JOGO_INICIADO, "Teste");
        assertEquals(EventoJogo.Tipo.JOGO_INICIADO, evento.getEvento());

        evento = new EventoJogo(EventoJogo.Tipo.JOGO_TERMINADO, "Teste");
        assertEquals(EventoJogo.Tipo.JOGO_TERMINADO, evento.getEvento());
    }

    /**
     * Test of getDescricao method, of class EventoJogo.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        EventoJogo evento = new EventoJogo(EventoJogo.Tipo.JOGO_INICIADO, "Teste1");

        assertEquals("Teste1", evento.getDescricao());

    }

}
