package pt.ips.pa.fase1.tads.interfaces;

import java.io.Serializable;

/**
 * Um conjunto aleatório é uma coleção de itens não repetidos, em que não é
 * possível adicionar ou retirar elementos, e que disponibiliza uma única
 * operação: a operação de visualização aleatória de um item. Ou seja, se
 * tivermos um conjunto aleatório com as cores amarelo, verde e azul, a operação
 * de peek (visualização de um item), devolve aleatoriamente uma das 3 cores.
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elementos contidos no conjunto
 */
public interface ConjuntoAleatorio<E> extends Serializable {

    /**
     * Disponibiliza um dos items
     *
     * @return item aleatório contido no conjunto
     */
    public E peek();

}
