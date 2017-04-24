/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads.implementations;

import pt.ips.pa.model.tads.interfaces.ConjuntoAleatorio;
import java.util.Random;

/**
 * Implementação estática do @link ConjuntoAleatorio
 *
 * @see ConjuntoAleatorio
 * @author Ricardo José Horta Morais
 *
 * @param <E> tipo de elementos
 */
public class ConjuntoAletorioEstatico<E> implements ConjuntoAleatorio<E> {

    private final E[] elementos;

    /**
     * Constroi um conjunto aleatório com todos os elementos recebidos
     *
     * @param elementos elementos que serão guardados como possibilidades
     */
    public ConjuntoAletorioEstatico(E... elementos) {

        this.elementos = elementos;

    }

    @Override
    public E peek() {

        Random aleatorio = new Random();
        int indice = aleatorio.nextInt(elementos.length);
        return elementos[indice];

    }

}
