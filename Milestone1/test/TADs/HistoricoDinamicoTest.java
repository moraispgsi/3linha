/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADs;

import TADInterfaces.Historico;
import TADS.HistoricoDinamico;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class HistoricoDinamicoTest {

    public HistoricoDinamicoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

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

}
