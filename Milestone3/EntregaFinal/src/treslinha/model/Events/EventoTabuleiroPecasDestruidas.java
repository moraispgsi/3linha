
package treslinha.model.Events;

import tads.interfaces.LinhaTres;
import treslinha.model.interfaces.PecaTresLinha;


/**
 * Representa o evento de um tabuleiro quando peças foram destruidas
 * @author Ricardo José Horta Morais
 */
public class EventoTabuleiroPecasDestruidas {
    
    private final int numeroPecas;
    private final int indiceLinha;
    private final LinhaTres<PecaTresLinha> linha;
    
    /**
     * Construtor
     * @param numeroPecas numero de peças que foram destruidas
     * @param indiceLinha indice da linha onde as peças foram destruidas
     * @param linha linha onde as peças foram destruidas
     */
    public EventoTabuleiroPecasDestruidas(int numeroPecas, int indiceLinha, LinhaTres<PecaTresLinha> linha) {
        this.numeroPecas = numeroPecas;
        this.indiceLinha = indiceLinha;
        this.linha = linha;
    }
    
    /**
     * Devolve o indice da linha onde as peças foram destruidas
     * @return indice da linha onde as peças foram destruidas
     */
    public int getIndiceLinha() {
        return indiceLinha;
    }
    
    /**
     * Devolve a linha onde as peças foram destruidas
     * @return linha onde as peças foram destruidas
     */
    public LinhaTres<PecaTresLinha> getLinha() {
        return linha;
    }
    
    /**
     * Devolve o número de peças destruidas
     * @return número de peças destruidas
     */
    public int getNumeroPecas() {
        return numeroPecas;
    }
    
    

}
