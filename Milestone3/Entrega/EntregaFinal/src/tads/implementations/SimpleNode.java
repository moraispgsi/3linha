/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.implementations;

import tads.interfaces.Node;

/**
 * Implementação simples de um Node
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo dos elementos
 */
public class SimpleNode<E> implements Node<E> {

    private E elemento;
    private Node posterior;

    /**
     * Contrutor de um node
     *
     * @param elemento elemento a que este node se refere
     * @param posterior node posterior a este
     */
    public SimpleNode(E elemento, Node posterior) {
        this.elemento = elemento;
        this.posterior = posterior;
    }

    /**
     * Getter do elemento a que este node se refere
     *
     * @return elemento a que este node se refere
     */
    @Override
    public E getElemento() {
        return elemento;
    }

    @Override
    public Node getPosterior() {
        return posterior;
    }

    @Override
    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    @Override
    public void setPosterior(Node posterior) {
        this.posterior = posterior;
    }

}
