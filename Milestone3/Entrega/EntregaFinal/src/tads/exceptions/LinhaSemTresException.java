/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.exceptions;

/**
 * Ocorre quando uma linha não tem 3 elementos disponiveis
 *
 * @author Ricardo José Horta Morais
 */
public class LinhaSemTresException extends Exception {

    /**
     * Construtor
     */
    public LinhaSemTresException() {

        super("A linha não contêm 3 elementos.");

    }

}
