/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

/**
 *
 * @author Morai
 */
public class TecladoLetras {

    private final int MAX_HORIZONTAL = 4;
    private final int MAX_VERTICAL = 4;

    private char[][] letras = {
        {'a', 'b', 'c', 'd', 'e'},
        {'f', 'g', 'h', 'i', 'j'},
        {'k', 'l', 'm', 'n', 'o'},
        {'p', 'q', 'r', 's', 't'},
        {'u', 'v', 'x', 'y', 'z'}

    };

    private int indiceVertical;
    private int indiceHorizontal;

    public TecladoLetras() {
        indiceVertical = 0;
        indiceHorizontal = 0;
    }

    public void moverParaCima() {

        if (indiceVertical > 0) {
            indiceVertical--;
        }

    }

    public void moverParaBaixo() {

        if (indiceVertical < MAX_VERTICAL) {
            indiceVertical++;
        }

    }

    public void moverParaEsquerda() {

        if (indiceHorizontal > 0) {
            indiceHorizontal--;
        }

    }

    public void moverParaDireita() {

        if (indiceHorizontal < MAX_HORIZONTAL) {
            indiceHorizontal++;
        }

    }

    public char getLetraSelecionada() {

        return Character.toUpperCase(letras[indiceVertical][indiceHorizontal]);

    }

    @Override
    public String toString() {

        String resultado = "";

        for (int i = 0; i <= MAX_VERTICAL; i++) {

            for (int j = 0; j <= MAX_HORIZONTAL; j++) {

                if (i == indiceVertical && j == indiceHorizontal) {
                    resultado += ">" + Character.toUpperCase(letras[i][j]);
                } else {
                    resultado += " " + letras[i][j];
                }

                resultado += " ";
            }
            resultado += "\n";
        }

        return resultado;
    }

}
