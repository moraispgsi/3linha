/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADInterfaces;


/**
 * Uma LinhaTres é uma coleção de itens com um comportamento semelhante ao TAD
 * Deque permitindo inserir e retirar elementos no início e no fim da fila. No
 * entanto as operações de remover elementos, removem sempre 3 elementos de uma
 * vez e a operações de getFirst e getLast, devolvem os 3 primeiros elementos e
 * os 3 últimos respetivamente. O TAD LinhaTres tem a noção de capacidade
 * máxima, gerando um erro quando essa é excedida. Disponibiliza ainda um
 * iterador para percorrer a coleção.
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elementos contidos no conjunto
 */
public interface LinhaTres<E> extends Iterable<E> {

    /**
     * Insere um elemento no início da fila
     *
     * @param elemento elemento a inserir
     */
    public void addFirst(E elemento);

    /**
     * Insere um elemento no fim da fila
     *
     * @param elemento elemento a inserir
     */
    public void addLast(E elemento);

    /**
     * Remove os 3 primeiro elementos da fila
     */
    public void removeFirst();

    /**
     * Remove os 3 últimos elementos da fila
     */
    public void removeLast();

    /**
     * Devolve os 3 primeiros elementos do conjunto
     *
     * @return o conjunto triplo dos 3 primeiros elementos do conjunto
     */
    public ConjuntoTriplo getFirst();

    /**
     * Devolve os 3 ultimos elementos do conjunto
     *
     * @return o conjunto triplo dos 3 ultimos elementos do conjunto
     */
    public ConjuntoTriplo getLast();
    
    /**
     * Devolve true se a linha estiver vazia false senão
     * @return true se a linha estiver vazia false senão
     */
    public boolean isEmpty();
    
    /**
     * Devolve o número de elementos presentes na linha
     * @return número de elementos presentes na linha
     */
    public int size();

}
