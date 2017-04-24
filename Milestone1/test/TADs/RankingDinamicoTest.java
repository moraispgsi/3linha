/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADs;

import TADS.RankingDinamico;
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
public class RankingDinamicoTest {
    
    public RankingDinamicoTest() {
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
     * Test of get method, of class RankingDinamico.
     */
    @Test
    public void testGet() {

        RankingDinamico<String> rank = new RankingDinamico<>((a,b)-> {
            
            return a.compareTo(b) > 0;
        
        });

        rank.adicionar("D");
        rank.adicionar("A");
        rank.adicionar("B");
        rank.adicionar("C");

        assertEquals("A",rank.get(0));
        assertEquals("B",rank.get(1));
        assertEquals("C",rank.get(2));
        assertEquals("D",rank.get(3));
    }

    /**
     * Test of set method, of class RankingDinamico.
     */
    @Test
    public void testSet() {

        RankingDinamico<String> rank = new RankingDinamico<>((a,b)-> {
            
            return a.compareTo(b) > 0;
        
        });

        rank.adicionar("D");
        rank.adicionar("A");
        rank.adicionar("B");
        rank.adicionar("C");
        
        rank.set(2, "E");
        
        assertEquals("A",rank.get(0));
        assertEquals("B",rank.get(1));
        assertEquals("E",rank.get(2));
        assertEquals("D",rank.get(3));
    }

    
    /**
     * Test of adicionar method, of class RankingDinamico.
     */
    @Test
    public void testAdicionar() {
        
        RankingDinamico<String> rank = new RankingDinamico<>((a,b)-> {
            
            return a.compareTo(b) > 0;
        
        });

        rank.adicionar("D");
        rank.adicionar("A");
        rank.adicionar("B");
        rank.adicionar("C");
        
        assertEquals("A",rank.get(0));
        assertEquals("B",rank.get(1));
        assertEquals("C",rank.get(2));
        assertEquals("D",rank.get(3));

    }

    /**
     * Test of remover method, of class RankingDinamico.
     */
    @Test
    public void testRemover() {

        RankingDinamico<String> rank = new RankingDinamico<>((a,b)-> {
            
            return a.compareTo(b) > 0;
        
        });

        rank.adicionar("D");
        rank.adicionar("A");
        rank.adicionar("B");
        rank.adicionar("C");
        
        rank.remover(0);
        
        assertEquals("B",rank.get(0));
        assertEquals("C",rank.get(1));
        assertEquals("D",rank.get(2));
    }

    /**
     * Test of isEmpty method, of class RankingDinamico.
     */
    @Test
    public void testIsEmpty() {

        RankingDinamico<String> rank = new RankingDinamico<>((a,b)-> {
            
            return a.compareTo(b) > 0;
        
        });
        
        assert(rank.isEmpty());

        rank.adicionar("D");
        rank.adicionar("A");
        rank.adicionar("B");
        rank.adicionar("C");
        
        assert(!rank.isEmpty());
    }

    /**
     * Test of size method, of class RankingDinamico.
     */
    @Test
    public void testSize() {

        RankingDinamico<String> rank = new RankingDinamico<>((a,b)-> {
            
            return a.compareTo(b) > 0;
        
        });

        rank.adicionar("D");
        rank.adicionar("A");
        rank.adicionar("B");
        rank.adicionar("C");
        
        assert(rank.size() == 4);
        
    }
    
}
