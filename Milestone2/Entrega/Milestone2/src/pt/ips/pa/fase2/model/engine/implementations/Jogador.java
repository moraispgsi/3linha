/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.fase2.model.engine.implementations;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um jogador (imutavel). Jogadores com nicks iguais são considerados
 * iguais.
 *
 * @author Ricardo José Horta Morais
 */
public class Jogador implements Serializable {

    private String nick;

    /**
     * Construtor
     *
     * @param nick nick do jogador
     */
    public Jogador(String nick) {

        this.nick = nick;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nick);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogador other = (Jogador) obj;
        return Objects.equals(this.nick, other.nick);
    }

    /**
     * Devolve o nick do jogador
     *
     * @return nick do jogador
     */
    public String getNick() {
        return nick;
    }

}
