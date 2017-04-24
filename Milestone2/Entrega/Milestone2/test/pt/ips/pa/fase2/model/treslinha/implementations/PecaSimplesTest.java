package pt.ips.pa.fase2.model.treslinha.implementations;

import org.junit.Test;
import static org.junit.Assert.*;
import pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException;
import pt.ips.pa.fase1.tads.implementations.LinhaTresDinamica;
import pt.ips.pa.fase1.tads.interfaces.LinhaTres;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;

/**
 * Testa a classe PecaSimples
 *
 * @author Ricardo José Horta Morais
 */
public class PecaSimplesTest {

    public PecaSimplesTest() {
    }

    /**
     * Test of equals method, of class PecaSimples.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        PecaSimples pecaSimples1 = new PecaSimples("Peca1");
        PecaSimples pecaSimples2 = new PecaSimples("Peca1");
        PecaSimples pecaSimples3 = new PecaSimples("Peca3");

        assertEquals(true, pecaSimples1.equals(pecaSimples2));
        assertEquals(false, pecaSimples1.equals(pecaSimples3));

    }

    /**
     * Test of getNome method, of class PecaSimples.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");

        PecaSimples pecaSimples1 = new PecaSimples("Peca1");
        PecaSimples pecaSimples2 = new PecaSimples("Peca2");
        PecaSimples pecaSimples3 = new PecaSimples("Peca3");

        assertEquals("Peca1", pecaSimples1.getNome());
        assertEquals("Peca2", pecaSimples2.getNome());
        assertEquals("Peca3", pecaSimples3.getNome());

    }

    /**
     * Test of executarEsquerda method, of class PecaSimples.
     */
    @Test
    public void testExecutarEsquerda() {
        System.out.println("executarEsquerda");

        try {

            PecaSimples pecaSimples = new PecaSimples("Peca1");

            LinhaTres<PecaTresLinha> linha = new LinhaTresDinamica(8);

            linha.addFirst(pecaSimples);
            linha.addFirst(pecaSimples);
            linha.addFirst(pecaSimples);

            pecaSimples.executarEsquerda(linha);

            assert (linha.isEmpty());
        } catch (LinhaCheiaException ex) {
            fail();
        }

    }

    /**
     * Test of executarDireita method, of class PecaSimples.
     */
    @Test
    public void testExecutarDireita() {
        System.out.println("executarDireita");
        try {

            PecaSimples pecaSimples = new PecaSimples("Peca1");

            LinhaTres<PecaTresLinha> linha = new LinhaTresDinamica(8);

            linha.addLast(pecaSimples);
            linha.addLast(pecaSimples);
            linha.addLast(pecaSimples);

            pecaSimples.executarDireita(linha);

            assert (linha.isEmpty());
        } catch (LinhaCheiaException ex) {
            fail();
        }
    }

}
