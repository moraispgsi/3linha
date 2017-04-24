/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads.implementations;

import java.time.LocalDateTime;
import pt.ips.pa.model.tads.interfaces.DNode;
import pt.ips.pa.model.tads.interfaces.DateDNode;
import pt.ips.pa.model.tads.interfaces.Node;

/**
 * Implementação simples de um DataDNode
 * 
 * @author Ricardo José Horta Morais
 * @param <E> tipo dos elementos
 */
public class SimpleDateDNode<E> implements DateDNode<E> {

    private final DNode<E> node;
    private LocalDateTime date;

    /**
     * Contrutor de um date double ended node
     */
    public SimpleDateDNode() {
        node = new SimpleDNode<>();
        date = LocalDateTime.now();
    }

    /**
     * Contrutor de um date double ended node
     *
     * @param elemento elemento a que este node se refere
     * @param anterior node anterior a este
     * @param posterior node posterior a este
     */
    public SimpleDateDNode(E elemento, DateDNode anterior, DateDNode posterior) {
        node = new SimpleDNode<>(elemento, anterior, posterior);
        date = LocalDateTime.now();
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public DateDNode getAnterior() {
        return (DateDNode) node.getAnterior();
    }

    @Override
    public void setAnterior(DNode anterior) {
        node.setAnterior(anterior);
    }

    @Override
    public E getElemento() {
        return node.getElemento();
    }

    @Override
    public DateDNode getPosterior() {
        return (DateDNode) node.getPosterior();
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
