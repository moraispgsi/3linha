package pt.ips.pa.fase1.tads.exceptions;

/**
 * Ocorre ao inserir um elemento já existente
 *
 * @author Ricardo José Horta Morais
 */
public class ElementoExistenteException extends RuntimeException {

    /**
     * Construtor
     */
    public ElementoExistenteException() {
        super("Elemento já existe.");
    }

}
