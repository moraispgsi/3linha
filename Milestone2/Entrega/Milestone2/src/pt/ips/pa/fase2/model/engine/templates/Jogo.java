/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.engine.templates;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;
import pt.ips.pa.fase2.model.engine.exceptions.PontoDeRestauroInexistenteException;
import pt.ips.pa.fase2.utils.implementations.UtilsSerializacao;

/**
 * Template de um jogo que implementa um padrão memento com serialização.
 * Qualquer subclasse terá apenas que fazer o override do método
 * restaurarEstado(Jogo jogo) para poder beneficiar da funcionalidade de
 * restauro. É de notar que o memento é guardado através da serialização.
 *
 * @author Ricardo José Horta Morais
 */
public abstract class Jogo extends Observable implements Serializable {

    private final int id;

    private String nomeDoJogo;

    /**
     * Stack de momentos é protegida da serialização mas pode ser usada numa
     * subclasse
     */
    transient protected Stack<Memento> stackMementos = new Stack();

    /**
     * Construtor
     *
     * @param id id atribuido a este jogo
     * @param nomeDoJogo nome do jogo
     */
    protected Jogo(int id, String nomeDoJogo) {
        this.nomeDoJogo = nomeDoJogo;
        this.id = id;
    }

    /**
     * Restaura um ponto de restauro anterior caso exista
     *
     * @throws PontoDeRestauroInexistenteException caso não exista ponto de
     * restauro
     */
    public void restaurarPontoAnterior() throws PontoDeRestauroInexistenteException {

        if (stackMementos.isEmpty()) {
            throw new PontoDeRestauroInexistenteException();
        }

        Memento memento = stackMementos.pop();

        memento.restaurar();

    }

    /**
     * Cria um memento do estado atual
     */
    protected void criarMemento() {

        Memento memento = new Memento(UtilsSerializacao.serializar(Jogo.this));
        stackMementos.push(memento);

    }

    /**
     * Restaura o estado a partir do jogo poderá ser feito override, este método
     * é chamado quando um memento é restaurado em caso subclasse é possível
     * fazer um cast para a subclass de Jogo e retirar os valor dos atributos.
     *
     * @param jogo jogo do memento
     */
    protected void restaurarEstado(Jogo jogo) {
        nomeDoJogo = jogo.nomeDoJogo;
    }

    /**
     * Devolve o nome do jogo
     *
     * @return nome do jogo
     */
    public String getNomeDoJogo() {
        return nomeDoJogo;
    }

    /**
     * Devolve o id associado a este Jogo
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Representa um ponto de Restauro
     */
    protected class Memento {

        private byte[] dados;

        /**
         * Construtor
         *
         * @param dados dados serializados de um ponto de restauro
         */
        protected Memento(byte[] dados) {
            this.dados = dados;
        }

        /**
         * Restaura o memento pedindo ao jogo para alterar o seu estado a partir
         * dos dados deserializados
         */
        public void restaurar() {
            restaurarEstado(UtilsSerializacao.deserializar(Jogo.class, dados));
        }

    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        stackMementos = new Stack();

    }

}
