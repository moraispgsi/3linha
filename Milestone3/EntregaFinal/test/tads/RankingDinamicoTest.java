package tads;

import tads.implementations.RankingDinamico;
import org.junit.Test;
import static org.junit.Assert.*;
import tads.exceptions.ElementoExistenteException;
import tads.exceptions.IndiceNaoExistenteException;

/**
 * Testa a classe RankingDinamico
 *
 * @see RankingDinamico<E>
 * @author Ricardo José Horta Morais
 */
public class RankingDinamicoTest {

    /**
     * Test of get method, of class RankingDinamico.
     */
    @Test
    public void testGet() {

        try {
            RankingDinamico<String> rank = new RankingDinamico<>((a, b) -> {

                return a.compareTo(b) > 0;

            });

            rank.adicionar("D");
            rank.adicionar("A");
            rank.adicionar("B");
            rank.adicionar("C");

            assertEquals("A", rank.get(0));
            assertEquals("B", rank.get(1));
            assertEquals("C", rank.get(2));
            assertEquals("D", rank.get(3));
        } catch (ElementoExistenteException | IndiceNaoExistenteException ex) {
            fail();
        }
    }

    /**
     * Test of set method, of class RankingDinamico.
     */
    @Test
    public void testSet() {

        try {
            RankingDinamico<String> rank = new RankingDinamico<>((a, b) -> {

                return a.compareTo(b) > 0;

            });

            rank.adicionar("D");
            rank.adicionar("A");
            rank.adicionar("B");
            rank.adicionar("C");

            rank.set(2, "E");

            assertEquals("A", rank.get(0));
            assertEquals("B", rank.get(1));
            assertEquals("D", rank.get(2));
            assertEquals("E", rank.get(3));
        } catch (ElementoExistenteException | IndiceNaoExistenteException ex) {
            fail();
        }
    }

    /**
     * Test of adicionar method, of class RankingDinamico.
     */
    @Test
    public void testAdicionar() {

        try {
            RankingDinamico<String> rank = new RankingDinamico<>((a, b) -> {

                return a.compareTo(b) > 0;

            });

            rank.adicionar("D");
            rank.adicionar("A");
            rank.adicionar("B");
            rank.adicionar("C");

            assertEquals("A", rank.get(0));
            assertEquals("B", rank.get(1));
            assertEquals("C", rank.get(2));
            assertEquals("D", rank.get(3));
        } catch (ElementoExistenteException | IndiceNaoExistenteException ex) {
            fail();
        }

    }

    /**
     * Test of remover method, of class RankingDinamico.
     */
    @Test
    public void testRemover() {

        try {
            RankingDinamico<String> rank = new RankingDinamico<>((a, b) -> {

                return a.compareTo(b) > 0;

            });

            rank.adicionar("D");
            rank.adicionar("A");
            rank.adicionar("B");
            rank.adicionar("C");

            rank.remover(0);

            assertEquals("B", rank.get(0));
            assertEquals("C", rank.get(1));
            assertEquals("D", rank.get(2));
        } catch (ElementoExistenteException | IndiceNaoExistenteException ex) {
            fail();
        }
    }

    /**
     * Test of isEmpty method, of class RankingDinamico.
     */
    @Test
    public void testIsEmpty() {

        try {
            RankingDinamico<String> rank = new RankingDinamico<>((a, b) -> {

                return a.compareTo(b) > 0;

            });

            assert (rank.isEmpty());

            rank.adicionar("D");
            rank.adicionar("A");
            rank.adicionar("B");
            rank.adicionar("C");

            assert (!rank.isEmpty());
        } catch (ElementoExistenteException ex) {
            fail();
        }
    }

    /**
     * Test of size method, of class RankingDinamico.
     */
    @Test
    public void testSize() {

        try {
            RankingDinamico<String> rank = new RankingDinamico<>((a, b) -> {

                return a.compareTo(b) > 0;

            });

            rank.adicionar("D");
            rank.adicionar("A");
            rank.adicionar("B");
            rank.adicionar("C");

            assert (rank.size() == 4);
        } catch (ElementoExistenteException ex) {
            fail();
        }

    }

}
