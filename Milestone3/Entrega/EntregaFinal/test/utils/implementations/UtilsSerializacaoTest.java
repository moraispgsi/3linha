/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.implementations;

import utils.implementations.UtilsSerializacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testa a interface UtilsSerializacao
 *
 * @author Ricardo José Horta Morais
 */
public class UtilsSerializacaoTest {

    /**
     * Test of copiaSerializada method, of class UtilsSerializacao.
     */
    @Test
    public void testCopiaSerializada() {
        System.out.println("copiaSerializada");

        Object string = "Teste";

        Object string2 = UtilsSerializacao.copiaSerializada(string);

        assertEquals(string, string2);

        assert (string != string2);

    }

}
