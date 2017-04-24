
package TADInterfaces;

/**
 * Um ranking é uma lista ordenada de elementos, em que os elementos são
 * ordenados segundo um critério específico.
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elemento
 */
public interface Ranking<E extends Comparable> {

    /**
     * Devolve o elemento que está no indice
     *
     * @param indice indice onde está o elemento
     * @return elemento do indice
     */
    public E get(int indice);

    /**
     * Altera um elemento existente presente no indice 
     * @param indice  indice onde está o elemento
     * @param elemento elemento que vai substituir 
     * @return elemento substituido
     */
    public E set(int indice, E elemento);

    /**
     * Adiciona um elemento no indice
     * @param elemento elemento que irá ser adicionado
     */
    public void adicionar(E elemento);

    /**
     * Remove um elemento presente num item
     * @param indice indice do elemento a remover
     * @return elemento removido
     */
    public E remover(int indice);
    
    /**
     * Devolve true se o ranking estiver vazio false senão
     * @return true se o ranking estiver vazio false senão
     */
    public boolean isEmpty();
    
    /**
     * Devolve o número de elementos presentes no ranking
     * @return número de elementos presentes no ranking
     */
    public int size();

}
