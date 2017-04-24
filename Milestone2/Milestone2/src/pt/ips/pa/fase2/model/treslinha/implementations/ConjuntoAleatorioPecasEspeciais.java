/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.treslinha.implementations;

import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;
import pt.ips.pa.fase1.tads.implementations.ConjuntoAleatorioEstatico;
import pt.ips.pa.fase1.tads.interfaces.ConjuntoAleatorio;

/**
 *
 * @author Morai
 */
public class ConjuntoAleatorioPecasEspeciais implements ConjuntoAleatorio<PecaTresLinha> {

    private final static ConjuntoAleatorioEstatico<PecaTresLinha> conjunto;

    static {

        PecaTresLinha pecaCaveira = new PecaCaveira("Peca caveira");
        conjunto = new ConjuntoAleatorioEstatico(pecaCaveira);

    }

    @Override
    public PecaTresLinha peek() {
        return conjunto.peek();
    }

}
