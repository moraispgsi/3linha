/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.implementations;

import tads.interfaces.ConjuntoAleatorio;
import java.util.Random;

/**
 * Implementação estática do @link ConjuntoAleatorio
 *
 * @see ConjuntoAleatorio
 * @author Ricardo José Horta Morais
 *
 * @param <E> tipo de elementos
 */
public class ConjuntoAleatorioEstatico<E> implements ConjuntoAleatorio<E> {

    private final E[] elementos;
    private static final Random aleatorio = new Random();

    /**
     * Constroi um conjunto aleatório com todos os elementos recebidos
     *
     * @param elementos elementos que serão guardados como possibilidades
     */
    public ConjuntoAleatorioEstatico(E... elementos) {

        this.elementos = elementos;

    }

    @Override
    public E peek() {

        int indice = aleatorio.nextInt(elementos.length);
        return elementos[indice];

    }

}
