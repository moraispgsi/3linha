/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.treslinha.implementations;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException;
import pt.ips.pa.fase1.tads.implementations.LinhaTresDinamica;
import pt.ips.pa.fase1.tads.interfaces.LinhaTres;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;

/**
 * Testa a classe PecaCaveira
 *
 * @author Ricardo José Horta Morais
 */
public class PecaCaveiraTest {

    public PecaCaveiraTest() {
    }

    /**
     * Test of executarDireita method, of class PecaCaveira.
     */
    @Test
    public void testExecutarDireita() {
        System.out.println("executarDireita");
        try {

            PecaCaveira pecaCaveira = new PecaCaveira("Peca Caveira");
            LinhaTres<PecaTresLinha> linha = new LinhaTresDinamica(8);

            linha.addLast(pecaCaveira);

            pecaCaveira.executarDireita(linha);

            assert (linha.isEmpty());
        } catch (LinhaCheiaException ex) {
            fail();
        }

    }

    /**
     * Test of executarEsquerda method, of class PecaCaveira.
     */
    @Test
    public void testExecutarEsquerda() {

        System.out.println("executarEsquerda");
        try {

            PecaCaveira pecaCaveira = new PecaCaveira("Peca Caveira");
            LinhaTres<PecaTresLinha> linha = new LinhaTresDinamica(8);

            linha.addFirst(pecaCaveira);

            pecaCaveira.executarEsquerda(linha);

            assert (linha.isEmpty());
        } catch (LinhaCheiaException ex) {
            fail();
        }
    }

    /**
     * Test of getNome method, of class PecaCaveira.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        PecaCaveira peca = new PecaCaveira("Peca caveira"); 
        
        assertEquals("Peca caveira",peca.getNome() );
    }

}
