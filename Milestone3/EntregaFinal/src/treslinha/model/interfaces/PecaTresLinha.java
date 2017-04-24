package treslinha.model.interfaces;

import java.io.Serializable;
import tads.interfaces.LinhaTres;

/**
 * Representa um peça do jogo Três linha
 *
 * @author Ricardo José Horta Morais
 */
public interface PecaTresLinha extends Serializable {

    /**
     * Verifica se os objetos são iguais
     *
     * @param obj objeto a testar
     * @return true se forem iguais false senão
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Calcula um hashcode da peça
     *
     * @return hashcode da peça
     */
    @Override
    public int hashCode();

    /**
     * Devolve o nome da peça
     *
     * @return nome da peça
     */
    public String getNome();

    /**
     * Atua sobre a linha pelo lado esquerdo
     *
     * @param linha linha onde a peça foi colocada
     */
    public void executarEsquerda(LinhaTres<PecaTresLinha> linha);

    /**
     * Atua sobre a linha pelo lado direito
     *
     * @param linha linha onde a peça foi colocada
     */
    public void executarDireita(LinhaTres<PecaTresLinha> linha);

}
