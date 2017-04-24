/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

import pt.ips.pa.bonus.treslinha.interfaces.TemaPecaCharacterParser;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;

/**
 *
 * @author Morai
 */
public class WarPecaCharacterParser implements TemaPecaCharacterParser {

    @Override
    public char parseChar(PecaTresLinha peca) {

        switch (peca.getNome()) {
            case "Peca1":
                return 'E';
            case "Peca2":
                return 'I';
            case "Peca3":
                return 'P';
            case "Peca4":
                return 'S';
            case "Peca5":
                return 'N';
            case "Peca caveira":
                return 'X';
            default:
                return '_';

        }

    }

}
