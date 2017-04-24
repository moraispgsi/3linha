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
public class ConjuntoAleatorioPecasNormais implements ConjuntoAleatorio<PecaTresLinha> {

    private final static ConjuntoAleatorioEstatico<PecaTresLinha> conjunto;

    static {
        PecaTresLinha peca1 = new PecaSimples("Peca1");
        PecaTresLinha peca2 = new PecaSimples("Peca2");
        PecaTresLinha peca3 = new PecaSimples("Peca3");
        PecaTresLinha peca4 = new PecaSimples("Peca4");
        PecaTresLinha peca5 = new PecaSimples("Peca5");

        conjunto = new ConjuntoAleatorioEstatico(peca1,
                peca2,
                peca3,
                peca4,
                peca5
        );
    }

    @Override
    public PecaTresLinha peek() {
        return conjunto.peek();
    }

}
