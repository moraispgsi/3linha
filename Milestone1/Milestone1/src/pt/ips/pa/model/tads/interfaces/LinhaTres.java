/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads.interfaces;

import pt.ips.pa.model.tads.exceptions.LinhaSemTresException;
import pt.ips.pa.model.tads.exceptions.LinhaCheiaException;
import java.util.Iterator;

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
     * @throws pt.ips.pa.model.tads.exceptions.LinhaCheiaException Linha já
     * se encontra preenchida
     */
    public void addFirst(E elemento) throws LinhaCheiaException;

    /**
     * Insere um elemento no fim da fila
     *
     * @param elemento elemento a inserir
     * @throws pt.ips.pa.model.tads.exceptions.LinhaCheiaException Linha já
     * se encontra preenchida
     */
    public void addLast(E elemento) throws LinhaCheiaException;

    /**
     * Remove os 3 primeiro elementos da fila
     *
     * @throws pt.ips.pa.model.tads.exceptions.LinhaSemTresException Linha
     * alberga menos de 3 elementos
     */
    public void removeFirst() throws LinhaSemTresException;

    /**
     * Remove os 3 últimos elementos da fila
     *
     * @throws pt.ips.pa.model.tads.exceptions.LinhaSemTresException Linha
     * alberga menos de 3 elementos
     */
    public void removeLast() throws LinhaSemTresException;

    /**
     * Devolve os 3 primeiros elementos do conjunto
     *
     * @return o conjunto triplo dos 3 primeiros elementos do conjunto
     * @throws pt.ips.pa.model.tads.exceptions.LinhaSemTresException Linha
     * alberga menos de 3 elementos
     */
    public ConjuntoTriplo getFirst() throws LinhaSemTresException;

    /**
     * Devolve os 3 ultimos elementos do conjunto
     *
     * @return o conjunto triplo dos 3 ultimos elementos do conjunto
     * @throws pt.ips.pa.model.tads.exceptions.LinhaSemTresException Linha
     * alberga menos de 3 elementos
     */
    public ConjuntoTriplo getLast() throws LinhaSemTresException;

    /**
     * Devolve true se a linha estiver vazia false senão
     *
     * @return true se a linha estiver vazia false senão
     */
    public boolean isEmpty();

    /**
     * Devolve o número de elementos presentes na parte esquerda da linha
     *
     * @return número de elementos presentes na parte esquerda da linha
     */
    public int sizeEsquerdo();

    /**
     * Devolve o número de elementos presentes na parte direita da linha
     *
     * @return número de elementos presentes na parte esquerda da linha
     */
    public int sizeDireito();

    /**
     * Iterador que percorre o lado esquerdo a partir do centro até ao final do
     * lado esquerdo
     *
     * @return iterador
     */
    public Iterator<E> getIteradorEsquerdo();

    /**
     * Iterador que percorre o lado direito a partir do centro até ao final do
     * lado direito
     *
     * @return iterador
     */
    public Iterator<E> getIteradorDireito();

}
