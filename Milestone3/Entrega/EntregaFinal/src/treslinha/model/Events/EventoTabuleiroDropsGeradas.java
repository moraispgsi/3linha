
package treslinha.model.Events;

import treslinha.model.interfaces.PecaTresLinha;

/**
 * Representa o evento de um tabuleiro quando novas drops são geradas
 * @author Ricardo José Horta Morais
 */
public class EventoTabuleiroDropsGeradas {

    private final PecaTresLinha dropEsquerdaAntiga;
    private final PecaTresLinha dropDireitaAntiga;

    private final PecaTresLinha dropEsquerda;
    private final PecaTresLinha dropDireita;
    
    /**
     * Construtor
     * @param dropEsquerdaAntiga drop esquerda antiga
     * @param dropDireitaAntiga drop direita antiga
     * @param dropEsquerda drop esquerda nova
     * @param dropDireita  drop direita nova
     */
    public EventoTabuleiroDropsGeradas(PecaTresLinha dropEsquerdaAntiga, PecaTresLinha dropDireitaAntiga, PecaTresLinha dropEsquerda, PecaTresLinha dropDireita) {
        this.dropEsquerdaAntiga = dropEsquerdaAntiga;
        this.dropDireitaAntiga = dropDireitaAntiga;
        this.dropEsquerda = dropEsquerda;
        this.dropDireita = dropDireita;
    }
    
    /**
     * Devolve a drop esquerda antiga
     * @return drop esquerda antiga
     */
    public PecaTresLinha getDropEsquerdaAntiga() {
        return dropEsquerdaAntiga;
    }
    
    /**
     * Devolve a drop direita antiga
     * @return drop direita antiga
     */
    public PecaTresLinha getDropDireitaAntiga() {
        return dropDireitaAntiga;
    }
    
    /**
     * Devolve a drop esquerda nova
     * @return drop esquerda nova
     */
    public PecaTresLinha getDropEsquerda() {
        return dropEsquerda;
    }
    
    /**
     * Devolve a drop direita nova
     * @return drop direita nova
     */
    public PecaTresLinha getDropDireita() {
        return dropDireita;
    }

}
