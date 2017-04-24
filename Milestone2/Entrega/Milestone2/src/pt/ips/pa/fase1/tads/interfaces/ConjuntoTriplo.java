package pt.ips.pa.fase1.tads.interfaces;

import java.io.Serializable;

/**
 * Conjunto imutavel de 3 elementos seguidos e ordenados
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elementos contidos no conjunto
 */
public interface ConjuntoTriplo<E> extends Serializable {

    /**
     * Getter para o elemento mais à esquerda
     *
     * @return elemento mais à esquerda
     */
    public E getEsquerdo();

    /**
     * Getter para o elemento do centro
     *
     * @return elemento mais à esquerda
     */
    public E getCentro();

    /**
     * Getter para o elemento mais à direita
     *
     * @return elemento mais à direita
     */
    public E getDireito();

}
