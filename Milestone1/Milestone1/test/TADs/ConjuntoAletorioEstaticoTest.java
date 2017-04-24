

package TADs;

import pt.ips.pa.model.tads.interfaces.ConjuntoAleatorio;
import pt.ips.pa.model.tads.implementations.ConjuntoAletorioEstatico;
import org.junit.Test;

/**
 * Testa o classe ConjuntoAleatorioEstatico
 *
 * @see ConjuntoAleatorioEstatico<E>
 * @author Ricardo José Horta Morais
 */
public class ConjuntoAletorioEstaticoTest {

    /**
     * Test of peek method, of class ConjuntoAletorioEstatico.
     */
    @Test
    public void testPeek() {

        ConjuntoAleatorio<String> conjunto = new ConjuntoAletorioEstatico<>("A", "B", "C");

        for (int i = 0; i < 30; i++) {

            String rand = conjunto.peek();
            assert (rand.equals("A") || rand.equals("B") || rand.equals("C"));

        }

    }

}
