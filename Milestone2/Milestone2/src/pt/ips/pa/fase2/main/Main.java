package pt.ips.pa.fase2.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ips.pa.bonus.treslinha.consola.implementations.Consola;
import pt.ips.pa.bonus.treslinha.consola.interfaces.Cartucho;
import pt.ips.pa.bonus.treslinha.implementations.CartuchoJogarJogo;
import pt.ips.pa.bonus.treslinha.implementations.EstatisticaBillboard;
import pt.ips.pa.bonus.treslinha.implementations.Monitor;
import pt.ips.pa.bonus.treslinha.implementations.TemaFactory;
import pt.ips.pa.bonus.treslinha.interfaces.MenuEscolhaMultipla;
import pt.ips.pa.fase2.controller.treslinha.implementations.GestorDeJogosTresLinha;
import pt.ips.pa.fase2.controller.treslinha.implementations.TipoPontuacao;
import pt.ips.pa.fase2.model.treslinha.implementations.JogoTresLinha;
import pt.ips.pa.fase2.model.treslinha.implementations.TipoJogo;
import pt.ips.pa.fase2.utils.implementations.UtilsSerializacao;
import pt.ips.pa.fase3.view.treslinha.implementations.Tema;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class Main {

    /**
     * Main
     *
     * @param args argumentos
     */
    public static void main(String[] args) {

        try {
            String directorio = System.getProperty("user.home") + "/Desktop";

            Path path = Paths.get(directorio + "/Gestor.ser");

            GestorDeJogosTresLinha gestor;

            if (Files.exists(path)) {

                byte[] data = Files.readAllBytes(path);
                gestor = UtilsSerializacao.deserializar(GestorDeJogosTresLinha.class, data);

            } else {
                gestor = new GestorDeJogosTresLinha(directorio);
            }

            Scanner sc = new Scanner(System.in);

            System.out.print("Insira o nome do jogador:\n>");

            String nick = sc.nextLine();

            MenuEscolhaMultipla menuPontuacao = new MenuEscolhaMultipla("Pontuação Base", "Pontuação Corrida");
            System.out.println("\n Escolha o tipo de pontuação\n");
            String resultado = menuPontuacao.iniciar();

            TipoPontuacao tipoPontuacao = resultado.equals("Pontuação Base") ? TipoPontuacao.BASE : TipoPontuacao.CORRIDA;

            JogoTresLinha jogo = gestor.criarJogo(nick, Tema.WAR, tipoPontuacao, TipoJogo.RAPIDO);

            Cartucho catucho = new CartuchoJogarJogo(TemaFactory.getPecaCharacterParser("War"), jogo);

            Consola consola = new Consola(catucho, System.in, System.out, () -> {

                try {

                    File file = new File(directorio + "/Gestor.ser");
                    file.createNewFile();
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(UtilsSerializacao.serializar(gestor));
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                EstatisticaBillboard estatisticaBillboard = new EstatisticaBillboard(gestor);
                estatisticaBillboard.start();

            });

            Monitor monitor = new Monitor(consola);

            consola.start();
            monitor.ligar();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
