package treslinha.view.gestor.menu.about;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class AboutController implements Initializable {

    @FXML
    Button btnVoltar;

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
     * Devolve o botão de voltar
     * @return botão de voltar
     */
    public Button getBtnVoltar() {
        return btnVoltar;
    }


}
