/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.treslinha.implementations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import pt.ips.pa.fase2.model.engine.exceptions.PontoDeRestauroInexistenteException;
import pt.ips.pa.fase2.model.engine.templates.Jogo;
import pt.ips.pa.fase2.model.engine.interfaces.EstrategiaPontuacao;
import static pt.ips.pa.fase2.model.treslinha.implementations.EventoJogo.Tipo.JOGADA_EXECUTADA;
import static pt.ips.pa.fase2.model.treslinha.implementations.EventoJogo.Tipo.JOGADA_RETROCEDIDA;
import static pt.ips.pa.fase2.model.treslinha.implementations.EventoJogo.Tipo.JOGO_INICIADO;
import static pt.ips.pa.fase2.model.treslinha.implementations.EventoJogo.Tipo.JOGO_TERMINADO;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;
import pt.ips.pa.fase1.tads.exceptions.CapacidadeNegativaException;
import pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException;
import pt.ips.pa.fase1.tads.exceptions.LinhaTresCapacidadeImparException;
import pt.ips.pa.fase1.tads.interfaces.ConjuntoAleatorio;
import pt.ips.pa.fase2.utils.implementations.UtilsSerializacao;

/**
 * Representa um Jogo Três linha
 *
 * @author Ricardo José Horta Morais
 */
public class JogoTresLinha extends Jogo {

    private int numeroRonda;
    private int numeroDePecasDestruidas;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean jogoTerminado = false;

    private TipoJogo tipo;
    private TabuleiroTresLinha tabuleiro;
    private EstrategiaPontuacao estrategiaPontuacao;

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

        super(id, "Três em Linha");

        numeroRonda = 1;
        numeroDePecasDestruidas = 0;
        dataHoraInicio = LocalDateTime.now();

        this.tipo = tipo;
        this.estrategiaPontuacao = estrategiaPontuacao;

        tabuleiro = new TabuleiroTresLinha(
                numeroLinhas,
                CapacidadeDasLinhas,
                conjuntoDePecasNormais,
                conjuntoDePecasEspeciais
        );

        notifyObserversEvento(JOGO_INICIADO, "O jogo foi iniciado");

    }

    @Override
    protected void restaurarEstado(Jogo jogo) {

        super.restaurarEstado(jogo);
        JogoTresLinha jogoConcreto = (JogoTresLinha) jogo;
        tabuleiro = jogoConcreto.tabuleiro;
        numeroDePecasDestruidas = jogoConcreto.numeroDePecasDestruidas;
        dataHoraInicio = jogoConcreto.dataHoraInicio;
        dataHoraFim = jogoConcreto.dataHoraFim;
        jogoTerminado = jogoConcreto.jogoTerminado;
        estrategiaPontuacao = jogoConcreto.estrategiaPontuacao;
        numeroRonda = jogoConcreto.numeroRonda;
        tipo = jogoConcreto.tipo;

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

        tabuleiro.selecionarLinha(i);

        drop();

    }

    /**
     * Deixa cair as peças na linha selecionada
     */
    public void drop() {

        if (jogoTerminado) {
            return;
        }

        try {

            criarMemento();
            numeroRonda++;

            if (tipo == TipoJogo.RAPIDO && numeroRonda > 20) {
                numeroRonda = 20;
                numeroDePecasDestruidas += tabuleiro.drop();
                terminarJogo();
                return;
            } else {
                numeroDePecasDestruidas += tabuleiro.drop();
            }

            notifyObserversEvento(JOGADA_EXECUTADA, "Drop efetuado na linha " + (tabuleiro.getLinhaSelecionada() + 1));

        } catch (LinhaCheiaException ex) {
            terminarJogo();
        }

    }

    /**
     * Seleciona a linha acima da anteriormente selecionada caso exista
     */
    public void subirDeLinha() {

        if (!jogoTerminado) {

            tabuleiro.subirDeLinha();
            notifyObserversEvento(JOGADA_EXECUTADA, "Subiu de linha");

        }

    }

    /**
     * Seleciona a linha abaixo da anteriormente selecionada caso exista
     */
    public void descerDeLinha() {

        if (!jogoTerminado) {

            tabuleiro.descerDeLinha();
            notifyObserversEvento(JOGADA_EXECUTADA, "Desceu de linha");

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
        return UtilsSerializacao.copiaSerializada(tabuleiro);
    }

}
