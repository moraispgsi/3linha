package pt.ips.pa.fase1.tads.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * O Histórico caracteriza-se por ser uma coleção de elementos, com tamanho
 * limitado, onde é apenas possível colocar elementos não sendo possível
 * retira-los. Os elementos são guardados por ordem de entrada para o histórico
 * e ficam associados a uma data (a data de entrada para o histórico). É
 * possível percorrer sequencialmente todos os elementos, assim como saber quais
 * os elementos de um determinado dia, ou dos últimos dias ou do mês corrente
 * etc. Quando o número de elementos é atingido, os elementos mais antigos são
 * descartados automaticamente.
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elementos contidos no conjunto
 */
public interface Historico<E> extends Iterable<E>, Serializable {

    /**
     * Devolve o número de elementos presentes no historico
     *
     * @return número de elementos presentes no historico
     */
    public int size();

    /**
     * Devolve true se o historico estiver vazio false senão
     *
     * @return true se o historico estiver vazio false senão
     */
    public boolean isEmpty();

    /**
     * Devolve a capacidade do historico
     *
     * @return capacidade do historico
     */
    public int getCapacity();

    /**
     * Adiciona um elemento ao historico
     *
     * @param elemento elemento a ser inserido
     */
    public void add(E elemento);

    /**
     * Critério de filtro pela data Usa o padrão Filter
     */
    public interface CriterioData {

        /**
         * Testa se a data corresponde ao critério
         *
         * @param data data a testar
         * @return true se corresponde ao critério false senão
         */
        public boolean isMatch(LocalDateTime data);

    }

    /**
     * Cria um iterador que itera os elementos que correspondem a um critério
     *
     * @param criterio critério de correspondência
     * @return iterador que itera os elementos que correspondem a um critério
     */
    public Iterator<E> getIteradorPorCriterioDeData(CriterioData criterio);

    /**
     * Cria um iterador que itera os elementos criados num determinado dia
     *
     * @param <E> Tipo do elemento
     * @param historico Historico
     * @param dia dia dos elementos
     * @return um iterador que itera os elementos criados num determinado dia
     */
    public static <E> Iterator<E> getDiaIterador(Historico<E> historico, LocalDateTime dia) {

        return historico.getIteradorPorCriterioDeData(data -> {

            return (data.getDayOfYear() == dia.getDayOfYear()
                    && data.getYear() == dia.getYear());

        });

    }

    /**
     * Cria um iterador que itera os elementos criados neste mês
     *
     * @param <E> Tipo do elemento
     * @param historico Historico
     * @return um iterador que itera os elementos criados num determinado dia
     */
    public static <E> Iterator<E> getMesIterador(Historico<E> historico) {

        final LocalDateTime hoje = LocalDateTime.now();
        return historico.getIteradorPorCriterioDeData(data -> {

            return (data.getMonthValue() == hoje.getMonthValue()
                    && data.getYear() == hoje.getYear());

        });

    }

    /**
     * Cria um iterador que itera os elementos criados nos ultimos X dias
     *
     * @param <E> Tipo do elemento
     * @param historico Historico
     * @param numeroDeDias numero de dias a partir da data de hoje
     * @return um iterador que itera os elementos criados nos ultimos X dias
     */
    public static <E> Iterator<E> getUltimosDiasIterador(Historico<E> historico, int numeroDeDias) {

        final LocalDateTime ultimoDia = LocalDateTime.now().minusDays(numeroDeDias);

        return historico.getIteradorPorCriterioDeData(data -> {

            return !data.isBefore(ultimoDia);

        });

    }

}
