/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads.interfaces;

/**
 * Representa um node ou seja um node que liga ao posterior e este guarda um
 * unico elemento do tipo generico
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elemento
 */
public interface Node<E> {

    /**
     * Getter do elemento a que este node se refere
     *
     * @return elemento a que este node se refere
     */
    public E getElemento();

    /**
     * Getter do node posterior relativamente a este
     *
     * @return node anterior relativamente a este
     */
    public Node<E> getPosterior();

    /**
     * Setter do elemento a que este node se refere
     *
     * @param elemento elemento a que este node se irá referir
     */
    public void setElemento(E elemento);

    /**
     * Setter do node posterior relativamente a este
     *
     * @param posterior node posterior relativamente a este
     */
    public void setPosterior(Node<E> posterior);

}
