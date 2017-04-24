package tads;

import java.time.LocalDateTime;
import tads.interfaces.Historico;
import tads.implementations.HistoricoDinamico;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testa a classe HistoricoDinamico
 *
 * @see HistoricoDinamico<E>
 * @author Ricardo José Horta Morais
 */
public class HistoricoDinamicoTest {

    /**
     * Test of add method, of class HistoricoDinamico.
     */
    @Test
    public void testAdd() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        historico.add("A");
        historico.add("B");
        historico.add("C");
        historico.add("D");
        historico.add("E");
        historico.add("F");

        Iterator<String> iterador = historico.iterator();

        assert (iterador.hasNext());

        assertEquals("F", iterador.next());
        assertEquals("E", iterador.next());
        assertEquals("D", iterador.next());

        assert (!iterador.hasNext());
    }

    /**
     * Test of iterator method, of class HistoricoDinamico.
     */
    @Test
    public void testIterator() {
        testAdd();
    }

    /**
     * Test of isEmpty method, of class HistoricoDinamico.
     */
    @Test
    public void testIsEmpty() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        assert (historico.isEmpty());

        historico.add("A");

        assert (!historico.isEmpty());

    }

    /**
     * Test of getCapacity method, of class HistoricoDinamico.
     */
    @Test
    public void testGetCapacity() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        assertEquals(historico.getCapacity(), 3);

        historico.add("A");
        historico.add("B");
        historico.add("C");
        historico.add("D");
        historico.add("E");
        historico.add("F");

        Iterator<String> iterador = historico.iterator();
        int i = 0;
        while (iterador.hasNext()) {

            iterador.next();
            i++;
        }

        assertEquals(3, i);
    }

    /**
     * Test of size method, of class HistoricoDinamico.
     */
    @Test
    public void testSize() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        assertEquals(0, historico.size());

        historico.add("A");
        assertEquals(1, historico.size());

        historico.add("B");
        assertEquals(2, historico.size());

        historico.add("C");
        assertEquals(3, historico.size());

        historico.add("D");
        assertEquals(3, historico.size());

    }

    /**
     * Test of getIterador method, of class HistoricoDinamico.
     */
    @Test
    public void testGetIterador() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        historico.add("A");
        historico.add("B");
        historico.add("C");
        historico.add("D");

        Iterator<String> it = historico.iterator();

        assertEquals("D", it.next());
        assertEquals("C", it.next());
        assertEquals("B", it.next());

        assert (!it.hasNext());

    }

    /**
     * Test of getDiaIterador method, of class HistoricoDinamico.
     */
    @Test
    public void testGetDiaIterador() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        historico.add("A");
        historico.add("B");
        historico.add("C");
        historico.add("D");

        Iterator<String> it = Historico.getDiaIterador(historico, LocalDateTime.now());

        assertEquals("D", it.next());
        assertEquals("C", it.next());
        assertEquals("B", it.next());

        assert (!it.hasNext());

    }

    /**
     * Test of getMesIterador method, of class HistoricoDinamico.
     */
    @Test
    public void testGetMesIterador() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        historico.add("A");
        historico.add("B");
        historico.add("C");
        historico.add("D");

        Iterator<String> it = Historico.getMesIterador(historico);

        assertEquals("D", it.next());
        assertEquals("C", it.next());
        assertEquals("B", it.next());

        assert (!it.hasNext());

    }

    /**
     * Test of getUltimosDiasIterador method, of class HistoricoDinamico.
     */
    @Test
    public void testGetUltimosDiasIterador() {

        Historico<String> historico = new HistoricoDinamico<>(3);

        historico.add("A");
        historico.add("B");
        historico.add("C");
        historico.add("D");

        Iterator<String> it = Historico.getUltimosDiasIterador(historico, 5);

        assertEquals("D", it.next());
        assertEquals("C", it.next());
        assertEquals("B", it.next());

        assert (!it.hasNext());

    }

}
