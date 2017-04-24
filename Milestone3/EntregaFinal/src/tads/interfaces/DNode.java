package tads.interfaces;

/**
 * Representa um double ended node ou seja um node que liga ao anterior e ao
 * posterior e este guarda um unico elemento do tipo generico
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elemento
 */
public interface DNode<E> extends Node<E> {

    /**
     * Getter do node anterior relativamente a este
     *
     * @return node anterior relativamente a este
     */
    public DNode getAnterior();

    /**
     * Setter do node anterior relativamente a este
     *
     * @param anterior node anterior relativamente a este
     */
    public void setAnterior(DNode<E> anterior);

    @Override
    public DNode<E> getPosterior();

    @Override
    public void setPosterior(Node<E> posterior);

}
