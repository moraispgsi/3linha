package tads.implementations;

import tads.interfaces.DateDNode;
import tads.interfaces.Historico;
import java.util.Iterator;

/**
 * Implementação dinâmica do Historico
 *
 * @see Historico
 *
 * @author Ricardo José Horta Morais
 *
 * @param <E> tipo de elementos
 */
public class HistoricoDinamico<E> implements Historico<E> {

    private DateDNode<E> head;
    private int size;
    private int capacity;

    /**
     * Constroi um Histórico com uma certa capacidade
     *
     * @param capacity capacidade do histórico
     */
    public HistoricoDinamico(int capacity) {

        if (capacity < 1) {
            throw new RuntimeException("Capacidade tem de ser maior que 0.");
        }

        this.head = new SimpleDateDNode<>();
        this.head.setAnterior(head);
        this.head.setPosterior(head);
        this.capacity = capacity;
        this.size = 0;

    }

    @Override
    public void add(E elemento) {

        DateDNode<E> novoNode = new SimpleDateDNode<>(elemento, head, head.getPosterior());
        novoNode.getPosterior().setAnterior(novoNode);
        head.setPosterior(novoNode);

        size++;

        if (size > capacity) {

            size = capacity;

            head.setAnterior(head.getAnterior().getAnterior());
            head.getAnterior().setPosterior(head);
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Critério de filtro de DateDNode
     */
    protected interface Criterio {

        /**
         * Verifica se se verifica o critério
         *
         * @param node DateDNode a testar
         * @return true se se verifica o critério false senão
         */
        public boolean isMatch(DateDNode node);

    }

    /**
     * Cria e devolve um iterador onde os elementos iterados correspondem a um
     * determinado critério
     *
     * @param criterio critério ao qual os elementos iterados correspondem
     * @return um iterador onde os elementos iterados correspondem a um
     * determinado critério
     */
    protected Iterator<E> getIteradorPorCriterio(Criterio criterio) {

        Iterator<E> it = new Iterator() {

            DateDNode<E> cursor;

            {
                cursor = head.getPosterior();
                moveToNextMatch();
            }

            private void moveToNextMatch() {

                while (cursor != head && !criterio.isMatch(cursor)) {

                    cursor = cursor.getPosterior();

                }

            }

            @Override
            public boolean hasNext() {
                return cursor != head;
            }

            @Override
            public Object next() {

                E elem = cursor.getElemento();
                cursor = cursor.getPosterior();

                moveToNextMatch();

                return elem;
            }

        };

        return it;

    }

    @Override
    public Iterator<E> getIteradorPorCriterioDeData(CriterioData criterio) {

        return getIteradorPorCriterio(current -> {

            return criterio.isMatch(current.getDate());

        });

    }

    @Override
    public Iterator<E> iterator() {

        return getIteradorPorCriterio(current -> {

            return true;

        });

    }

}
