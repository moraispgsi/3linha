package pt.ips.pa.fase1.tads.interfaces;

import java.io.Serializable;
import java.util.stream.Stream;
import pt.ips.pa.fase1.tads.exceptions.ElementoExistenteException;
import pt.ips.pa.fase1.tads.exceptions.IndiceNaoExistenteException;

/**
 * Um ranking é uma lista ordenada de elementos, em que os elementos são
 * ordenados segundo um critério específico.
 *
 * @author Ricardo José Horta Morais
 * @param <E> tipo de elemento
 */
public interface Ranking<E> extends Iterable<E>, Serializable {

    /**
     * Devolve o elemento que está no indice
     *
     * @param indice indice onde está o elemento
     * @return elemento do indice
     * @throws pt.ips.pa.fase1.tads.exceptions.IndiceNaoExistenteException Não
     * existe elemento associado ao índice
     */
    public E get(int indice) throws IndiceNaoExistenteException;

    /**
     * Altera um elemento existente presente no indice
     *
     * @param indice indice onde está o elemento
     * @param elemento elemento que vai substituir
     * @throws pt.ips.pa.fase1.tads.exceptions.IndiceNaoExistenteException Não
     * existe elemento associado ao índice
     * @return elemento substituido
     */
    public E set(int indice, E elemento) throws IndiceNaoExistenteException;

    /**
     * Adiciona um elemento
     *
     * @param elemento elemento que irá ser adicionado
     * @throws pt.ips.pa.fase1.tads.exceptions.ElementoExistenteException ocorre
     * tentamos inserir um elemento já existente
     */
    public void adicionar(E elemento) throws ElementoExistenteException;

    /**
     * Remove um elemento presente no índice
     *
     * @param indice indice do elemento a remover
     * @throws pt.ips.pa.fase1.tads.exceptions.IndiceNaoExistenteException Não
     * existe elemento associado ao índice
     * @return elemento removido
     */
    public E remover(int indice) throws IndiceNaoExistenteException;

    /**
     * Remove um elemento
     *
     * @param elemento elemento a eliminar
     */
    public void remover(E elemento);

    /**
     * Devolve true se o ranking estiver vazio false senão
     *
     * @return true se o ranking estiver vazio false senão
     */
    public boolean isEmpty();

    /**
     * Devolve o número de elementos presentes no ranking
     *
     * @return número de elementos presentes no ranking
     */
    public int size();

    /**
     * Verifica se o elemento já existe no ranking
     *
     * @param elemento elemento a testar
     * @return true se já existir o elemento no ranking, false senão
     */
    public boolean hasElement(E elemento);

    /**
     * Devolve o indice do elemento no ranking, -1 se não encontrado
     *
     * @param elemento elemento a procurar
     * @return indice do elemento no ranking, -1 se não encontrado
     */
    public int indexOf(E elemento);

    /**
     * Devolve um stream
     *
     * @return stream
     */
    public Stream<E> stream();
}
