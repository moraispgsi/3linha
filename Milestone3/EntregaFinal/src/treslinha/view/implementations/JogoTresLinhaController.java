package treslinha.view.implementations;

import engine.exceptions.PontoDeRestauroInexistenteException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import treslinha.model.implementations.JogoTresLinha;
import static treslinha.model.implementations.TipoJogo.RAPIDO;
import treslinha.utils.EventoJogo;
import treslinha.view.parsers.ParserFactory;

/**
 * FXML Controller class
 *
 * @author Ricardo José Horta Morais
 */
public class JogoTresLinhaController implements Initializable, Observer {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane tabuleiroAnchorPane;

    @FXML
    private Label labelValorRonda;

    @FXML
    private Label labelRonda;

    @FXML
    private GridPane tabuleiroGridPane;

    @FXML
    private Label labelSegundos;

    @FXML
    private Label labelPontos;

    @FXML
    private Label labelValorPontos;

    @FXML
    private Label labelValorSegundos;

    @FXML
    private Button btnTerminar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnUndo;

    @FXML
    private ChoiceBox<String> temaChoiceBox;

    private JogoTresLinha model;
    private TabuleiroView tabuleiroView;

    private Runnable updatePontos;
    private Runnable updateRondas;

    private Timer timer = new Timer("Metronome", true);

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
     * Setter para o tabuleiroView
     *
     * @param tabuleiro tabuleiroView
     */
    public void setTabuleiro(TabuleiroView tabuleiro) {
        tabuleiroAnchorPane.getChildren().add(tabuleiro);
        AnchorPane.setBottomAnchor(tabuleiro, 0.0);
        AnchorPane.setTopAnchor(tabuleiro, 0.0);
        AnchorPane.setLeftAnchor(tabuleiro, 0.0);
        AnchorPane.setRightAnchor(tabuleiro, 0.0);
    }

    /**
     * Altera os pontos
     *
     * @param valor Altera os pontos
     */
    public void setValorPontos(int valor) {
        labelValorPontos.setText(String.valueOf(valor));
    }

    /**
     * Altera a ronda
     *
     * @param valor ronda
     */
    public void setValorRonda(int valor) {
        labelValorRonda.setText(String.valueOf(valor));
    }

    /**
     * Altera a ronda e o limite
     *
     * @param valor ronda
     * @param limite limite de rondas
     */
    public void setValorRonda(int valor, int limite) {
        labelValorRonda.setText(String.valueOf(valor) + "/" + String.valueOf(limite));
    }

    /**
     * Altera o valor dos segundos
     *
     * @param valor valor dos segundos
     */
    public void setValorSegundos(int valor) {
        labelValorSegundos.setText(String.valueOf(valor));
    }

    /**
     * Devolve o botão de terminar
     *
     * @return botão de terminar
     */
    public Button getBtnTerminar() {
        return btnTerminar;
    }

    /**
     * Devolve o botão de voltar
     *
     * @return botão de voltar
     */
    public Button getBtnVoltar() {
        return btnVoltar;
    }

    /**
     * Devolve o botão de undo
     *
     * @return botão de undo
     */
    public Button getBtnUndo() {
        return btnUndo;
    }

    /**
     * Altera o model do jogotreslinha
     *
     * @param model model do jogotreslinha
     */
    public void setModel(JogoTresLinha model) {

        if (this.model != null) {

            this.tabuleiroAnchorPane.getChildren().clear();
            this.model.deleteObserver(this);
            temaChoiceBox.getItems().clear();

        }

        this.model = model;

        temaChoiceBox.getItems().addAll(ParserFactory.getTemas());
        temaChoiceBox.getSelectionModel().selectedItemProperty().addListener((o, antigo, novo) -> {
            mainAnchorPane.getScene().getStylesheets().clear();
            mainAnchorPane.getScene().getStylesheets().add(ParserFactory.getCssPath(novo));

        });

        temaChoiceBox.getSelectionModel().selectFirst();

        updatePontos = () -> {
            setValorPontos(model.getPontuacao());
            setValorSegundos(model.getSegundosDeJogo());
        };

        updateRondas = () -> {

            if (model.getTipo() == RAPIDO) {
                setValorRonda(model.getNumeroRonda(), 20);
            } else {
                setValorRonda(model.getNumeroRonda());
            }

        };

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(updatePontos);

            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);

        tabuleiroView = new TabuleiroView(model.getTabuleiro());

        tabuleiroView.setOnDrop((e) -> {

            model.drop(e.getIndiceLinha());

        });

        setTabuleiro(tabuleiroView);
        setValorPontos(model.getPontuacao());
        btnUndo.setOnAction((e) -> {
            try {
                model.undo();
            } catch (PontoDeRestauroInexistenteException ex) {

            }
        });

        updateRondas.run();

        model.addObserver((Observer) this);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JogoTresLinha) {

            
            if (arg instanceof EventoJogo) {

                EventoJogo evento = (EventoJogo) arg;

                switch (evento.getEvento()) {

                    case JOGADA_RETROCEDIDA:
                    case JOGADA_EXECUTADA:
                        updateRondas.run();
                        updatePontos.run();
                        break;
                    case JOGO_TERMINADO:

                        //controller.getNumeroRonda().setText("Fim");
                        break;

                }

            }

        }
    }

}
