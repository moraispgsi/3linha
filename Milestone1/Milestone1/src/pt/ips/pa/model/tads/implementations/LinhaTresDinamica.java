package pt.ips.pa.model.tads.implementations;

import pt.ips.pa.model.tads.exceptions.LinhaCheiaException;
import pt.ips.pa.model.tads.interfaces.DNode;
import pt.ips.pa.model.tads.interfaces.ConjuntoTriplo;
import pt.ips.pa.model.tads.exceptions.LinhaSemTresException;
import pt.ips.pa.model.tads.interfaces.LinhaTres;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementação dinâmica da LinhaTres
 *
 * @see LinhaTres
 * @author Ricardo José Horta Morais
 * @param <E> Tipo de elementos
 */
public class LinhaTresDinamica<E> implements LinhaTres<E> {

    private DNode<E> centro;
    private DNode<E> head;
    private int capacidadeLado;
    private int sizeEsquerda;
    private int sizeDireita;

    /**
     * Constroi uma LinhaTres com uma certa capacidade
     *
     * @param capacidade capacidade da LinhaTres
     */
    public LinhaTresDinamica(int capacidade) {

        if (capacidade < 1) {
            throw new RuntimeException("A capacidade tem de ser maior que 0.");
        }
        if (capacidade % 2 != 0) {
            throw new RuntimeException("A capacidade deve ser um número par.");
        }

        this.centro = new SimpleDNode<>();
        this.head = new SimpleDNode<>();

        this.head.setPosterior(head);
        this.head.setAnterior(head);

        this.capacidadeLado = capacidade / 2;
        this.sizeEsquerda = 0;
        this.sizeDireita = 0;
    }

    @Override
    public void addFirst(E elemento) throws LinhaCheiaException {

        if (sizeEsquerda >= capacidadeLado) {
            throw new LinhaCheiaException();
        }

        DNode<E> novoNode = new SimpleDNode<>(elemento, head, head.getPosterior());

        if (sizeEsquerda == 0) {
            centro.setAnterior(novoNode);
        }

        head.getPosterior().setAnterior(novoNode);
        head.setPosterior(novoNode);

        sizeEsquerda++;
    }

    @Override
    public void addLast(E elemento) throws LinhaCheiaException {

        if (sizeDireita >= capacidadeLado) {

            throw new LinhaCheiaException();

        }

        DNode<E> novoNode = new SimpleDNode<>(elemento, head.getAnterior(), head);

        if (sizeDireita == 0) {
            centro.setPosterior(novoNode);
        }

        head.getAnterior().setPosterior(novoNode);
        head.setAnterior(novoNode);

        sizeDireita++;

    }

    @Override
    public void removeFirst() throws LinhaSemTresException {

        if (sizeEsquerda + sizeDireita < 3) {
            throw new LinhaSemTresException();
        }
        DNode<E> atual = head.getPosterior();
        for (int i = 0; i < 3; i++) {
            atual = atual.getPosterior();
        }
        head.setPosterior(atual);
        atual.setAnterior(head);

        sizeDireita -= 3 - sizeEsquerda;

        if (sizeEsquerda <= 3) {

            centro.setPosterior(atual);
            centro.setAnterior(null);
            sizeEsquerda = 0;

        } else {
            sizeEsquerda -= 3;
        }

    }

    @Override
    public void removeLast() throws LinhaSemTresException {

        if (sizeEsquerda + sizeDireita < 3) {
            throw new LinhaSemTresException();
        }

        DNode<E> atual = head.getAnterior();
        for (int i = 0; i < 3; i++) {
            atual = atual.getAnterior();
        }

        head.setAnterior(atual);
        atual.setPosterior(head);

        sizeEsquerda -= 3 - sizeDireita;

        if (sizeDireita <= 3) {

            centro.setAnterior(atual);
            centro.setPosterior(null);
            sizeDireita = 0;

        } else {
            sizeDireita -= 3;
        }

    }

    @Override
    public ConjuntoTriplo getFirst() throws LinhaSemTresException {

        if (sizeEsquerda + sizeDireita < 3) {
            throw new LinhaSemTresException();
        }
        E elements[] = (E[]) new Object[3];
        DNode<E> current = head.getPosterior();

        for (int i = 0; i < 3; i++) {

            elements[i] = current.getElemento();
            current = current.getPosterior();

        }

        ConjuntoTriplo<E> conjunto = new SimpleConjuntoTriplo<>(elements[0], elements[1], elements[2]);

        return conjunto;

    }

    @Override
    public ConjuntoTriplo getLast() throws LinhaSemTresException {

        if (sizeEsquerda + sizeDireita < 3) {
            throw new LinhaSemTresException();
        }
        E elements[] = (E[]) new Object[3];
        DNode<E> current = head.getAnterior();

        for (int i = 0; i < 3; i++) {

            elements[i] = current.getElemento();
            current = current.getAnterior();

        }

        ConjuntoTriplo<E> conjunto = new SimpleConjuntoTriplo<>(elements[2], elements[1], elements[0]);

        return conjunto;

    }

    @Override
    public boolean isEmpty() {
        return sizeEsquerda + sizeDireita == 0;
    }

    @Override
    public int sizeEsquerdo() {
        return sizeEsquerda;
    }

    @Override
    public int sizeDireito() {
        return sizeDireita;
    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> iterador = new Iterator<E>() {

            private DNode<E> atual = head.getPosterior();

            @Override
            public boolean hasNext() {
                return atual != head;
            }

            @Override
            public E next() {

                E elemento = atual.getElemento();
                if (elemento == null) {
                    throw new NoSuchElementException();
                }

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
    public Iterator<E> getIteradorEsquerdo() {

        Iterator<E> iterador = new Iterator<E>() {

            private DNode<E> current = centro.getAnterior();

            @Override
            public boolean hasNext() {

                return current != null && current != head;

            }

            @Override
            public E next() {

                E elem = current.getElemento();
                if (elem == null) {
                    throw new NoSuchElementException();
                }

                current = current.getAnterior();

                return elem;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };

        return iterador;
    }

    @Override
    public Iterator<E> getIteradorDireito() {

        Iterator<E> iterador = new Iterator<E>() {

            private DNode<E> current = centro.getPosterior();

            @Override
            public boolean hasNext() {

                return current != null && current != head;

            }

            @Override
            public E next() {

                E elem = current.getElemento();

                if (elem == null) {
                    throw new NoSuchElementException();
                }

                current = current.getPosterior();

                return elem;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };

        return iterador;
    }

}
