/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.engine.exceptions;

/**
 *
 * @author Morai
 */
public class PontoDeRestauroInexistenteException extends Exception {

    public PontoDeRestauroInexistenteException() {
        super("Ponto de restauro não existe.");
    }

}
