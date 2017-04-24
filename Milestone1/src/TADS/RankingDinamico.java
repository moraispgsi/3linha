package TADS;

import TADInterfaces.Ranking;
import TADInterfaces.Comparador;

/**
 *
 * Implementação dinâmica do Ranking
 *
 * @see Ranking<E>
 *
 * @author Ricardo José Horta Morais
 *
 * @param <E> tipo de elementos
 */
public class RankingDinamico<E extends Comparable> implements Ranking<E> {

    private final Comparador<E> comparador;

    private final Node<E> header;
    private int size;

    public RankingDinamico(Comparador<E> comparador) {

        this.comparador = comparador;

        header = new Node<>(null, null);

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

    @Override
    public E get(int indice) {

        if (size <= indice) {
            throw new RuntimeException("Size maior que o indice.");//TODO
        }
        return encontrar(indice).getElemento();

    }

    @Override
    public E set(int indice, E elemento) {

        if (size <= indice) {
            throw new RuntimeException("Size maior que o indice.");//TODO
        }
        Node<E> nodeEncontrado = encontrar(indice);
        E valorAntigo = nodeEncontrado.getElemento();
        nodeEncontrado.setElemento(elemento);

        return valorAntigo;

    }

    /**
     * Ordena o ranking através do algoritmo bubble sort
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
    public void adicionar(E elemento) {

        Node<E> posterior = header.getPosterior();

        Node<E> novoNode = new Node<>(elemento, posterior);

        header.setPosterior(novoNode);

        ordenar();

        size++;

    }

    @Override
    public E remover(int indice) {
        
        if (size <= indice) {
            throw new RuntimeException("Size maior que o indice.");//TODO
        }
        
        Node<E> anterior = encontrar(indice-1);

        Node<E> removido = anterior.getPosterior();

        anterior.setPosterior(removido.getPosterior());

        size--;
        
        return removido.getElemento();
        
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
