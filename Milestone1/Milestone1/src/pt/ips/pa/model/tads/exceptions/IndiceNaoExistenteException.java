
package pt.ips.pa.model.tads.exceptions;

/**
 * Ocorre quando não existe um elemento associado a um índice
 *
 * @author Ricardo José Horta Morais
 */
public class IndiceNaoExistenteException extends Exception {

    private int indice;

    public IndiceNaoExistenteException(int indice) {
        super("Não existe elemento associado ao índice " + indice);

        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
    
    

}
