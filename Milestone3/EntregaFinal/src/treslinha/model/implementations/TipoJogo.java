/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.model.implementations;

/**
 * Representa um tipo de jogo
 *
 * @author Ricardo José Horta Morais
 */
public enum TipoJogo {

    /**
     * Jogo normal
     */
    NORMAL,
    /**
     * Jogo rápido
     */
    RAPIDO;

    @Override
    public String toString() {

        switch (this) {
            case NORMAL:
                return "Normal";
            case RAPIDO:
                return "Rapido";
            default:
                return "";
        }

    }

}
