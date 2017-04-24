/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.view.gestor.menu.novojogo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import treslinha.model.implementations.GestorDeJogosTresLinha;
import treslinha.model.implementations.JogoTresLinha;
import treslinha.model.implementations.TipoJogo;
import treslinha.utils.TipoPontuacao;
import treslinha.view.gestor.main.Gestor;

/**
 * FXML Controller class
 *
 * @author Morai
 */
public class NovoJogoController implements Initializable {

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField nickJogadorTextField;

    @FXML
    private Label labelErroNick;

    @FXML
    private ComboBox<TipoJogo> tipoComboBox;

    @FXML
    private ComboBox<TipoPontuacao> tipoPontuacaoComboBox;

    private Gestor gestor;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tipoComboBox.getItems().addAll(TipoJogo.NORMAL, TipoJogo.RAPIDO);
        tipoComboBox.getSelectionModel().selectFirst();

        tipoPontuacaoComboBox.getItems().addAll(TipoPontuacao.BASE, TipoPontuacao.CORRIDA);
        tipoPontuacaoComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Setter para o gestor da aplicação
     *
     * @param gestor gestor da aplicação
     */
    public void setGestorAplicacao(Gestor gestor) {
        this.gestor = gestor;
    }

    @FXML
    void handleIniciarJogo(ActionEvent event) {
        if (GestorDeJogosTresLinha.isNickJogadorValido(nickJogadorTextField.getText())) {

            if (gestor != null) {
                JogoTresLinha jogo = gestor.getGestor().criarJogo(nickJogadorTextField.getText(),
                        tipoPontuacaoComboBox.getSelectionModel().getSelectedItem(),
                        tipoComboBox.getSelectionModel().getSelectedItem());

                gestor.abrirJogoTresLinhaScene(jogo);

            }
        } else {

            labelErroNick.setText("Nick inválido.");

        }

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
