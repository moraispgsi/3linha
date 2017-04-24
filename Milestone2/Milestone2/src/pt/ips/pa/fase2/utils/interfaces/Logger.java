/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.utils.interfaces;

import java.io.Serializable;

/**
 * Representa um logger que guarda registos em formato de texto
 *
 * @author Ricardo José Horta Morais
 */
public interface Logger extends Serializable {

    /**
     * Adiciona um registo no logger
     *
     * @param registo registo em formato de texto
     */
    public void adicionarRegisto(String registo);

}
