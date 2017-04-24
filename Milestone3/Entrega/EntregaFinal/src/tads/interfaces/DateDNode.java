package tads.interfaces;

import java.time.LocalDateTime;

/**
 * Representa um date double ended node ou seja um node que liga ao anterior e
 * ao posterior e este guarda um unico elemento do tipo generico e que tem uma
 * data associada
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elemento
 */
public interface DateDNode<E> extends DNode<E> {

    /**
     * Getter da data associada
     *
     * @return data associada
     */
    public LocalDateTime getDate();

    /**
     * Setter da data associada
     *
     * @param date data associada
     */
    public void setDate(LocalDateTime date);

    @Override
    public DateDNode<E> getPosterior();

    @Override
    public DateDNode getAnterior();

}
