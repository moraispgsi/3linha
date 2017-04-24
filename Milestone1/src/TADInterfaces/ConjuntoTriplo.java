/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADInterfaces;

/**
 * Conjunto imutavel de 3 elementos seguidos e ordenados
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elementos contidos no conjunto
 */
public class ConjuntoTriplo<E> {

    private final E esquerdo;
    private final E centro;
    private final E direito;
    
    public ConjuntoTriplo(E esquerdo, E centro, E direito) {

        if (esquerdo == null
                || centro == null
                || direito == null) {
            throw new RuntimeException("Conjunto inválido.");
        }

        this.esquerdo = esquerdo;
        this.centro = centro;
        this.direito = direito;
    }

    public E getEsquerdo() {
        return esquerdo;
    }

    public E getCentro() {
        return centro;
    }

    public E getDireito() {
        return direito;
    }
    
    

}
