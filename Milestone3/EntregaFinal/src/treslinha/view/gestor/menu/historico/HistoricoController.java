package treslinha.view.gestor.menu.historico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import engine.implementations.Jogador;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import tads.interfaces.Historico;
import treslinha.utils.RegistoHistorico;
import treslinha.view.gestor.main.Gestor;

/**
 * FXML Controller class
 *
 * @author Ricardo José Horta Morais
 */
public class HistoricoController implements Initializable {

    @FXML
    private GridPane mainGridPane;

    @FXML
    private ChoiceBox<String> nickChoiceBox;

    @FXML
    private TableView<RegistoHistorico> historicoTableView;

    @FXML
    private TableColumn<RegistoHistorico, Number> pontuacaoColumn;

    @FXML
    private TableColumn<RegistoHistorico, Number> rondasColumn;

    @FXML
    private TableColumn<RegistoHistorico, String> tipoJogoColumn;

    @FXML
    private TableColumn<RegistoHistorico, Number> pecasDestruidasColumn;

    @FXML
    private TableColumn<RegistoHistorico, Number> segundosColumn;

    @FXML
    private Button btnVoltar;

    private Gestor gestor;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pontuacaoColumn.setCellValueFactory((cellData) -> {
            return new SimpleIntegerProperty(cellData.getValue().getPontuacaoObtida());
        });
        
        rondasColumn.setCellValueFactory((cellData) -> {
            return new SimpleIntegerProperty(cellData.getValue().getNumeroRondasJogadas());
        });
        
        pecasDestruidasColumn.setCellValueFactory((cellData) -> {
            return new SimpleIntegerProperty(cellData.getValue().getNumeroPecasDestruidas());
        });
        
        segundosColumn.setCellValueFactory((cellData) -> {
            return new SimpleIntegerProperty(cellData.getValue().getSegundosDeJogo());
        });

        tipoJogoColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(cellData.getValue().getTipoDeJogo());
        });

    }
    
    /**
     * Setter para o gestor
     * @param gestor gestor da aplicação
     */
    public void setGestor(Gestor gestor) {

        this.gestor = gestor;
        
        List<String> nicks = gestor.getGestor()
                .getJogadores()
                .stream()
                .map((jogador) -> {
                    return jogador.getNick();
                }).collect(Collectors.toList());
        
        nickChoiceBox.getItems().addAll(nicks);
        
        nickChoiceBox.getSelectionModel().selectedItemProperty().addListener((o,antigo,novo)->{
            
            Historico<RegistoHistorico> historico = gestor.getGestor().getHistorico(new Jogador(novo));
            
            Iterator<RegistoHistorico> it = historico.iterator();
            
            historicoTableView.getItems().clear();
            
            while(it.hasNext()){
                
                historicoTableView.getItems().add(it.next());
                
            }
            
        });
        
        
    }
    
    /**
     * Devolve o botão de voltar
     * @return botão de voltar
     */
    public Button getBtnVoltar() {
        return btnVoltar;
    }

}
