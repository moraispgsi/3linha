
package treslinha.model.Events;

import tads.interfaces.LinhaTres;
import treslinha.model.interfaces.PecaTresLinha;

/**
 * Representa o evento de um tabuleiro quando uma linha está cheia
 * @author Ricardo José Horta Morais
 */
public class EventoTabuleiroLinhaCheia {
    
    private final int indiceLinha;
    private final LinhaTres<PecaTresLinha> linha;
    
    /**
     * Construtor
     * @param indiceLinha indice da linha que ficou cheia
     * @param linha linha que ficou cheia
     */
    public EventoTabuleiroLinhaCheia(int indiceLinha, LinhaTres<PecaTresLinha> linha) {
        this.indiceLinha = indiceLinha;
        this.linha = linha;
    }
    
    /**
     * Devolve o indice da linha que ficou cheia
     * @return indice da linha que ficou cheia
     */
    public int getIndiceLinha() {
        return indiceLinha;
    }
    /**
     * Devolve a linha que ficou cheia
     * @return linha que ficou cheia
     */
    public LinhaTres<PecaTresLinha> getLinha() {
        return linha;
    }

}
