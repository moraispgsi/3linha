/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.view.parsers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morai
 */
public class ParserFactory {

    /**
     * Devolve o caminho do ficheiro de css a partir do tema
     *
     * @param tema tema
     * @return caminho do ficheiro de css a partir do tema
     */
    public static String getCssPath(String tema) {

        switch (tema.toLowerCase()) {

            case "halloween":
                return "/treslinha/view/parsers/TemaHalloween.css";
            case "smile":
                return "/treslinha/view/parsers/TemaSmile.css";
            case "war":
                return "/treslinha/view/parsers/TemaWar.css";
            case "gems":
                return "/treslinha/view/parsers/TemaGems.css";

        }

        throw new RuntimeException("Tema não existe.");

    }

    /**
     * Devolve uma lista de temas
     *
     * @return lista de temas
     */
    public static List<String> getTemas() {

        List<String> lista = new ArrayList();
        lista.add("Gems");
        lista.add("Smile");
        lista.add("Halloween");
        lista.add("War");

        return lista;

    }

}
