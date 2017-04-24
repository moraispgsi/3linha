/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.consola.implementations;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import pt.ips.pa.bonus.treslinha.consola.interfaces.Cartucho;

/**
 * Representa uma consola de cartuchos. Cartuchos diferentes podem ser inseridos
 * para mudar de jogo.
 *
 * @author Ricardo José Horta Morais
 */
public class Consola extends Thread implements Observer {

    private volatile Frame frame;

    private Cartucho cartucho;

    private Scanner scanner;
    private PrintStream printFeedbackStream;

    private Runnable onDesligar;

    /**
     * Construtor
     *
     * @param cartucho cartucho que ficará inserido na consola
     * @param inputStream stream on serão extraidos os inputs
     * @param feedbackStream stream on serão lançados os feedbacks
     * @param onDesligar runnable que irá executar ao ser desligada a consola
     */
    public Consola(Cartucho cartucho, InputStream inputStream, OutputStream feedbackStream, Runnable onDesligar) {

        frame = new Frame("");
        frame.setFrame(cartucho.getFrame().getText());
        this.cartucho = cartucho;
        this.onDesligar = onDesligar;

        this.scanner = new Scanner(inputStream, "Latin2");

        this.printFeedbackStream = new PrintStream(feedbackStream);

        cartucho.getFrame().addObserver((Observer) this);
    }

    /**
     * Altera o cartucho
     *
     * @param cartucho cartucho do jogo
     */
    public synchronized void setCartucho(Cartucho cartucho) {

        cartucho.desconetar();
        this.cartucho = cartucho;
        this.frame = cartucho.getFrame();

    }

    @Override
    public synchronized void run() {

        while (!cartucho.isTerminado()) {

            String input = scanner.nextLine();

            if (input.length() == 0) {
                continue;
            }

            printFeedbackStream.print(cartucho.inputLetra(input.charAt(0)));

        }

        printFeedbackStream.println("O jogo terminou.");
        onDesligar.run();
    }

    /**
     * Devolve a referência para a frame da consola
     *
     * @return referência da frame da consola
     */
    public Frame getFrame() {
        return frame;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof Frame) {
            Frame fr = (Frame) o;
            System.out.println(fr.getText());
        }

    }

}
