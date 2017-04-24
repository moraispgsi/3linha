package treslinha.view.implementations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import tads.interfaces.LinhaTres;
import treslinha.model.Events.EventoTabuleiroDrop;
import treslinha.model.Events.EventoTabuleiroDropsGeradas;
import treslinha.model.Events.EventoTabuleiroPecasDestruidas;
import treslinha.model.Events.EventoTabuleiroRestaurado;
import treslinha.model.implementations.TabuleiroTresLinha;
import treslinha.model.interfaces.PecaTresLinha;

/**
 *
 * @author Morai
 */
public class TabuleiroView extends AnchorPane implements Observer {

    private Pane pecaLayer = new Pane();
    private Pane uiLayer = new Pane();

    private EventHandler<? super TabuleiroViewEvento> dropEventHandler = null;

    private final int DROP_FADE_MILISEGUNDOS = 400;
    private final int DROPPING_MILISEGUNDOS = 150;
    private final double MULTIPLICADOR_MARGEM = 0.02;

    private TabuleiroTresLinha model;

    private final DoubleProperty imageWidth = new SimpleDoubleProperty();
    private final DoubleProperty imageHeight = new SimpleDoubleProperty();
    private final DoubleProperty marginHorizontal = new SimpleDoubleProperty();

    private final DoubleProperty posicaoDrop = new SimpleDoubleProperty();
    private final DoubleProperty posicaoCentroDrop = new SimpleDoubleProperty();

    private Line aimLine;

    private ImageView dropEsquerda;
    private ImageView dropDireita;

    private Map<ImageView, PecaTresLinha> mapImageViewParaPeca;
    private Map<LinhaTres, Set<ImageView>> mapLinhaParaImageViews;
    private Map<LinhaTres, Integer> mapLinhaParaIndice;

    /**
     * Construtor
     * @param model model do tabuleiroTresLinha
     */
    public TabuleiroView(TabuleiroTresLinha model) {

        this.getChildren().add(pecaLayer);
        AnchorPane.setTopAnchor(pecaLayer, 0.0);
        AnchorPane.setBottomAnchor(pecaLayer, 0.0);
        AnchorPane.setLeftAnchor(pecaLayer, 0.0);
        AnchorPane.setRightAnchor(pecaLayer, 0.0);

        this.getChildren().add(uiLayer);
        AnchorPane.setTopAnchor(uiLayer, 0.0);
        AnchorPane.setBottomAnchor(uiLayer, 0.0);
        AnchorPane.setLeftAnchor(uiLayer, 0.0);
        AnchorPane.setRightAnchor(uiLayer, 0.0);

        uiLayer.setOnMouseDragged((e) -> {

            double halfImageHeight = (imageHeight.get()) / 2;
            boolean isInUpperBound = e.getY() > imageHeight.get() / 2;
            boolean isInBottomBound = this.heightProperty().get() - halfImageHeight > e.getY();

            if (isInUpperBound && isInBottomBound) {
                posicaoDrop.set(e.getY() - halfImageHeight);
            }

        });

        uiLayer.setOnMouseMoved((e) -> {

            double halfImageHeight = (imageHeight.get()) / 2;
            boolean isInUpperBound = e.getY() > imageHeight.get() / 2;
            boolean isInBottomBound = this.heightProperty().get() - halfImageHeight > e.getY();

            if (isInUpperBound && isInBottomBound) {
                posicaoDrop.set(e.getY() - halfImageHeight);
            }

        });

        uiLayer.setOnMouseClicked((e) -> {

            if (pecaLayer.heightProperty().get() < model.getNumeroLinhas()) {
                //inconclusivo
                return;
            }

            int indice = (int) posicaoCentroDrop.get() / (int) imageHeight.get();

            if (dropEventHandler != null) {
                TabuleiroViewEvento evento = new TabuleiroViewEvento(TabuleiroViewEvento.DROP, indice);
                dropEventHandler.handle(evento);
            }

        });

        mapImageViewParaPeca = new HashMap();
        posicaoCentroDrop.bind(posicaoDrop.add(pecaLayer.heightProperty().divide(model.getNumeroLinhas()).divide(2)));
        marginHorizontal.bind(this.widthProperty().multiply(Math.min(1, Math.abs(MULTIPLICADOR_MARGEM))));

        mapLinhaParaImageViews = new HashMap();
        mapLinhaParaIndice = new HashMap();

        setModel(model);

    }

