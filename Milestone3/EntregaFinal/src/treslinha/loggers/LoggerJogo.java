package treslinha.loggers;

import treslinha.utils.EventoJogo;
import java.util.Observable;
import java.util.Observer;
import treslinha.model.implementations.JogoTresLinha;
import utils.implementations.LoggerSimples;
import utils.interfaces.Logger;

/**
 * Representa o logger de um jogo
 *
 * @author Ricardo José Horta Morais
 */
public class LoggerJogo implements Logger, Observer {

    private Logger baseLogger;
    private boolean isLigado;
    private boolean registarJogadas;
    private boolean registarUndo;
    private boolean registarInicioFim;

    /**
     * Construtor
     *
     * @param directorio diretorio onde será guardado o ficheiro
     * @param nomeLog nome do ficheiro
     * @param isLigado se começa ligado
     * @param registarJogadas se regista jogadas
     * @param registarInicioFim se regista o inicio e o fim de um jogo
     * @param registarUndo se registra as jogadas retrocedidas
     */
    public LoggerJogo(String directorio, String nomeLog, boolean isLigado, boolean registarJogadas, boolean registarInicioFim, boolean registarUndo) {

        baseLogger = new LoggerSimples(directorio + "\\" + nomeLog + ".log");
        this.isLigado = isLigado;
        this.registarJogadas = registarJogadas;
        this.registarInicioFim = registarInicioFim;
        this.registarUndo = registarUndo;
    }

    /**
     * Devolve se está ligado
     *
     * @return se está ligado
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
        this.isLigado = false;
    }

    private void registarJogada(JogoTresLinha jogo, String jogada) {

        if (canRegistarJogadas()) {
            adicionarRegisto("Jogo [" + jogo.getNome() + "] ID[" + jogo.getId() + "]: Jogada efetuada - " + jogada);
        }

    }

    /**
     * Se está a registar as jogadas
     *
     * @return true está a registar as jogadas false senão
     */
    public boolean isRegistarJogadasChecked() {
        return registarJogadas;
    }

    /**
     * Passa a registar as jogadas
     */
    public void checkRegistarJogadas() {
        this.registarJogadas = true;
    }

    /**
     * Deixa de registar as jogadas
     */
    public void uncheckRegistarJogadas() {
        this.registarJogadas = false;
    }

    private boolean canRegistarJogadas() {
        return isLigado && registarJogadas;
    }

    private void registarInicio(JogoTresLinha jogo) {

        if (canRegistarInicioFim()) {
            adicionarRegisto("Jogo [" + jogo.getNome() + "] ID[" + jogo.getId() + "]: Iniciado");
        }

    }

    private void registarFim(JogoTresLinha jogo) {

        if (canRegistarInicioFim()) {
            adicionarRegisto("Jogo [" + jogo.getNome() + "] ID[" + jogo.getId() + "]: Terminou");
        }

    }

    /**
     * Se está a registar o inicio e fim de jogo
     *
     * @return true se está a registar o inicio e fim de jogo, false senão
     */
    public boolean isRegistarInicioFimChecked() {
        return registarInicioFim;
    }

    /**
     * Pass a registar o inicio e fim do jogo
     */
    public void checkRegistarInicioFim() {
        this.registarInicioFim = true;
    }

    /**
     * Deixa de registar o inicio e fim do jogo
     */
    public void uncheckRegistarInicioFim() {
        this.registarInicioFim = false;
    }

    private boolean canRegistarInicioFim() {
        return isLigado && registarInicioFim;
    }

    private void registarUndo(JogoTresLinha jogo) {

        if (canRegistarUndo()) {
            adicionarRegisto("Jogo [" + jogo.getNome() + "] ID[" + jogo.getId() + "]: Undo efetuado.");
        }

    }

    /**
     * Se está a registar a jogadas retrocedidas
     *
     * @return true se está a registar a jogadas retrocedidas, false senão
     */
    public boolean isRegistarUndoChecked() {
        return registarUndo;
    }

    /**
     * Passa a registar as jogadas retrocedidas
     */
    public void checkRegistarUndo() {
        this.registarUndo = true;
    }

    /**
     * Deixa de registar as jogadas retrocedidas
     */
    public void uncheckRegistarUndo() {
        this.registarUndo = false;
    }

    private boolean canRegistarUndo() {
        return isLigado && registarUndo;
    }

    @Override
    public void adicionarRegisto(String registo) {
        if (isLigado) {
            baseLogger.adicionarRegisto(registo);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof JogoTresLinha) {

            JogoTresLinha jogo = (JogoTresLinha) o;

            if (arg instanceof EventoJogo) {

                EventoJogo evento = (EventoJogo) arg;

                switch (evento.getEvento()) {

                    case JOGO_INICIADO:
                        registarInicio(jogo);
                        break;
                    case JOGO_TERMINADO:
                        registarFim(jogo);
                        break;
                    case JOGADA_EXECUTADA:
                        registarJogada(jogo, evento.getDescricao());
                        break;
                    case JOGADA_RETROCEDIDA:
                        registarUndo(jogo);
                        break;

                }

            }

        }

    }

}
