/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.implementations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utils.interfaces.Logger;

/**
 * Representa um logger simples que guarda registos em modo append num ficheiro
 * à escolha
 *
 * @author Ricardo José Horta Morais
 */
public class LoggerSimples implements Logger {

    private static final int numeroTentativas = 20;

    private String filePath;

    /**
     * Construtor
     *
     * @param filePath path para o ficheiro onde serão guardados os registos
     */
    public LoggerSimples(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void adicionarRegisto(String registo) {
        for (int i = 0; i < numeroTentativas; i++) {

            try {
                LocalDateTime dataAtual = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                File file = new File(filePath);

                if (file.exists()) {
                    file.createNewFile();
                }

                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write("[" + dataAtual.format(formatter) + "]: " + registo);
                    fw.write(System.lineSeparator());
                }
                return;

            } catch (IOException ex) {

            }
        }
        throw new RuntimeException("Não foi possível criar registo.");

    }

}
