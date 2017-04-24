/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.view.gestor.menu.opcoes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import treslinha.loggers.LoggerGestor;
import treslinha.loggers.LoggerJogo;
import treslinha.view.gestor.main.Gestor;

/**
 * FXML Controller class
 *
 * @author Morai
 */
public class OpcoesController implements Initializable {

    @FXML
    private CheckBox loggerJogoLigadoCheck;

    @FXML
    private Button btnVoltar;

    @FXML
    private CheckBox loggerGestorJogosCriadosCheck;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private CheckBox loggerGestorLigadoCheck;

    @FXML
    private CheckBox loggerJogoRegistarInicioFimCheck;

    @FXML
    private CheckBox loggerJogoRegistarUndosCheck;

    @FXML
    private CheckBox loggerGestorRegistroJogadoresCheck;

    @FXML
    private CheckBox loggerJogoRegistarJogadasCheck;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Setter para o gestor da aplicação
     *
     * @param gestor gestor da aplicação
     */
    public void setGestor(Gestor gestor) {

        LoggerGestor loggerGestor = gestor.getGestor().getLogger();
        LoggerJogo loggerJogo = gestor.getGestor().getLoggerJogos();

        loggerGestorLigadoCheck.setSelected(loggerGestor.isLigado());
        loggerGestorRegistroJogadoresCheck.setSelected(loggerGestor.isRegistarJogadorCriadoChecked());
        loggerGestorJogosCriadosCheck.setSelected(loggerGestor.isRegistarJogoCriadoChecked());

        if (!loggerGestor.isLigado()) {
            loggerGestorRegistroJogadoresCheck.setDisable(true);
            loggerGestorJogosCriadosCheck.setDisable(true);
        }

        loggerJogoLigadoCheck.setSelected(loggerJogo.isLigado());
        loggerJogoRegistarInicioFimCheck.setSelected(loggerJogo.isRegistarInicioFimChecked());
        loggerJogoRegistarJogadasCheck.setSelected(loggerJogo.isRegistarJogadasChecked());
        loggerJogoRegistarUndosCheck.setSelected(loggerJogo.isRegistarUndoChecked());

        if (!loggerJogo.isLigado()) {
            loggerJogoRegistarInicioFimCheck.setDisable(true);
            loggerJogoRegistarJogadasCheck.setDisable(true);
            loggerJogoRegistarUndosCheck.setDisable(true);
        }

        loggerGestorLigadoCheck.selectedProperty().addListener((o, antigo, novo) -> {

            if (novo) {
                loggerGestorRegistroJogadoresCheck.setDisable(false);
                loggerGestorJogosCriadosCheck.setDisable(false);
                loggerGestor.ligar();
            } else {
                loggerGestorRegistroJogadoresCheck.setDisable(true);
                loggerGestorJogosCriadosCheck.setDisable(true);
                loggerGestor.desligar();
            }

        });

        loggerGestorRegistroJogadoresCheck.selectedProperty().addListener((o, antigo, novo) -> {
            if (novo) {
                loggerGestor.checkRegistarJogadorCriado();
            } else {
                loggerGestor.uncheckRegistarJogadorCriado();
            }
        });

        loggerGestorJogosCriadosCheck.selectedProperty().addListener((o, antigo, novo) -> {
            if (novo) {
                loggerGestor.checkRegistarJogoCriado();
            } else {
                loggerGestor.uncheckRegistarJogoCriado();
            }
        });

        loggerJogoLigadoCheck.selectedProperty().addListener((o, antigo, novo) -> {
            if (novo) {
                loggerJogoRegistarInicioFimCheck.setDisable(false);
                loggerJogoRegistarJogadasCheck.setDisable(false);
                loggerJogoRegistarUndosCheck.setDisable(false);
                loggerJogo.ligar();
            } else {
                loggerJogoRegistarInicioFimCheck.setDisable(true);
                loggerJogoRegistarJogadasCheck.setDisable(true);
                loggerJogoRegistarUndosCheck.setDisable(true);
                loggerJogo.desligar();
            }
        });

        loggerJogoRegistarInicioFimCheck.selectedProperty().addListener((o, antigo, novo) -> {
            if (novo) {
                loggerJogo.checkRegistarInicioFim();
            } else {
                loggerJogo.uncheckRegistarInicioFim();
            }
        });

        loggerJogoRegistarJogadasCheck.selectedProperty().addListener((o, antigo, novo) -> {
            if (novo) {
                loggerJogo.checkRegistarJogadas();
            } else {
                loggerJogo.uncheckRegistarJogadas();
            }
        });

        loggerJogoRegistarUndosCheck.selectedProperty().addListener((o, antigo, novo) -> {
            if (novo) {
                loggerJogo.checkRegistarUndo();
            } else {
                loggerJogo.uncheckRegistarUndo();
            }
        });

    }

    /**
     * Devolve o botão de voltar
     *
     * @return botão de voltar
     */
    public Button getBtnVoltar() {
        return btnVoltar;
    }

}
