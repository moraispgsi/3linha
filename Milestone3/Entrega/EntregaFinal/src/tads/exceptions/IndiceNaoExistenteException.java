package tads.exceptions;

/**
 * Ocorre quando não existe um elemento associado a um índice
 *
 * @author Ricardo José Horta Morais
 */
public class IndiceNaoExistenteException extends RuntimeException {

    private int indice;

    /**
     * Construtor
     *
     * @param indice indice que não existe
     */
    public IndiceNaoExistenteException(int indice) {
        super("Não existe elemento associado ao índice " + indice);

        this.indice = indice;
    }

    /**
     * Devolve o indice que causou o conflito
     *
     * @return indice que causou o conflito
     */
    public int getIndice() {
        return indice;
    }

}
