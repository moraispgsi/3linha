/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.exceptions;

/**
 * Ocorre quando um linha está cheia
 *
 * @author Ricardo José Horta Morais
 */
public class LinhaCheiaException extends Exception {

    /**
     * Construtor
     */
    public LinhaCheiaException() {
        super("Linha cheia.");
    }

}