    private ImageView criarImageViewPeca() {

        ImageView imageView = new ImageView();

        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(imageWidth);
        imageView.fitHeightProperty().bind(this.heightProperty().divide(model.getNumeroLinhas()));
        imageView.setSmooth(true);

        return imageView;

    }
    
    /**
     * Setter do handler do evento de drop
     * @param handler handler do evento de drop
     */
    public void setOnDrop(EventHandler<? super TabuleiroViewEvento> handler) {

        dropEventHandler = handler;

    }

    private void fadeOut(Node node, Duration duracao, Runnable runOnDelete) {

        FadeTransition fadeOutAndDelete = new FadeTransition(duracao, node);
        fadeOutAndDelete.setFromValue(1);
        fadeOutAndDelete.setToValue(0);
        fadeOutAndDelete.setOnFinished((e) -> {

            if (runOnDelete != null) {
                runOnDelete.run();
            }
        });

        fadeOutAndDelete.play();

    }

    private void fadeIn(Node node, Duration duracao, Runnable runOnEnd) {

        FadeTransition fadeIn = new FadeTransition(duracao, node);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setOnFinished((e) -> {

            if (runOnEnd != null) {
                runOnEnd.run();
            }

        });

        fadeIn.play();

    }

    private void setModel(TabuleiroTresLinha model) {

        if (this.model != null) {

            mapLinhaParaImageViews = new HashMap();
            mapLinhaParaIndice = new HashMap();
            this.model.deleteObserver(this);
        }

        this.model = model;

        imageHeight.bind(this.heightProperty().divide(model.getNumeroLinhas()));

        model.addObserver(this);
        //aP + 2m = width <=> P = (width - 2m) / a 
        imageWidth.bind(pecaLayer.widthProperty().subtract(marginHorizontal.multiply(2)).divide(model.getCapacidadeDasLinhas() + 2));

        pecaLayer.getChildren().clear();
        mapImageViewParaPeca.clear();

        aimLine = new Line();
        aimLine.startXProperty().bind(imageWidth);
        aimLine.endXProperty().bind(pecaLayer.widthProperty().subtract(imageWidth));
        aimLine.setStrokeWidth(1);
        aimLine.setStroke(Color.LIGHTSTEELBLUE);
        aimLine.startYProperty().bind(posicaoCentroDrop);
        aimLine.endYProperty().bind(posicaoCentroDrop);
        aimLine.getStrokeDashArray().addAll(2d, 21d);
        aimLine.setId("aimLine");

        pecaLayer.getChildren().add(aimLine);

        LinhaTres linhas[] = model.getLinhas();

        refazerDrops();

        for (int i = 0; i < linhas.length; i++) {

            mapLinhaParaImageViews.put(linhas[i], new HashSet());
            mapLinhaParaIndice.put(linhas[i], i);

            refazerLinha(linhas[i]);
        }

    }

    private void refazerDrops() {

        DropShadow ds = new DropShadow();
        ds.setRadius(4);
        ds.setColor(Color.LIGHTGOLDENRODYELLOW);

        dropEsquerda = criarImageViewPeca();
        dropEsquerda.setEffect(ds);
        dropEsquerda.setId(model.getDropEsquerda().getNome());
        dropEsquerda.setLayoutX(0);
        dropEsquerda.layoutYProperty().bind(posicaoDrop);
        mapImageViewParaPeca.put(dropEsquerda, model.getDropEsquerda());

        dropDireita = criarImageViewPeca();
        dropDireita.setEffect(ds);
        dropDireita.setId(model.getDropDireita().getNome());
        dropDireita.layoutXProperty().bind(widthProperty().subtract(dropDireita.fitWidthProperty()));
        dropDireita.layoutYProperty().bind(posicaoDrop);
        mapImageViewParaPeca.put(dropDireita, model.getDropDireita());

        pecaLayer.getChildren().addAll(dropEsquerda, dropDireita);

        FadeTransition fadeDropEsquerda = new FadeTransition(Duration.millis(DROP_FADE_MILISEGUNDOS), dropEsquerda);
        fadeDropEsquerda.setFromValue(0.0);
        fadeDropEsquerda.setToValue(1);
        fadeDropEsquerda.play();

        FadeTransition fadeDropDireita = new FadeTransition(Duration.millis(DROP_FADE_MILISEGUNDOS), dropDireita);
        fadeDropDireita.setFromValue(0.0);
        fadeDropDireita.setToValue(1);
        fadeDropDireita.play();

    }

