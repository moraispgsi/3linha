package tads.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import tads.exceptions.ElementoExistenteException;
import tads.exceptions.IndiceNaoExistenteException;
import tads.interfaces.Node;
import tads.interfaces.Ranking;
import tads.interfaces.Comparador;

/**
 *
 * Implementação dinâmica do Ranking
 *
 * @see Ranking
 *
 * @author Ricardo José Horta Morais
 *
 * @param <E> tipo de elementos
 */
public class RankingDinamico<E> implements Ranking<E> {

    private final Comparador<E> comparador;

    private final Node<E> header;
    private int size;

    /**
     * Constroi um Ranking organizado pela ordem de um comparador
     *
     * @param comparador comparador ao qual o ranking ficará organizado
     */
    public RankingDinamico(Comparador<E> comparador) {

        this.comparador = comparador;

        header = new SimpleNode<>(null, null);

        size = 0;

    }

    /**
     * Encontra o Node que está no indice
     *
     * @param indice indice do Node
     * @return node pertencente ao indice
     */
    private Node<E> encontrar(int indice) {

        Node atual = header;

        for (int i = -1; i < indice; i++) {

            atual = atual.getPosterior();

        }
        return atual;
    }

    /**
     * Ordena o ranking através do algoritmo bubble sort Este algoritmo tem boa
     * performance em listas ordenadas e é bastante simples. Outro algoritmo
     * seria overkill.
     */
    private void ordenar() {

        if (size < 2) {
            return;
        }

        int contador;
        boolean trocou;

        do {
            trocou = false;
            contador = 0;

            Node<E> esquerdo = header.getPosterior();
            Node<E> direito = esquerdo.getPosterior();

            do {

                if (comparador.comparar(esquerdo.getElemento(), direito.getElemento())) {

                    E auxiliar = direito.getElemento();
                    direito.setElemento(esquerdo.getElemento());
                    esquerdo.setElemento(auxiliar);

                    trocou = true;
                }

                esquerdo = direito;
                direito = direito.getPosterior();

                contador++;

            } while (contador < size - 1);

        } while (trocou);
    }

    @Override
    public E get(int indice) throws IndiceNaoExistenteException {

        if (size <= indice) {
            throw new IndiceNaoExistenteException(indice);
        }

        ordenar();

        return encontrar(indice).getElemento();

    }

    @Override
    public E set(int indice, E elemento) throws IndiceNaoExistenteException {

        if (size <= indice) {
            throw new IndiceNaoExistenteException(indice);
        }

        ordenar();

        Node<E> nodeEncontrado = encontrar(indice);
        E valorAntigo = nodeEncontrado.getElemento();
        nodeEncontrado.setElemento(elemento);

        ordenar();

        return valorAntigo;

    }

    @Override
    public void adicionar(E elemento) throws ElementoExistenteException {

        if (hasElement(elemento)) {

            throw new ElementoExistenteException();

        }

        Node<E> posterior = header.getPosterior();

        Node<E> novoNode = new SimpleNode<>(elemento, posterior);

        header.setPosterior(novoNode);

        ordenar();

        size++;

    }

    @Override
    public E remover(int indice) throws IndiceNaoExistenteException {

        if (size <= indice) {
            throw new IndiceNaoExistenteException(indice);
        }

        Node<E> anterior = encontrar(indice - 1);

        Node<E> removido = anterior.getPosterior();

        anterior.setPosterior(removido.getPosterior());

        size--;

        return removido.getElemento();

    }

    @Override
    public void remover(E elemento) {

        int indice = indexOf(elemento);
        if (indice >= 0) {
            remover(indice);
        }

    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean hasElement(E elemento) {

        Node atual = header.getPosterior();

        while (atual != null) {

            if (atual.getElemento().equals(elemento)) {
                return true;
            }

            atual = atual.getPosterior();

        }

        return false;

    }

    @Override
    public Iterator<E> iterator() {

        ordenar();

        return new Iterator<E>() {

            private Node<E> cursor = header.getPosterior();

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {

                if (cursor == null) {
                    throw new NoSuchElementException();
                }

                E elem = cursor.getElemento();
                cursor = cursor.getPosterior();

                return elem;

            }

        };

    }

    @Override
    public int indexOf(E elemento) {

        ordenar();
        Node atual = header.getPosterior();

        for (int i = 0; atual != null; i++) {

            if (atual.getElemento().equals(elemento)) {
                return i;
            }

            atual = atual.getPosterior();

        }

        return -1;

    }

    @Override
    public Stream<E> stream() {

        List<E> lista = new ArrayList();
        iterator().forEachRemaining(e
                -> lista.add(e));
        return lista.stream();

    }

}
