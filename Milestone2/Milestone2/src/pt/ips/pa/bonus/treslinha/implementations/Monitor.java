/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.bonus.treslinha.consola.implementations.Consola;
import pt.ips.pa.bonus.treslinha.consola.implementations.Frame;

/**
 * Representa um monitor que recebe as frames geradas por uma consola e
 * apresenta-as no System.out
 *
 * @author Ricardo José Horta Morais
 */
public class Monitor implements Observer {

    private Consola consola;

    /**
     * Construtor
     *
     * @param consola consola a ser observada
     */
    public Monitor(Consola consola) {

        this.consola = consola;

    }

    /**
     * Liga o monitor
     */
    public void ligar() {

        System.out.println(consola.getFrame().getText());
        consola.getFrame().addObserver(this);

    }

    /**
     * Desliga o monitor
     */
    public void desligar() {

        consola.getFrame().deleteObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof Frame) {
            Frame frame = (Frame) o;
            System.out.println(frame.getText());
        }

    }

}
