/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.model.implementations;

import treslinha.model.interfaces.PecaTresLinha;
import tads.implementations.ConjuntoAleatorioEstatico;
import tads.interfaces.ConjuntoAleatorio;

/**
 *
 * @author Morai
 */
public class ConjuntoAleatorioPecasEspeciais implements ConjuntoAleatorio<PecaTresLinha> {

    private final static ConjuntoAleatorioEstatico<PecaTresLinha> conjunto;

    static {

        PecaTresLinha pecaCaveira = new PecaCaveira("PecaCaveira");
        conjunto = new ConjuntoAleatorioEstatico(pecaCaveira);

    }

    @Override
    public PecaTresLinha peek() {
        return conjunto.peek();
    }

}
