/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.implementations;

import tads.interfaces.ConjuntoTriplo;

/**
 * Implementação simples de um ConjuntoTriplo
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo dos elementos
 */
public class SimpleConjuntoTriplo<E> implements ConjuntoTriplo<E> {

    private final E esquerdo;
    private final E centro;
    private final E direito;

    /**
     * Construtor
     *
     * @param esquerdo Elemento mais à esquerda
     * @param centro Elemento ao centro
     * @param direito Elemento à direita
     */
    public SimpleConjuntoTriplo(E esquerdo, E centro, E direito) {

        if (esquerdo == null
                || centro == null
                || direito == null) {
            throw new RuntimeException("Conjunto inválido.");
        }

        this.esquerdo = esquerdo;
        this.centro = centro;
        this.direito = direito;
    }

    @Override
    public E getEsquerdo() {
        return esquerdo;
    }

    @Override
    public E getCentro() {
        return centro;
    }

    @Override
    public E getDireito() {
        return direito;
    }

}
