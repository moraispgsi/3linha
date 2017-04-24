

package TADInterfaces;

import java.util.Iterator;

/**
 * O Histórico caracteriza-se por ser uma coleção de elementos, com tamanho limitado,
 * onde é apenas possível colocar elementos não sendo possível retira-los. Os
 * elementos são guardados por ordem de entrada para o histórico e ficam associados a
 * uma data (a data de entrada para o histórico). É possível percorrer sequencialmente
 * todos os elementos, assim como saber quais os elementos de um determinado dia,
 * ou dos últimos dias ou do mês corrente etc. Quando o número de elementos é
 * atingido, os elementos mais antigos são descartados automaticamente.

 * @author Ricardo José Horta Morais
 * @param <E> tipo de elementos contidos no conjunto
 */
public interface Historico<E> extends Iterable<E>{
    
    
    /**
     * Devolve o número de elementos presentes no historico
     * @return número de elementos presentes no historico
     */
    public int size();
    /**
     * Devolve true se o historico estiver vazio false senão
     * @return true se o historico estiver vazio false senão
     */
    public boolean isEmpty();
    
    /**
     * Devolve a capacidade do historico
     * @return capacidade do historico
     */
    public int getCapacity();
    /**
     * Adiciona um elemento ao historico
     * @param elemento elemento a ser inserido
     */
    public void add(E elemento);

}
