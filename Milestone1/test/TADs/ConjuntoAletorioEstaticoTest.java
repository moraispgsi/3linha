/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADs;

import TADInterfaces.ConjuntoAleatorio;
import TADS.ConjuntoAletorioEstatico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class ConjuntoAletorioEstaticoTest {
    
    public ConjuntoAletorioEstaticoTest() {
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
     * Test of peek method, of class ConjuntoAletorioEstatico.
     */
    @Test
    public void testPeek() {
        
        ConjuntoAleatorio<String> conjunto = new ConjuntoAletorioEstatico<>("A","B","C");
        
        for(int i=0;i<30;i++){
            
            String rand = conjunto.peek();
            assert(rand.equals("A") || rand.equals("B") || rand.equals("C"));
            
        }
        
    }
    
}
