/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.controller.treslinha.implementations;

import pt.ips.pa.fase2.model.engine.templates.Jogo;
import pt.ips.pa.fase2.utils.implementations.LoggerSimples;
import pt.ips.pa.fase2.utils.interfaces.Logger;

/**
 * Representa o logger de um gestor de jogos
 *
 * @author Ricardo José Horta Morais
 */
public class LoggerGestor implements Logger {

    private Logger baseLogger;
    private boolean isLigado;
    private boolean registarJogadorCriado;
    private boolean registarJogoCriado;

    /**
     * Construtor
     *
     * @param directoria diretoria on é guardado o ficheiro
     * @param isLigado se o logger está ligado
     * @param registarRegistroJogador se está a registrar os jogadores criados
     * @param registarJogoCriado se está a registar os jogos criados
     */
    public LoggerGestor(String directoria, boolean isLigado, boolean registarRegistroJogador, boolean registarJogoCriado) {
        baseLogger = new LoggerSimples(directoria + "/Gestor.log");
        this.isLigado = isLigado;
        this.registarJogadorCriado = registarRegistroJogador;
        this.registarJogoCriado = registarJogoCriado;
    }

    /**
     * Devolve se está ligado
     *
     * @return true se estiver ligado, false senão
     */
    public boolean isLigado() {
        return isLigado;
    }

    /**
     * Liga o logger
     */
    public void ligar() {
        this.isLigado = true;
    }

    /**
     * Desliga o logger
     */
    public void desligar() {
        this.isLigado = true;
    }

    /**
     * Regista no logger que foi criado um novo jogador
     *
     * @param nick nick do jogador
     */
    public void registarJogadorCriado(String nick) {

        if (canRegistarJogadorCriado()) {
            adicionarRegisto("Foi criado o jogador [" + nick + "]");
        }

    }

    /**
     * Se está a registar jogadores criados
     *
     * @return true se estiver a registar, false senão
     */
    public boolean isRegistarJogadorCriadoChecked() {
        return registarJogadorCriado;
    }

    /**
     * Passa registar jogadores criados
     */
    public void checkRegistarJogadorCriado() {
        this.registarJogadorCriado = true;
    }

    /**
     * Deixa de registar jogadores criados
     */
    public void uncheckRegistarJogadorCriado() {
        this.registarJogadorCriado = false;
    }

    /**
     * Se é possível registar jogadores criados
     *
     * @return true se é possível registar jogadores criados, false senão
     */
    public boolean canRegistarJogadorCriado() {
        return isLigado && registarJogadorCriado;
    }

    /**
     * Regista um jogo criado
     *
     * @param jogo jogo criado
     * @param tipo tipo de jogo
     * @param nickJogador nick do jogador que jogou o jogo
     */
    public void registarJogoCriado(Jogo jogo, String tipo, String nickJogador) {

        if (canRegistarJogoCriado()) {
            adicionarRegisto("Um jogo [" + jogo.getNomeDoJogo() + "] ID [" + jogo.getId() + "] Tipo [" + tipo + "] foi criado para o jogador [" + nickJogador + "]");
        }

    }

    /**
     * Se está a registar os jogos criados
     *
     * @return true se está a registar os jogos criados, false senão
     */
    public boolean isRegistarJogoCriadoChecked() {
        return registarJogoCriado;
    }

    /**
     * Passa a registar jogos criados
     */
    public void checkRegistarJogoCriado() {
        this.registarJogoCriado = true;
    }

    /**
     * Deixa de registar jogos criados
     */
    public void uncheckRegistarJogoCriado() {
        this.registarJogoCriado = false;
    }

    /**
     * Devolve se é possível registar um jogo criado
     *
     * @return true se é possível registar um jogo criado, false senão
     */
    public boolean canRegistarJogoCriado() {
        return isLigado && registarJogoCriado;
    }

    @Override
    public void adicionarRegisto(String registo) {
        baseLogger.adicionarRegisto(registo);
    }

}
