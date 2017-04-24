package pt.ips.pa.bonus.treslinha.consola.interfaces;

import pt.ips.pa.bonus.treslinha.consola.implementations.Frame;

/**
 * Representa um cartucho que pode ser inserido numa consola
 *
 * @author Ricardo José Horta Morais
 */
public interface Cartucho {

    /**
     * Verifica se o cartucho terminou e deixou de receber input
     *
     * @return true se terminou false senão
     */
    public boolean isTerminado();

    /**
     * Devolve a referência para uma frame
     *
     * @return referência para uma frame
     */
    public Frame getFrame();

    /**
     * Interpreta um caracter de input e devolve o feedback
     *
     * @param caracter caracter de input
     * @return feedback
     */
    public String inputLetra(Character caracter);

    /**
     * Obriga o cartucho a terminar e a deixar de interpretar inputs
     */
    public void desconetar();

}
