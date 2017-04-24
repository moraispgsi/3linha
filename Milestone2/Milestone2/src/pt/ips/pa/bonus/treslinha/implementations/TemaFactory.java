/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

import pt.ips.pa.bonus.treslinha.interfaces.TemaPecaCharacterParser;

/**
 *
 * @author Morai
 */
public class TemaFactory {

    public static TemaPecaCharacterParser getPecaCharacterParser(String tema) {

        switch (tema.toLowerCase()) {

            case "war":
                return new WarPecaCharacterParser();
            case "smile":
                return new SmilePecaCharacterParser();
            case "halloween":
            default:
                return new HalloweenPecaCharacterParser();

        }

    }

}
