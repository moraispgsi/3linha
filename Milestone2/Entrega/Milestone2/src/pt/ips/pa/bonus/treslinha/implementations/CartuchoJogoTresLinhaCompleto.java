/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.treslinha.implementations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ips.pa.bonus.treslinha.consola.implementations.Frame;
import pt.ips.pa.bonus.treslinha.consola.interfaces.Cartucho;
import pt.ips.pa.fase2.controller.treslinha.implementations.GestorDeJogosTresLinha;
import pt.ips.pa.fase2.main.Main;
import pt.ips.pa.fase2.utils.implementations.UtilsSerializacao;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class CartuchoJogoTresLinhaCompleto implements Cartucho, Observer {

    private static final String directorio = "C:/Users/Morai/Desktop";
    private boolean isTerminado = false;

    private Cartucho cartuchoAtual;
    private GestorDeJogosTresLinha gestor;
    private Frame frame;

    public CartuchoJogoTresLinhaCompleto() {

        try {
            Path path = Paths.get(directorio + "/Gestor.ser");

            if (Files.exists(path)) {

                byte[] data = Files.readAllBytes(path);
                gestor = UtilsSerializacao.deserializar(GestorDeJogosTresLinha.class, data);

            } else {
                gestor = new GestorDeJogosTresLinha(directorio);
            }

        } catch (IOException ex) {
            throw new RuntimeException("Problema grave");
        }

        cartuchoAtual = new CartuchoEscolherJogador(gestor);
        cartuchoAtual.getFrame().addObserver((Observer) this);
        frame = new Frame(cartuchoAtual.getFrame().getText());

    }

    @Override
    public boolean isTerminado() {
        return isTerminado;
    }

    @Override
    public Frame getFrame() {
        return frame;
    }

    @Override
    public String inputLetra(Character caracter) {

        if (isTerminado) {
            return "";
        }

        String feedback = cartuchoAtual.inputLetra(caracter);

        if (cartuchoAtual.isTerminado()) {
            desconetar();
        }

        return feedback;

    }

    @Override
    public void desconetar() {

        try {

            File file = new File(directorio + "/Gestor.ser");
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(UtilsSerializacao.serializar(gestor));
            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        isTerminado = true;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Frame) {
            Frame fr = (Frame) o;
            System.out.println(fr.getText());
        }
    }

}
