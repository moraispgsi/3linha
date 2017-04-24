package tads.exceptions;

/**
 * Excessão que ocorre quando é atribuida uma capacidade impar a uma linha três
 *
 * @author Ricardo José Horta Morais
 */
public class LinhaTresCapacidadeImparException extends RuntimeException {

    /**
     * Construtor
     */
    public LinhaTresCapacidadeImparException() {
        super("A capacidade deve ser um número par.");
    }

}
