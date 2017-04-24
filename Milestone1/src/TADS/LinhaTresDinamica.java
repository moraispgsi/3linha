/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADS;

import TADInterfaces.ConjuntoTriplo;
import TADInterfaces.LinhaTres;
import java.util.Iterator;

/**
 * Implementação dinâmica da LinhaTres
 *
 * @see LinhaTres<E>
 * @author Ricardo José Horta Morais
 * @param <E> Tipo de elementos
 */
public class LinhaTresDinamica<E> implements LinhaTres<E> {

    private DNode<E> head;
    private DNode<E> tail;
    private int capacidade;
    private int size;

    public LinhaTresDinamica(int capacidade) {

        if(capacidade < 1)
            throw new RuntimeException("A capacidade tem de ser maior que 0.");
            
        this.head = null;
        this.tail = null;
        this.capacidade = capacidade;
        this.size = 0;

    }

    @Override
    public void addFirst(E elemento) {

        if (size >= capacidade) {

            throw new RuntimeException("Linha cheia.");

        }

        DNode<E> novoNode = new DNode<>(elemento, null, null);

        if (size == 0) {

            head = novoNode;
            tail = novoNode;

        } else {

            novoNode.setPosterior(head);
            head.setAnterior(novoNode);
            head = novoNode;

        }

        size++;
    }

    @Override
    public void addLast(E elemento) {

        if (size >= capacidade) {

            throw new RuntimeException("Linha cheia.");

        }

        DNode<E> novoNode = new DNode<>(elemento, null, null);

        if (size == 0) {

            head = novoNode;
            tail = novoNode;

        } else {

            novoNode.setAnterior(tail);
            tail.setPosterior(novoNode);
            tail = novoNode;

        }

        size++;

    }

    @Override
    public void removeFirst() {

        if (size < 3) {
            throw new RuntimeException("Bot");//TODO
        }
        DNode<E> atual = head;
        for (int i = 0; i < 3; i++) {
            atual = atual.getPosterior();
        }
        head = atual;
        head.setAnterior(null);

        size -= 3;

    }

    @Override
    public void removeLast() {

        if (size < 3) {
            throw new RuntimeException("Bot");//TODO
        }
        DNode<E> atual = tail;
        for (int i = 0; i < 3; i++) {
            atual = atual.getAnterior();
        }
        tail = atual;
        tail.setPosterior(null);

        size -= 3;

    }

    @Override
    public ConjuntoTriplo getFirst() {

        if (size < 3) {
            throw new RuntimeException("Bot");//TODO
        }
        ConjuntoTriplo conjunto = new ConjuntoTriplo(head.getElemento(),
                head.getPosterior().getElemento(),
                head.getPosterior().getPosterior().getElemento());

        return conjunto;

    }

    @Override
    public ConjuntoTriplo getLast() {

        if (size < 3) {
            throw new RuntimeException("Bot");//TODO
        }
        ConjuntoTriplo conjunto = new ConjuntoTriplo(tail.getAnterior().getAnterior().getElemento(),
                tail.getAnterior().getElemento(),
                tail.getElemento());

        return conjunto;

    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> iterador = new Iterator<E>() {

            private DNode<E> atual = head;
            
            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                
                E elemento = atual.getElemento();
                atual = atual.getPosterior();
                
                return elemento;
                
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };

        return iterador;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public int size() {
        return size;
    }

}
