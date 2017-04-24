/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Morai
 */
public class MenuEscolhaMultipla {

    Scanner scanner = new Scanner(System.in);

    private List<String> opcoes;
    private int indice;

    public MenuEscolhaMultipla(String... opcoes) {
        indice = 0;
        if (opcoes.length <= 1) {
            throw new RuntimeException("Deve existir mais do que uma escolha");
        }
        this.opcoes = Arrays.asList(opcoes);
    }

    public String iniciar() {
        char c = ' ';
        do {
            for (int i = 0; i < opcoes.size(); i++) {

                if (i == indice) {
                    System.out.print(">");
                } else {
                    System.out.print(" ");
                }

                System.out.println(opcoes.get(i));

            }

            System.out.println("\n W para cima | S para baixo | D para escolher");

            String input = scanner.nextLine();
            if (input.length() == 0) {
                continue;
            }

            c = Character.toUpperCase(input.charAt(0));

            if (c == 'W' && indice > 0) {
                indice--;
            } else if (c == 'S' && indice < opcoes.size() - 1) {
                indice++;
            }

        } while (c != 'D');

        return opcoes.get(indice);

    }

}