    private void refazerLinha(LinhaTres linha) {

        Iterator<PecaTresLinha> it = linha.getIteradorDireito();

        mapLinhaParaImageViews.get(linha).stream().forEach((imageView) -> {

            pecaLayer.getChildren().remove(imageView);
            mapImageViewParaPeca.remove(imageView);

        });

        mapLinhaParaImageViews.get(linha).clear();

        for (int j = 0; it.hasNext(); j++) {
            PecaTresLinha peca = it.next();
            //Image pecaImagem = parser.parsePecaToImage(peca);
            ImageView imageView = criarImageViewPeca();//pecaImagem);
            imageView.setId(peca.getNome());
            imageView.layoutXProperty().bind(widthProperty().divide(2).add(imageWidth.multiply(j)));
            imageView.layoutYProperty().bind(imageHeight.multiply(mapLinhaParaIndice.get(linha)));

            pecaLayer.getChildren().add(imageView);
            mapLinhaParaImageViews.get(linha).add(imageView);
            mapImageViewParaPeca.put(imageView, peca);

        }

        it = linha.getIteradorEsquerdo();

        for (int j = 0; it.hasNext(); j++) {
            PecaTresLinha peca = it.next();

            ImageView imageView = criarImageViewPeca();//pecaImagem);
            imageView.setId(peca.getNome());
            imageView.layoutXProperty().bind(widthProperty().divide(2).subtract(imageWidth.multiply(j + 1)));
            imageView.layoutYProperty().bind(imageHeight.multiply(mapLinhaParaIndice.get(linha)));

            pecaLayer.getChildren().add(imageView);
            mapLinhaParaImageViews.get(linha).add(imageView);
            mapImageViewParaPeca.put(imageView, peca);
        }

    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof TabuleiroTresLinha) {

            TabuleiroTresLinha tabuleiro = (TabuleiroTresLinha) o;

            if (arg instanceof EventoTabuleiroDrop) {
                EventoTabuleiroDrop evento = (EventoTabuleiroDrop) arg;

                ImageView dropEsquerdaAntiga = dropEsquerda;
                ImageView dropDireitaAntiga = dropDireita;
                dropEsquerdaAntiga.layoutYProperty().unbind();
                dropDireitaAntiga.layoutYProperty().unbind();

                int sizeEsquerdo = evento.getLinha().sizeEsquerdo();
                TranslateTransition animacaoDropEsquerda = new TranslateTransition(Duration.millis(DROPPING_MILISEGUNDOS), dropEsquerdaAntiga);
                animacaoDropEsquerda.setToX(-dropEsquerdaAntiga.getLayoutX() + marginHorizontal.get() + ((evento.getLinha().getCapacidadeLado()) - sizeEsquerdo + 1) * imageWidth.get());
                animacaoDropEsquerda.setToY(-dropEsquerdaAntiga.getLayoutY() + imageHeight.get() * evento.getIndiceLinha());
                animacaoDropEsquerda.setOnFinished((e) -> {

                    pecaLayer.getChildren().removeAll(dropEsquerdaAntiga, dropDireitaAntiga);
                    refazerLinha(evento.getLinha());

                });

                int sizeDireito = evento.getLinha().sizeDireito();
                TranslateTransition animacaoDropDireita = new TranslateTransition(Duration.millis(DROPPING_MILISEGUNDOS), dropDireitaAntiga);
                animacaoDropDireita.setToX(-dropDireitaAntiga.getLayoutX() + getWidth() / 2 + (sizeDireito - 1) * imageWidth.get());
                animacaoDropDireita.setToY(-dropDireitaAntiga.getLayoutY() + imageHeight.get() * evento.getIndiceLinha());

                animacaoDropEsquerda.play();
                animacaoDropDireita.play();

            } else if (arg instanceof EventoTabuleiroDropsGeradas) {

                refazerDrops();
            } else if (arg instanceof EventoTabuleiroPecasDestruidas) {

                EventoTabuleiroPecasDestruidas evento = (EventoTabuleiroPecasDestruidas) arg;

            } else if (arg instanceof EventoTabuleiroRestaurado) {

                setModel(model);

            }

        }

    }

}
