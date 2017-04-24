package treslinha.view.gestor.menu.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Ricardo José Horta Morais
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btnHistorico;

    @FXML
    private Button btnNovoJogo;

    @FXML
    private Button btnOpcoes;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnEstatisticas;

    @FXML
    private Button btnRanking;

    @FXML
    private Button btnAbout;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    /**
     * Devolve o botão de novo jogo
     *
     * @return botão de novo jogo
     */
    public Button getBtnNovoJogo() {
        return btnNovoJogo;
    }

    /**
     * Devolve o botão de opcoes
     *
     * @return botão de opcoes
     */
    public Button getBtnOpcoes() {
        return btnOpcoes;
    }

    /**
     * Devolve o botão de sair
     *
     * @return botão de sair
     */
    public Button getBtnSair() {
        return btnSair;
    }

    /**
     * Devolve o botão de estatisticas
     *
     * @return botão de estatisticas
     */
    public Button getBtnEstatisticas() {
        return btnEstatisticas;
    }

    /**
     * Devolve o botão de ranking
     *
     * @return botão de ranking
     */
    public Button getBtnRanking() {
        return btnRanking;
    }

    /**
     * Devolve o botão de historico
     *
     * @return botão de historico
     */
    public Button getBtnHistorico() {
        return btnHistorico;
    }

    /**
     * Devolve o botão de about
     *
     * @return botão de about
     */
    public Button getBtnAbout() {
        return btnAbout;
    }
    
    

}
