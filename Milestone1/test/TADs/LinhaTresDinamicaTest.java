/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADs;

import TADInterfaces.ConjuntoTriplo;
import TADInterfaces.LinhaTres;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class LinhaTresDinamicaTest {

    public LinhaTresDinamicaTest() {
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
     * Test of addFirst method, of class LinhaTresDinamica.
     */
    @Test
    public void testAddFirst() {

        LinhaTres<String> linhaTres = new TADS.LinhaTresDinamica<>(5);

        try {

            linhaTres.addFirst("A");
            linhaTres.addFirst("B");
            linhaTres.addFirst("C");
            linhaTres.addFirst("D");
            linhaTres.addFirst("E");
            linhaTres.addFirst("F");

            assert (false);

        } catch (Exception ex) {

        }

        Iterator<String> iterador = linhaTres.iterator();

        assert (iterador.hasNext());

        assertEquals("E", iterador.next());
        assertEquals("D", iterador.next());
        assertEquals("C", iterador.next());
        assertEquals("B", iterador.next());
        assertEquals("A", iterador.next());

        assert (!iterador.hasNext());

    }

    /**
     * Test of addLast method, of class LinhaTresDinamica.
     */
    @Test
    public void testAddLast() {

        LinhaTres<String> linhaTres = new TADS.LinhaTresDinamica<>(5);

        try {

            linhaTres.addLast("A");
            linhaTres.addLast("B");
            linhaTres.addLast("C");
            linhaTres.addLast("D");
            linhaTres.addLast("E");
            linhaTres.addLast("F");

            assert (false);

        } catch (Exception ex) {

        }

        Iterator<String> iterador = linhaTres.iterator();

        assert (iterador.hasNext());

        assertEquals("A", iterador.next());
        assertEquals("B", iterador.next());
        assertEquals("C", iterador.next());
        assertEquals("D", iterador.next());
        assertEquals("E", iterador.next());

        assert (!iterador.hasNext());

    }

    /**
     * Test of removeFirst method, of class LinhaTresDinamica.
     */
    @Test
    public void testRemoveFirst() {
        LinhaTres<String> linhaTres = new TADS.LinhaTresDinamica<>(5);

        linhaTres.addFirst("A");
        linhaTres.addFirst("B");
        linhaTres.addFirst("C");
        linhaTres.addFirst("D");
        linhaTres.addFirst("E");

        linhaTres.removeFirst();

        Iterator<String> iterador = linhaTres.iterator();

        assert (iterador.hasNext());

        assertEquals("B",iterador.next());
        assertEquals("A",iterador.next());

        assert (!iterador.hasNext());
    }

    /**
     * Test of removeLast method, of class LinhaTresDinamica.
     */
    @Test
    public void testRemoveLast() {
        LinhaTres<String> linhaTres = new TADS.LinhaTresDinamica<>(5);

        linhaTres.addFirst("A");
        linhaTres.addFirst("B");
        linhaTres.addFirst("C");
        linhaTres.addFirst("D");
        linhaTres.addFirst("E");

        linhaTres.removeLast();

        Iterator<String> iterador = linhaTres.iterator();

        assert (iterador.hasNext());

        assertEquals("E",iterador.next());
        assertEquals("D",iterador.next());

        assert (!iterador.hasNext());
    }

    /**
     * Test of getFirst method, of class LinhaTresDinamica.
     */
    @Test
    public void testGetFirst() {
        LinhaTres<String> linhaTres = new TADS.LinhaTresDinamica<>(5);

        linhaTres.addFirst("A");
        linhaTres.addFirst("B");
        linhaTres.addFirst("C");
        linhaTres.addFirst("D");
        linhaTres.addFirst("E");

        ConjuntoTriplo triplo = linhaTres.getFirst();

        assertEquals("E",triplo.getEsquerdo() );
        assertEquals("D",triplo.getCentro() );
        assertEquals("C",triplo.getDireito() );

        Iterator<String> iterador = linhaTres.iterator();

        assert (iterador.hasNext());

        assertEquals("E",iterador.next() );
        assertEquals("D",iterador.next() );
        assertEquals("C",iterador.next() );
        assertEquals("B",iterador.next() );
        assertEquals("A",iterador.next() );

        assert (!iterador.hasNext());
    }

    /**
     * Test of getLast method, of class LinhaTresDinamica.
     */
    @Test
    public void testGetLast() {

        LinhaTres<String> linhaTres = new TADS.LinhaTresDinamica<>(5);

        linhaTres.addFirst("A");
        linhaTres.addFirst("B");
        linhaTres.addFirst("C");
        linhaTres.addFirst("D");
        linhaTres.addFirst("E");

        ConjuntoTriplo triplo = linhaTres.getLast();

        assertEquals("C",triplo.getEsquerdo() );
        assertEquals("B",triplo.getCentro() );
        assertEquals("A",triplo.getDireito() );

        Iterator<String> iterador = linhaTres.iterator();

        assert (iterador.hasNext());

        assertEquals("E",iterador.next() );
        assertEquals("D",iterador.next() );
        assertEquals("C",iterador.next() );
        assertEquals("B",iterador.next() );
        assertEquals("A",iterador.next() );

        assert (!iterador.hasNext());

    }

}
