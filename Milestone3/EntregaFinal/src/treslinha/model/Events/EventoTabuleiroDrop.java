
package treslinha.model.Events;

import tads.interfaces.LinhaTres;
import treslinha.model.interfaces.PecaTresLinha;

/**
 * Representa um o evento de drop de um tabuleiro.
 * Ocorre quando são inseridas peças no tabuleiro.
 * @author Ricardo José Horta Morais
 */
public class EventoTabuleiroDrop {

    private final PecaTresLinha dropEsquerda;
    private final PecaTresLinha dropDireita;

    private final int indiceLinha;
    private final LinhaTres<PecaTresLinha> linha;

    public EventoTabuleiroDrop(PecaTresLinha dropEsquerda, PecaTresLinha dropDireita, int indiceLinha, LinhaTres<PecaTresLinha> linha) {
        this.dropEsquerda = dropEsquerda;
        this.dropDireita = dropDireita;

        this.indiceLinha = indiceLinha;
        this.linha = linha;
    }
    
    /**
     * Devolve a drop esquerda
     * @return drop esquerda
     */
    public PecaTresLinha getDropEsquerda() {
        return dropEsquerda;
    }
    
    /**
     * Devolve a drop direita
     * @return drop direita
     */
    public PecaTresLinha getDropDireita() {
        return dropDireita;
    }
    
    /**
     * Devolve o indice da linha onde foram inseridas as drops
     * @return indice da linha onde foram inseridas as drops
     */
    public int getIndiceLinha() {
        return indiceLinha;
    }
    
    /**
     * Devolve a linha onde foram inseridas as drops
     * @return linha onde foram inseridas as drops
     */
    public LinhaTres<PecaTresLinha> getLinha() {
        return linha;
    }

}
