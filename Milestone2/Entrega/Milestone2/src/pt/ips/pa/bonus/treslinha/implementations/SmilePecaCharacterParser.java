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
public class SmilePecaCharacterParser implements TemaPecaCharacterParser {

    @Override
    public char parseChar(PecaTresLinha peca) {
        switch (peca.getNome()) {
            case "Peca1":
                return 'O';
            case "Peca2":
                return 'N';
            case "Peca3":
                return 'C';
            case "Peca4":
                return 'S';
            case "Peca5":
                return 'A';
            case "Peca caveira":
                return 'X';
            default:
                return '_';

        }
    }

}
