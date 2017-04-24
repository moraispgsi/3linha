/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.interfaces;

import java.io.Serializable;

/**
 * O Comparador descreve a maneira como são ordenados os dados de um certo tipo
 * de elementos.
 *
 * @author Ricardo José Horta Morais
 * @param <E> Tipo de elemento a ordenar
 */
public interface Comparador<E> extends Serializable {

    /**
     * Diz se estão ordenados de acordo com este tipo de ordenação
     *
     * @param esquerdo elemento do lado esquerdo
     * @param direito elemento do lado direito
     * @return true se os elementos devem ser trocados e false senão
     *
     */
    public boolean comparar(E esquerdo, E direito);

}
