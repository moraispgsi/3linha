/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADS;

/**
 *  Representa um double ended node ou seja um node que liga ao anterior e ao 
 * posterior e este guarda um unico elemento do tipo generico
 * 
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elemento
 */
public class DNode<E> {
    
    private E elemento;
    private DNode anterior;
    private DNode posterior;

    public DNode(E elemento, DNode anterior, DNode posterior) {
        this.elemento = elemento;
        this.anterior = anterior;
        this.posterior = posterior;
    }

    public E getElemento() {
        return elemento;
    }

    public DNode getAnterior() {
        return anterior;
    }

    public DNode getPosterior() {
        return posterior;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public void setAnterior(DNode anterior) {
        this.anterior = anterior;
    }

    public void setPosterior(DNode posterior) {
        this.posterior = posterior;
    }
    
    

}
