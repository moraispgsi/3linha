/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.implementations;

import tads.interfaces.DNode;
import tads.interfaces.Node;

/**
 * Implementação simples de um DNode
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo dos elementos
 */
public class SimpleDNode<E> implements DNode<E> {

    private final Node<E> node;
    private DNode anterior;

    /**
     * Contrutor de um double ended node
     */
    public SimpleDNode() {
        node = new SimpleNode<>(null, null);
        this.anterior = null;
    }

    /**
     * Contrutor de um double ended node
     *
     * @param elemento elemento a que este node se refere
     * @param anterior node anterior a este
     * @param posterior node posterior a este
     */
    public SimpleDNode(E elemento, DNode<E> anterior, DNode<E> posterior) {
        node = new SimpleNode<>(elemento, posterior);
        this.anterior = anterior;
    }

    @Override
    public DNode getAnterior() {
        return anterior;
    }

    @Override
    public void setAnterior(DNode anterior) {
        this.anterior = anterior;
    }

    @Override
    public E getElemento() {
        return node.getElemento();
    }

    @Override
    public DNode<E> getPosterior() {
        return (DNode<E>) node.getPosterior();
    }

    @Override
    public void setElemento(E elemento) {
        node.setElemento(elemento);
    }

    @Override
    public void setPosterior(Node posterior) {
        node.setPosterior(posterior);
    }

}
