/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.view.gestor.menu.ranking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tads.interfaces.Ranking;

import treslinha.utils.RegistoRanking;
import treslinha.utils.TipoPontuacao;
import treslinha.view.gestor.main.Gestor;

/**
 * FXML Controller class
 *
 * @author Ricardo José Horta Morais
 */
public class RankingController implements Initializable {

    @FXML
    private TableColumn<RegistoRanking, String> rankingJogadorColumn;

    @FXML
    private TableColumn<RegistoRanking, Number> rankingPontosColumn;

    @FXML
    private TableView<RegistoRanking> rankingTableView;

    @FXML
    private Button btnVoltar;

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

        rankingPontosColumn.setCellValueFactory((cellData) -> {
            return new SimpleIntegerProperty(cellData.getValue().getPontuacao());
        });

        rankingJogadorColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(cellData.getValue().getJogador().getNick());
        });

    }

    private void atualizar() {

        rankingTableView.getItems().clear();

        Ranking<RegistoRanking> ranking = null;
        switch (tipoPontuacaoComboBox.getSelectionModel().getSelectedItem()) {
            case BASE:
                ranking = gestor.getGestor().getRankingBase();
                break;
            case CORRIDA:
                ranking = gestor.getGestor().getRankingCorrida();
                break;

        }

        if (ranking == null) {
            throw new RuntimeException("Ranking null");
        }
        ranking
                .stream()
                .forEach((registo) -> {

                    rankingTableView.getItems().addAll(registo);

                });

    }
    
    /**
     * Setter para o gestor da aplicação
     * @param gestor gestor da aplicação
     */
    public void setGestor(Gestor gestor) {

        this.gestor = gestor;
        tipoPontuacaoComboBox.getItems().addAll(TipoPontuacao.BASE, TipoPontuacao.CORRIDA);
        tipoPontuacaoComboBox.getSelectionModel().selectFirst();

        tipoPontuacaoComboBox.getSelectionModel().selectedItemProperty().addListener((o, antigo, novo) -> {

            atualizar();

        });

        atualizar();

    }
    
    /**
     * Devolve o botão de voltar
     * @return botão de voltar
     */
    public Button getBtnVoltar() {
        return btnVoltar;
    }

}
