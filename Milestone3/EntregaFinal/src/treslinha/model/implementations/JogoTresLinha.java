/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.model.implementations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import engine.exceptions.PontoDeRestauroInexistenteException;
import engine.interfaces.EstrategiaPontuacao;
import engine.interfaces.Jogo;
import engine.interfaces.Restauravel;
import engine.implementations.BackupMemento;
import engine.interfaces.Recuperavel;
import java.io.Serializable;
import tads.exceptions.CapacidadeNegativaException;
import tads.exceptions.LinhaCheiaException;
import tads.exceptions.LinhaTresCapacidadeImparException;
import tads.interfaces.ConjuntoAleatorio;
import treslinha.model.interfaces.PecaTresLinha;
import treslinha.utils.EventoJogo;
import static treslinha.utils.EventoJogo.Tipo.JOGADA_EXECUTADA;
import static treslinha.utils.EventoJogo.Tipo.JOGADA_RETROCEDIDA;
import static treslinha.utils.EventoJogo.Tipo.JOGO_INICIADO;
import static treslinha.utils.EventoJogo.Tipo.JOGO_TERMINADO;

/**
 *
 * @author Morai
 */
public class JogoTresLinha extends Observable implements Serializable, Jogo, Recuperavel {

    private final int id;
    private static final String nome = "JogoTresLinha";
    private int numeroRonda = 1;
    private int numeroDePecasDestruidas = 0;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean jogoTerminado = false;

    private TipoJogo tipo = null;
    private TabuleiroTresLinha tabuleiro = null;
    private EstrategiaPontuacao estrategiaPontuacao = null;

    transient private BackupMemento backup;

    /**
     * Construtor
     *
     * @param id id associado ao jogo
     * @param numeroLinhas número de linhas do jogo
     * @param CapacidadeDasLinhas capacidade das linhas
     * @param tipo tipo de jogo
     * @param estrategiaPontuacao estrategia de calculo de pontuação
     * @param conjuntoDePecasNormais conjunto aleatório de peças normais
     * @param conjuntoDePecasEspeciais conjunto aleatório de peças especiais
     * @throws CapacidadeNegativaException ocorre quando a capacidade das linhas
     * é negativa
     * @throws LinhaTresCapacidadeImparException ocorre quando a capacidade das
     * linhas é impar
     */
    public JogoTresLinha(int id, int numeroLinhas, int CapacidadeDasLinhas,
            TipoJogo tipo, EstrategiaPontuacao estrategiaPontuacao,
            ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasNormais,
            ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasEspeciais
    ) throws CapacidadeNegativaException, LinhaTresCapacidadeImparException {

        this.id = id;
        this.backup = new BackupMemento((Restauravel) this);
        this.numeroRonda = 1;
        this.numeroDePecasDestruidas = 0;
        this.dataHoraInicio = LocalDateTime.now();
        this.tipo = tipo;
        this.estrategiaPontuacao = estrategiaPontuacao;

        this.tabuleiro = new TabuleiroTresLinha(
                numeroLinhas,
                CapacidadeDasLinhas,
                conjuntoDePecasNormais,
                conjuntoDePecasEspeciais
        );

        notifyObserversEvento(JOGO_INICIADO, "O jogo foi iniciado");

    }

    @Override
    public void restaurarEstado(Restauravel restauravel) {

        if (restauravel instanceof JogoTresLinha) {

            JogoTresLinha jogo = (JogoTresLinha) restauravel;

            numeroRonda = jogo.numeroRonda;
            numeroDePecasDestruidas = jogo.numeroDePecasDestruidas;
            dataHoraInicio = jogo.dataHoraInicio;
            dataHoraFim = jogo.dataHoraFim;
            jogoTerminado = jogo.jogoTerminado;
            tipo = jogo.tipo;
            this.tabuleiro.restaurarEstado(jogo.tabuleiro);
            estrategiaPontuacao = jogo.estrategiaPontuacao;

        }

    }

    @Override
    public void restaurarPontoAnterior() throws PontoDeRestauroInexistenteException {
        backup.restaurarPontoAnterior();
    }

    @Override
    public void criarPontoDeRestauro() {
        backup.criarMemento();
    }

    /**
     * Verifica se o jogo terminou
     *
     * @return true se o jogo terminou false senão
     */
    public boolean isJogoTerminado() {
        return jogoTerminado;
    }

    /**
     * Obriga o jogo a terminar
     */
    public void terminarJogo() {

        if (jogoTerminado) {
            return;
        }

        jogoTerminado = true;
        dataHoraFim = LocalDateTime.now();

        notifyObserversEvento(JOGO_TERMINADO, "O jogo terminou.");

    }

    /**
     * Deixa cair as duas peças na linha indicada
     *
     * @param i indice da linha
     */
    public void drop(int i) {

        if (jogoTerminado) {
            return;
        }

        try {

            criarPontoDeRestauro();
            numeroRonda++;

            if (tipo == TipoJogo.RAPIDO && numeroRonda > 20) {
                numeroRonda = 20;
                numeroDePecasDestruidas += tabuleiro.drop(i);
                terminarJogo();
                return;
            } else {
                numeroDePecasDestruidas += tabuleiro.drop(i);
            }

            notifyObserversEvento(JOGADA_EXECUTADA, "Drop efetuado na linha " + (i + 1));

        } catch (LinhaCheiaException ex) {
            terminarJogo();
        }

    }

    /**
     * Retrocede uma jogada caso o jogo não tenha terminado
     *
     * @throws PontoDeRestauroInexistenteException caso não exista ponto de
     * restauro
     */
    public void undo() throws PontoDeRestauroInexistenteException {

        try {

            if (!jogoTerminado) {

                restaurarPontoAnterior();
                notifyObserversEvento(JOGADA_RETROCEDIDA, "A jogada foi retrocedida.");

            }

        } catch (PontoDeRestauroInexistenteException ex) {
            throw new PontoDeRestauroInexistenteException();
        }

    }

    private void notifyObserversEvento(EventoJogo.Tipo tipo, String descricao) {

        EventoJogo evento = new EventoJogo(tipo, descricao);
        setChanged();
        notifyObservers(evento);

    }

    /**
     * Devolve o tipo de jogo
     *
     * @return tipo de jogo
     */
    public TipoJogo getTipo() {
        return tipo;
    }

    /**
     * Devolve o número da ronda
     *
     * @return número da ronda
     */
    public int getNumeroRonda() {
        return numeroRonda;
    }

    /**
     * Devolve o número de peças destruidas
     *
     * @return número de peças destruidas
     */
    public int getNumeroDePecasDestruidas() {
        return numeroDePecasDestruidas;
    }

    /**
     * Devolve o número de segundos que passaram desde o inicio do jogo até ao
     * momento caso o jogo tenha terminado será devolvido os segundos de jogo
     *
     * @return segundo de jogo
     */
    public int getSegundosDeJogo() {

        if (isJogoTerminado()) {
            return (int) dataHoraInicio.until(dataHoraFim, ChronoUnit.SECONDS);
        } else {
            return (int) dataHoraInicio.until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }

    }

    /**
     * Calcula a pontuação através da estratégia de pontuação
     *
     * @return pontuação atual
     */
    public int getPontuacao() {
        return estrategiaPontuacao.calcularPontuacao(numeroDePecasDestruidas, getSegundosDeJogo());
    }

    /**
     * Devolve um cópia do tabuleiro
     *
     * @return cópia do tabuleiro
     */
    public TabuleiroTresLinha getTabuleiro() {
        return tabuleiro;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getId() {
        return id;
    }

}
