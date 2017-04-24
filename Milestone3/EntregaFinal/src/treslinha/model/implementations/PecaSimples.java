/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.model.implementations;

import tads.exceptions.LinhaSemTresException;
import tads.interfaces.ConjuntoTriplo;
import tads.interfaces.LinhaTres;
import treslinha.model.interfaces.PecaTresLinha;

/**
 * Representa um peça simples. Esta peça tem a propriedade de destruir 2 peças
 * iguais e a própria peça no caso de estarem juntas
 *
 * @author Ricardo José Horta Morais
 */
public class PecaSimples implements PecaTresLinha {

    private String nome;

    /**
     * Construtor
     *
     * @param nome Nome da peça
     */
    public PecaSimples(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof PecaTresLinha) {
            PecaTresLinha peca = (PecaTresLinha) obj;
            return nome.equals(peca.getNome());
        }
        return false;

    }

    @Override
    public int hashCode() {

        return nome.hashCode();

    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void executarEsquerda(LinhaTres<PecaTresLinha> linha) {

        try {

            ConjuntoTriplo conjunto = linha.getFirst();
            if (conjunto.getEsquerdo().equals(conjunto.getCentro())
                    && conjunto.getCentro().equals(conjunto.getDireito())) {

                linha.removeFirst();

            }
        } catch (LinhaSemTresException ex) {

        }

    }

    @Override
    public void executarDireita(LinhaTres<PecaTresLinha> linha) {

        try {
            ConjuntoTriplo conjunto = linha.getLast();
            if (conjunto.getEsquerdo().equals(conjunto.getCentro())
                    && conjunto.getCentro().equals(conjunto.getDireito())) {

                linha.removeLast();

            }
        } catch (LinhaSemTresException ex) {

        }

    }

}
