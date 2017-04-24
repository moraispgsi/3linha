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
public class Node<E> {
    
    private E elemento;
    private Node posterior;

    public Node(E elemento, Node posterior) {
        this.elemento = elemento;
        this.posterior = posterior;
    }

    public E getElemento() {
        return elemento;
    }

    public Node getPosterior() {
        return posterior;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public void setPosterior(Node posterior) {
        this.posterior = posterior;
    }
    
    

}
