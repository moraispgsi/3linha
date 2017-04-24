/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.consola.implementations;

import java.io.Serializable;
import java.util.Observable;

/**
 *
 * @author Morai
 */
public class Frame extends Observable implements Serializable {

    private String conteudo;

    /**
     * Constructor
     *
     * @param conteudo conteúdo da frame
     */
    public Frame(String conteudo) {
        this.conteudo = conteudo;
        setChanged();
        notifyObservers();
    }

    /**
     * Devolve o texto da frame
     *
     * @return conteúdo da frame
     */
    public synchronized String getText() {
        return conteudo;
    }

    /**
     * Altera o conteúdo da frame
     *
     * @param conteudo conteúdo da frame
     */
    public synchronized void setFrame(String conteudo) {
        this.conteudo = conteudo;
        setChanged();
        notifyObservers();
    }

}
