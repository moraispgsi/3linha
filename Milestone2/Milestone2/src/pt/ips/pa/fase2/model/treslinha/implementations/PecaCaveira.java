package pt.ips.pa.fase2.model.treslinha.implementations;

import pt.ips.pa.fase1.tads.interfaces.LinhaTres;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;

/**
 * Representa um peça caveira. Esta peça tem a propriedade de destruir por
 * completo todos os elementos de uma linha.
 *
 * @author Ricardo José Horta Morais
 */
public class PecaCaveira implements PecaTresLinha {

    private PecaSimples peca;

    /**
     * Construtor
     *
     * @param nome nome associado à peça
     */
    public PecaCaveira(String nome) {
        peca = new PecaSimples(nome);
    }

    @Override
    public void executarDireita(LinhaTres<PecaTresLinha> linha) {
        linha.clear();
    }

    @Override
    public void executarEsquerda(LinhaTres<PecaTresLinha> linha) {
        linha.clear();
    }

    @Override
    public String getNome() {
        return peca.getNome();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PecaCaveira)) {
            return false;
        }
        return peca.equals(obj);
    }

    @Override
    public int hashCode() {
        return peca.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

}
