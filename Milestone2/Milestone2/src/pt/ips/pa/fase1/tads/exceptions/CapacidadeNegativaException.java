/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase1.tads.exceptions;

/**
 * Excessão que ocorre quando a capacidade é negativa
 *
 * @author Ricardo José Horta Morais
 */
public class CapacidadeNegativaException extends RuntimeException {

    /**
     * Construtor
     */
    public CapacidadeNegativaException() {
        super("A capacidade tem de ser maior que 0.");
    }

}
