/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

import pt.ips.pa.bonus.treslinha.interfaces.TemaPecaCharacterParser;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.bonus.treslinha.consola.implementations.Frame;
import pt.ips.pa.fase2.model.engine.exceptions.PontoDeRestauroInexistenteException;
import pt.ips.pa.bonus.treslinha.consola.interfaces.Cartucho;
import pt.ips.pa.fase2.model.treslinha.implementations.JogoTresLinha;
import pt.ips.pa.fase2.model.treslinha.implementations.TabuleiroTresLinha;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;
import pt.ips.pa.fase1.tads.interfaces.LinhaTres;

/**
 * Representa um cartucho do jogo três linha, pode ser inserido numa consola
 *
 * @author Ricardo José Horta Morais
 */
public class CartuchoJogarJogo implements Cartucho, Observer {

    private JogoTresLinha jogo;
    private Frame frame = new Frame("");
    private TemaPecaCharacterParser temaPecaParser;

    /**
     * Construtor
     *
     * @param temaPecaParser parser das peças para letras
     * @param jogo jogo tres linha a instalar no cartucho
     */
    public CartuchoJogarJogo(TemaPecaCharacterParser temaPecaParser, JogoTresLinha jogo) {
        this.jogo = jogo;
        this.temaPecaParser = temaPecaParser;
        gerarFrame();
        jogo.addObserver((Observer) this);
    }

    @Override
    public boolean isTerminado() {
        return jogo.isJogoTerminado();
    }

    @Override
    public String inputLetra(Character caracter) {

        if (isTerminado()) {
            return "";
        }

        switch (Character.toLowerCase(caracter)) {

            case 'q':
                jogo.terminarJogo();
                break;
            case 's':
                jogo.descerDeLinha();
                break;
            case 'w':
                jogo.subirDeLinha();
                break;
            case 'd':
                jogo.drop();
                break;
            case 'u':

                try {
                    jogo.undo();
                    return "Jogo retrocedido.\n";
                } catch (PontoDeRestauroInexistenteException ex) {

                    return "";

                }

        }

        return "";

    }

    private void gerarFrame() {

        TabuleiroTresLinha tabuleiro = jogo.getTabuleiro();
        LinhaTres<PecaTresLinha>[] linhas = tabuleiro.getLinhas();

        PecaTresLinha dropEsquerda = tabuleiro.getDropEsquerda();
        PecaTresLinha dropDireita = tabuleiro.getDropDireita();

        String resultado = "";

        resultado += "Ronda " + jogo.getNumeroRonda() + "\n";
        resultado += "Pontuacao " + jogo.getPontuacao() + "\n\n";

        for (int i = 0; i < tabuleiro.getNumeroLinhas(); i++) {

            boolean isSelecionada = (i == tabuleiro.getLinhaSelecionada());

            if (isSelecionada) {
                resultado += " " + temaPecaParser.parseChar(dropEsquerda) + " ";
            } else {
                resultado += "   ";
            }

            for (int j = 0; j < tabuleiro.getCapacidadeDasLinhas() / 2 - linhas[i].sizeEsquerdo(); j++) {
                resultado += isSelecionada ? "*" : "#";
            }

            String aux = "";
            Iterator<PecaTresLinha> it = linhas[i].getIteradorEsquerdo();
            while (it.hasNext()) {

                aux = temaPecaParser.parseChar(it.next()) + aux;//Inverter
            }
            resultado += aux;

            it = linhas[i].getIteradorDireito();
            while (it.hasNext()) {

                resultado += temaPecaParser.parseChar(it.next());

            }

            for (int j = 0; j < tabuleiro.getCapacidadeDasLinhas() / 2 - linhas[i].sizeDireito(); j++) {
                resultado += isSelecionada ? "*" : "#";
            }

            if (isSelecionada) {
                resultado += " " + temaPecaParser.parseChar(dropDireita) + " ";
            }
            //resultado += linhas[i].sizeEsquerdo() + "|" + linhas[i].sizeDireito();
            resultado += "\n";
        }

        frame.setFrame(resultado);

    }

    @Override
    public Frame getFrame() {

        return frame;

    }

    @Override
    public void update(Observable o, Object arg) {

        gerarFrame();

    }

    @Override
    public void desconetar() {
        jogo.terminarJogo();
    }

}
