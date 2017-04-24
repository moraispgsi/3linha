/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treslinha.view.gestor.menu.estatisticas;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import treslinha.model.implementations.GestorDeJogosTresLinha;
import treslinha.model.implementations.TipoJogo;
import treslinha.utils.TipoPontuacao;

/**
 * FXML Controller class
 *
 * @author Morai
 */
public class EstatisticasController implements Initializable {

    private enum TipoEstatistica {

        TOP10_NUMERO_DE_JOGOS,
        TOP10_TEMPO_MEDIO_JOGO,
        TOP10_PONTUACAO_MAX,
        TIPO_JOGOS_JOGADOS,
        TIPO_PONTUACAO_JOGOS_JOGADOS;

        @Override
        public String toString() {

            switch (this) {
                case TIPO_JOGOS_JOGADOS:
                    return "Distribuição tipo de jogos jogados";
                case TIPO_PONTUACAO_JOGOS_JOGADOS:
                    return "Distribuição tipo de pontuação de jogos jogados";
                case TOP10_TEMPO_MEDIO_JOGO:
                    return "Top10: Tempo médio de jogo";
                case TOP10_NUMERO_DE_JOGOS:
                    return "Top10: Número de jogos";
                case TOP10_PONTUACAO_MAX:
                    return "Top10: Pontuação máxima";
                default:
                    return "";

            }

        }
    }

    private enum TipoGrafico {

        AREA,
        BAR,
        LINE,
        SCATTER;

        @Override
        public String toString() {
            switch (this) {
                case AREA:
                    return "AreaChart";
                case BAR:
                    return "BarChart";
                case LINE:
                    return "LineChart";
                case SCATTER:
                    return "ScatterChart";

                default:
                    return "";

            }
        }
    }
    
    @FXML
    private GridPane tiposGridPane;
    
    @FXML
    private AnchorPane graficoAnchorPane;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private Label tipoPontuacaoLabel;

    @FXML
    private Label tipoGraficoLabel;

    @FXML
    private ComboBox<TipoPontuacao> tipoPontuacaoComboBox;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private ComboBox<TipoGrafico> tipoDeGraficoComboBox;

    @FXML
    private ComboBox<TipoEstatistica> estatisticaComboBox;

    private Node grafico;
    private GestorDeJogosTresLinha gestor;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        estatisticaComboBox.getItems().addAll(
                TipoEstatistica.TIPO_JOGOS_JOGADOS,
                TipoEstatistica.TIPO_PONTUACAO_JOGOS_JOGADOS,
                TipoEstatistica.TOP10_TEMPO_MEDIO_JOGO,
                TipoEstatistica.TOP10_NUMERO_DE_JOGOS,
                TipoEstatistica.TOP10_PONTUACAO_MAX
        );

        tipoDeGraficoComboBox.getItems().addAll(
                TipoGrafico.AREA,
                TipoGrafico.BAR,
                TipoGrafico.LINE,
                TipoGrafico.SCATTER
        );

        tipoPontuacaoComboBox.getItems().addAll(
                TipoPontuacao.BASE,
                TipoPontuacao.CORRIDA
        );

