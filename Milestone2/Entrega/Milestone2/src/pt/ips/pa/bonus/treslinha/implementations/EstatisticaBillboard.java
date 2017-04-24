/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ips.pa.fase2.controller.treslinha.implementations.GestorDeJogosTresLinha;
import pt.ips.pa.fase2.controller.treslinha.implementations.TipoPontuacao;

/**
 * Representa um billboard que mostra a informação estatistica de um gestor de
 * jogos três linha O billboard pode correr em model thread e normal. Todo o
 * output é escrito no System.out
 *
 * @author Ricardo José Horta Morais
 */
public class EstatisticaBillboard extends Thread {

    private final int milisegundoDelay = 200;
    private GestorDeJogosTresLinha gestor;

    /**
     * Gestor que ao qual as estatisticas serão extraidas
     *
     * @param gestor gestor de jogos três linha
     */
    public EstatisticaBillboard(GestorDeJogosTresLinha gestor) {
        this.gestor = gestor;

    }

    @Override
    public void run() {
        System.out.println("Billboard de estatisticas:");
        Map<String, Double> mapNickTempoMedioBase = gestor.getMediaTempoPorJogo10Melhores(TipoPontuacao.BASE);
        sleep();
        System.out.println("Média tempo por jogo dos 10 melhores jogadores em pontuação base:\n");
        sleep();

        mapNickTempoMedioBase.keySet().stream().forEachOrdered((nick) -> {
            System.out.println(">" + nick + ": " + String.format("%.2f", mapNickTempoMedioBase.get(nick)) + " segundos");
            sleep();
        });

        for (int i = 0; i < 10 - mapNickTempoMedioBase.size(); i++) {
            System.out.println(">");
            sleep();
        }

        Map<String, Double> mapNickTempoMedioCorrida = gestor.getMediaTempoPorJogo10Melhores(TipoPontuacao.CORRIDA);

        System.out.println("\nMédia tempo por jogo dos 10 melhores jogadores em pontuação corrida:\n");
        sleep();
        mapNickTempoMedioCorrida.keySet().stream().forEachOrdered((nick) -> {
            System.out.println(">" + nick + ": " + String.format("%.2f", mapNickTempoMedioCorrida.get(nick)) + " segundos");
            sleep();
        });
        for (int i = 0; i < 10 - mapNickTempoMedioCorrida.size(); i++) {
            System.out.println(">");
            sleep();
        }

        Map<String, Integer> mapNickPontuacaoBase = gestor.getPontuacaoMaxima10Melhores(TipoPontuacao.BASE);

        System.out.println("\nPontuação base máxima dos 10 melhores:\n");
        sleep();
        mapNickPontuacaoBase.keySet().stream().forEachOrdered((nick) -> {
            System.out.println(">" + nick + ": " + mapNickPontuacaoBase.get(nick) + " pontos");
            sleep();
        });

        for (int i = 0; i < 10 - mapNickPontuacaoBase.size(); i++) {
            System.out.println(">");
            sleep();
        }

        Map<String, Integer> mapNickPontuacaoCorrida = gestor.getPontuacaoMaxima10Melhores(TipoPontuacao.CORRIDA);

        System.out.println("\nPontuação corrida máxima dos 10 melhores:\n");
        sleep();
        mapNickPontuacaoCorrida.keySet().stream().forEachOrdered((nick) -> {
            System.out.println(">" + nick + ": " + mapNickPontuacaoCorrida.get(nick) + " pontos");
            sleep();
        });

        for (int i = 0; i < 10 - mapNickPontuacaoCorrida.size(); i++) {
            System.out.println(">");
            sleep();
        }

    }

    private void sleep() {

        try {
            Thread.sleep(milisegundoDelay);
        } catch (InterruptedException ex) {
            Logger.getLogger(EstatisticaBillboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
