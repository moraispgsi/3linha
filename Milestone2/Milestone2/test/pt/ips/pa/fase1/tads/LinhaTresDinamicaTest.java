package pt.ips.pa.fase1.tads;

import static java.lang.Math.E;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import pt.ips.pa.fase1.tads.exceptions.CapacidadeNegativaException;
import pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException;
import pt.ips.pa.fase1.tads.exceptions.LinhaSemTresException;
import pt.ips.pa.fase1.tads.exceptions.LinhaTresCapacidadeImparException;
import pt.ips.pa.fase1.tads.implementations.LinhaTresDinamica;
import pt.ips.pa.fase1.tads.interfaces.ConjuntoTriplo;
import pt.ips.pa.fase1.tads.interfaces.LinhaTres;

/**
 * Testa a classe LinhaTresDinamica
 *
 * @see LinhaTresDinamica<E>
 * @author Ricardo José Horta Morais
 */
public class LinhaTresDinamicaTest {

    /**
     * Test of addFirst method, of class LinhaTresDinamica.
     */
    @Test
    public void testAddFirst() {
        try {
            LinhaTres<String> linhaTres = new LinhaTresDinamica<>(8);

            try {

                linhaTres.addFirst("A");
                linhaTres.addFirst("B");
                linhaTres.addFirst("C");
                linhaTres.addFirst("D");
                linhaTres.addFirst("E");

                fail();
            } catch (RuntimeException | LinhaCheiaException ex) {

            }

            Iterator<String> iterador = linhaTres.iterator();

            assert (iterador.hasNext());

            assertEquals("D", iterador.next());
            assertEquals("C", iterador.next());
            assertEquals("B", iterador.next());
            assertEquals("A", iterador.next());

            assert (!iterador.hasNext());

        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            fail();
        }
    }

    /**
     * Test of addLast method, of class LinhaTresDinamica.
     */
    @Test
    public void testAddLast() {

        try {

            LinhaTres<String> linhaTres = new LinhaTresDinamica<>(8);

            try {

                linhaTres.addLast("A");
                linhaTres.addLast("B");
                linhaTres.addLast("C");
                linhaTres.addLast("D");
                linhaTres.addLast("E");

                fail();

            } catch (RuntimeException | pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException ex) {

            }

            Iterator<String> iterador = linhaTres.iterator();

            assert (iterador.hasNext());

            assertEquals("A", iterador.next());
            assertEquals("B", iterador.next());
            assertEquals("C", iterador.next());
            assertEquals("D", iterador.next());

            assert (!iterador.hasNext());

        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            fail();

        }

    }

    /**
     * Test of removeFirst method, of class LinhaTresDinamica.
     */
    @Test
    public void testRemoveFirst() {
        try {
            LinhaTres<String> linhaTres = new LinhaTresDinamica<>(8);

            try {

                linhaTres.addFirst("A");
                linhaTres.addFirst("B");
                linhaTres.addFirst("C");
                linhaTres.addFirst("D");

                linhaTres.removeFirst();
            } catch (pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException | LinhaSemTresException ex) {
                fail();
            }

            Iterator<String> iterador = linhaTres.iterator();

            assert (iterador.hasNext());

            assertEquals("A", iterador.next());

            assert (!iterador.hasNext());
        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            fail();

        }
    }

    /**
     * Test of removeLast method, of class LinhaTresDinamica.
     */
    @Test
    public void testRemoveLast() {
        try {
            LinhaTres<String> linhaTres = new LinhaTresDinamica<>(8);

            try {
                linhaTres.addFirst("A");
                linhaTres.addFirst("B");
                linhaTres.addFirst("C");
                linhaTres.addFirst("D");

                linhaTres.removeLast();

            } catch (pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException | LinhaSemTresException ex) {
                fail();
            }

            Iterator<String> iterador = linhaTres.iterator();

            assert (iterador.hasNext());

            assertEquals("D", iterador.next());

            assert (!iterador.hasNext());
        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            fail();

        }
    }

    /**
     * Test of getFirst method, of class LinhaTresDinamica.
     */
    @Test
    public void testGetFirst() {
        try {
            LinhaTres<String> linhaTres = new LinhaTresDinamica<>(8);

            try {

                linhaTres.addFirst("A");
                linhaTres.addFirst("B");
                linhaTres.addFirst("C");
                linhaTres.addFirst("D");

                ConjuntoTriplo triplo = linhaTres.getFirst();

                assertEquals("D", triplo.getEsquerdo());
                assertEquals("C", triplo.getCentro());
                assertEquals("B", triplo.getDireito());

                Iterator<String> iterador = linhaTres.iterator();

                assert (iterador.hasNext());

                assertEquals("D", iterador.next());
                assertEquals("C", iterador.next());
                assertEquals("B", iterador.next());
                assertEquals("A", iterador.next());

                assert (!iterador.hasNext());

            } catch (pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException | LinhaSemTresException ex) {
                fail();
            }
        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            fail();

        }
    }

    /**
     * Test of getLast method, of class LinhaTresDinamica.
     */
    @Test
    public void testGetLast() {

        try {

            LinhaTres<String> linhaTres = new LinhaTresDinamica<>(8);

            try {
                linhaTres.addFirst("A");
                linhaTres.addFirst("B");
                linhaTres.addFirst("C");
                linhaTres.addFirst("D");

                ConjuntoTriplo triplo = linhaTres.getLast();

                assertEquals("C", triplo.getEsquerdo());
                assertEquals("B", triplo.getCentro());
                assertEquals("A", triplo.getDireito());

                Iterator<String> iterador = linhaTres.iterator();

                assert (iterador.hasNext());

                assertEquals("D", iterador.next());
                assertEquals("C", iterador.next());
                assertEquals("B", iterador.next());
                assertEquals("A", iterador.next());

                assert (!iterador.hasNext());
            } catch (pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException | LinhaSemTresException ex) {
                fail();
            }

        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            fail();

        }

    }

}