        estatisticaComboBox.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {

            if (oldValue != TipoEstatistica.TIPO_JOGOS_JOGADOS
                    && oldValue != TipoEstatistica.TIPO_PONTUACAO_JOGOS_JOGADOS
                    && (newValue == TipoEstatistica.TIPO_JOGOS_JOGADOS
                    || newValue == TipoEstatistica.TIPO_PONTUACAO_JOGOS_JOGADOS)) {
                tiposGridPane.setDisable(true);
            }

            if ((oldValue == TipoEstatistica.TIPO_JOGOS_JOGADOS
                    || oldValue == TipoEstatistica.TIPO_PONTUACAO_JOGOS_JOGADOS)
                    && newValue != TipoEstatistica.TIPO_JOGOS_JOGADOS
                    && newValue != TipoEstatistica.TIPO_PONTUACAO_JOGOS_JOGADOS) {
                tiposGridPane.setDisable(false);
            }

            recriarGrafico();

        });
        tipoDeGraficoComboBox.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
            recriarGrafico();
        });
        tipoPontuacaoComboBox.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
            recriarGrafico();
        });

        estatisticaComboBox.getSelectionModel().selectFirst();
        tipoDeGraficoComboBox.getSelectionModel().selectFirst();
        tipoPontuacaoComboBox.getSelectionModel().selectFirst();

    }

    private XYChart criarXYChart(String titulo, String xLabel, String yLabel, XYChart.Series serie, TipoGrafico tipo) {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        XYChart chart;

        switch (tipo) {
            case AREA:
                chart = new AreaChart(xAxis, yAxis);
                break;
            case BAR:
                chart = new BarChart(xAxis, yAxis);
                break;
            case LINE:
                chart = new LineChart(xAxis, yAxis);
                break;
            case SCATTER:
                chart = new ScatterChart(xAxis, yAxis);
                break;

            default:
                chart = new AreaChart(xAxis, yAxis);
        }

        chart.setTitle(titulo);
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
        chart.getData().addAll(serie);
        return chart;
    }

    private Node getPieChartComPercentagem(String titulo, ObservableList<PieChart.Data> dados) {

        final PieChart chart = new PieChart(dados);
        chart.setTitle(titulo);

        final Label caption2 = new Label();
        caption2.setTextFill(Color.WHITE);
        caption2.setStyle("-fx-font: 12 arial;");

        final DoubleProperty xProperty2 = new SimpleDoubleProperty();
        final DoubleProperty yProperty2 = new SimpleDoubleProperty();

        for (final PieChart.Data data : chart.getData()) {

            final Runnable atualizarDados = () -> {
                double total = chart.getData()
                        .stream()
                        .mapToDouble((dataAux) -> {
                            return dataAux.getPieValue();
                        })
                        .sum();

                caption2.setText(String.format("%.2f", data.getPieValue() / total * 100) + "%");
            };

            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {

                atualizarDados.run();
            });

            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> {

                atualizarDados.run();
                xProperty2.set(e.getX());
                yProperty2.set(e.getY());

            });

            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {

                //caption2.setText("");
            });
        }

        caption2.translateXProperty().bind(xProperty2);
        caption2.translateYProperty().bind(yProperty2);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(chart, caption2);

        return stackPane;
    }

    private void recriarGrafico() {

        if (gestor == null) {
            return;
        }

        TipoEstatistica tipoEstatistica = estatisticaComboBox.getSelectionModel().getSelectedItem();
        TipoGrafico tipoGrafico = tipoDeGraficoComboBox.getSelectionModel().getSelectedItem();
        TipoPontuacao tipoPontuacao = tipoPontuacaoComboBox.getSelectionModel().getSelectedItem();

        XYChart.Series series1;

        switch (tipoEstatistica) {
            case TOP10_TEMPO_MEDIO_JOGO:

                series1 = new XYChart.Series();
                series1.setName("Tempo médio de jogo");

                Map<String, Double> mapTempoMedio = gestor.getMediaTempoPorJogo10Melhores(tipoPontuacao);

                mapTempoMedio.keySet().forEach((nick) -> {

                    series1.getData().add(new XYChart.Data(nick, mapTempoMedio.get(nick)));

                });

                setGrafico(criarXYChart(
                        "Top10: Tempo médio de jogo",
                        "Jogador",
                        "Segundos",
                        series1,
                        tipoGrafico
                ));

                break;
            case TOP10_NUMERO_DE_JOGOS:

                series1 = new XYChart.Series();
                series1.setName("Número jogos");

                Map<String, Integer> mapNumeroJogos = gestor.getNumeroJogos10MelhoresJogadores(tipoPontuacao);

                mapNumeroJogos.keySet().forEach((nick) -> {

                    series1.getData().add(new XYChart.Data(nick, mapNumeroJogos.get(nick)));

                });

                setGrafico(criarXYChart("Top10: Número jogos",
                        "Jogador",
                        "Número de jogos",
                        series1,
                        tipoGrafico
                ));

                break;

            case TOP10_PONTUACAO_MAX:

                series1 = new XYChart.Series();
                series1.setName("Pontuação máxima");

                Map<String, Integer> mapPontuacaoMaxima = gestor.getPontuacaoMaxima10Melhores(tipoPontuacao);

                mapPontuacaoMaxima.keySet().forEach((nick) -> {

                    series1.getData().add(new XYChart.Data(nick, mapPontuacaoMaxima.get(nick)));

                });

                setGrafico(criarXYChart(
                        "Top10: Pontuação máxima",
                        "Jogador",
                        "Pontuação",
                        series1,
                        tipoGrafico
                ));

                break;

            case TIPO_JOGOS_JOGADOS:

                long jogosNormais = gestor.getNumeroJogosConcluidos(TipoJogo.NORMAL, TipoPontuacao.BASE);
                jogosNormais += gestor.getNumeroJogosConcluidos(TipoJogo.NORMAL, TipoPontuacao.CORRIDA);

                long jogosRapidos = gestor.getNumeroJogosConcluidos(TipoJogo.RAPIDO, TipoPontuacao.BASE);
                jogosRapidos += gestor.getNumeroJogosConcluidos(TipoJogo.RAPIDO, TipoPontuacao.CORRIDA);

                ObservableList<PieChart.Data> pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Jogos Normais", jogosNormais),
                                new PieChart.Data("Jogos Rápidos", jogosRapidos));

                setGrafico(getPieChartComPercentagem("Tipos de jogo", pieChartData));

                break;

            case TIPO_PONTUACAO_JOGOS_JOGADOS:

                long jogosPontuacaoBase = gestor.getNumeroJogosConcluidos(TipoJogo.NORMAL, TipoPontuacao.BASE);
                jogosPontuacaoBase += gestor.getNumeroJogosConcluidos(TipoJogo.RAPIDO, TipoPontuacao.BASE);

                long jogosPontuacaoCorrida = gestor.getNumeroJogosConcluidos(TipoJogo.NORMAL, TipoPontuacao.CORRIDA);
                jogosPontuacaoCorrida += gestor.getNumeroJogosConcluidos(TipoJogo.RAPIDO, TipoPontuacao.CORRIDA);

                ObservableList<PieChart.Data> pieChartData2
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Jogos Pontuação Base", jogosPontuacaoBase),
                                new PieChart.Data("Jogos Pontuação Corrida", jogosPontuacaoCorrida));

                setGrafico(getPieChartComPercentagem("Tipos de pontuação de jogo", pieChartData2));

                break;
        }
    }

    private void setGrafico(Node node) {

        graficoAnchorPane.getChildren().clear();
        grafico = node;
        graficoAnchorPane.getChildren().add(grafico);
        AnchorPane.setTopAnchor(grafico, 0.0);
        AnchorPane.setBottomAnchor(grafico, 0.0);
        AnchorPane.setLeftAnchor(grafico, 0.0);
        AnchorPane.setRightAnchor(grafico, 0.0);

    }
    
    /**
     * Setter do model gestor
     * @param gestor model gestor
     */
    public void setGestor(GestorDeJogosTresLinha gestor) {
        this.gestor = gestor;
        recriarGrafico();

    }
    
    /**
     * Devolve o botão de voltar
     * @return botão de voltar
     */
    public Button getBtnVoltar() {
        return btnVoltar;
    }

}
