/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADS;

import TADInterfaces.ConjuntoAleatorio;
import java.util.Random;

/**
 * Implementação estática do @link ConjuntoAleatorio  
 * 
 * @see ConjuntoAleatorio<E>
 * @author Ricardo José Horta Morais
 * 
 * @param <E> tipo de elementos
 */
public class ConjuntoAletorioEstatico<E> implements ConjuntoAleatorio<E> {

    private final E [] elementos;
  
    public ConjuntoAletorioEstatico(E ... elementos) {
        
        this.elementos = elementos;
        
    }

    @Override
    public E peek() {
        
        Random aleatorio = new Random();
        int indice = Math.abs(aleatorio.nextInt()) % elementos.length;
        return elementos[indice];

    }

}
