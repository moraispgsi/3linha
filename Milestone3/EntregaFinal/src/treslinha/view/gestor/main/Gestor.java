package treslinha.view.gestor.main;

import java.io.File;
import java.io.FileOutputStream;
import treslinha.view.gestor.menu.main.MenuPrincipalController;
import treslinha.view.gestor.menu.novojogo.NovoJogoController;
import treslinha.view.gestor.menu.estatisticas.EstatisticasController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import treslinha.model.implementations.GestorDeJogosTresLinha;
import treslinha.model.implementations.JogoTresLinha;
import treslinha.view.gestor.menu.about.AboutController;
import treslinha.view.gestor.menu.historico.HistoricoController;
import treslinha.view.gestor.menu.opcoes.OpcoesController;
import treslinha.view.gestor.menu.ranking.RankingController;
import treslinha.view.implementations.JogoTresLinhaController;
import utils.implementations.UtilsSerializacao;

/**
 * Representa o gestor do jogo no contexto de aplicação
 *
 * @author Ricardo José Horta Morais
 */
public final class Gestor extends Application {

    private final String directorio = System.getProperty("user.home") + "/Desktop";
    private final Path path = Paths.get(directorio + "/Gestor.ser");
    private Stage stage = new Stage();

    private GestorDeJogosTresLinha gestor;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("JogoTrêsLinha");
        stage = primaryStage;

        abrirGestor();
        abrirMainMenuScene();

        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

    }

    private void guardarGestor() {

        try {

            File file = new File(directorio + "/Gestor.ser");
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(UtilsSerializacao.serializar(gestor));
            }

        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível gravar.");
        }

    }

    /**
     * Abre o ficheiro do gestor
     */
    private void abrirGestor() {

        try {

            if (Files.exists(path)) {

                byte[] data = Files.readAllBytes(path);
                gestor = UtilsSerializacao.deserializar(GestorDeJogosTresLinha.class, data);

            } else {
                gestor = new GestorDeJogosTresLinha(directorio);
            }

            stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (e) -> {

                guardarGestor();

            });

        } catch (IOException ex) {
            throw new RuntimeException("Abrir o gestor.");
        }

    }

    /**
     * Abre o menu principal
     */
    public void abrirMainMenuScene() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/main/MenuPrincipal.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MenuPrincipalController controller = fxmlLoader.getController();

            controller.getBtnEstatisticas().setOnAction((e) -> {
                abrirEstatisticasScene();
            });

            controller.getBtnNovoJogo().setOnAction((e) -> {
                abrirNovoJogoScene();
            });

            controller.getBtnRanking().setOnAction((e) -> {
                abrirRankingScene();
            });

            controller.getBtnOpcoes().setOnAction((e) -> {
                abrirOpcoesScene();
            });

            controller.getBtnHistorico().setOnAction((e) -> {
                abrirHistoricoScene();
            });

            controller.getBtnAbout().setOnAction((e) -> {
                abrirAboutScene();
            });

            controller.getBtnSair().setOnAction((e) -> {
                guardarGestor();
                stage.close();
            });
            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }

    }

    /**
     * Abre o About
     */
    public void abrirAboutScene() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/about/About.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            AboutController controller = fxmlLoader.getController();
            controller.getBtnVoltar().setOnAction((e) -> {
                abrirMainMenuScene();
            });

            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }

    }

    /**
     * Abre as estatisticas
     */
    public void abrirEstatisticasScene() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/estatisticas/Estatisticas.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            EstatisticasController controller = fxmlLoader.getController();
            controller.getBtnVoltar().setOnAction((e) -> {
                abrirMainMenuScene();
            });
            controller.setGestor(gestor);
            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }

    }

    /**
     * Abre os rankings
     */
    public void abrirRankingScene() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/ranking/Ranking.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            RankingController controller = fxmlLoader.getController();
            controller.setGestor(this);
            controller.getBtnVoltar().setOnAction((e) -> {
                abrirMainMenuScene();
            });
            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }

    }

    /**
     * Abre as opçoes
     */
    public void abrirOpcoesScene() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/opcoes/Opcoes.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            OpcoesController controller = fxmlLoader.getController();
            controller.setGestor(this);
            controller.getBtnVoltar().setOnAction((e) -> {
                abrirMainMenuScene();
            });
            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }

    }

    /**
     * Abre o historico
     */
    private void abrirHistoricoScene() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/historico/Historico.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HistoricoController controller = fxmlLoader.getController();
            controller.setGestor(this);
            controller.getBtnVoltar().setOnAction((e) -> {
                abrirMainMenuScene();
            });
            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }
    }

    /**
     * Abre o novo jogo
     */
    public void abrirNovoJogoScene() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/gestor/menu/novojogo/NovoJogo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            NovoJogoController controller = fxmlLoader.getController();
            controller.getBtnVoltar().setOnAction((e) -> {
                abrirMainMenuScene();
            });
            controller.setGestorAplicacao(this);

            stage.setScene(scene);

        } catch (IOException ex) {
            throw new RuntimeException("Problema IO");
        }

    }

    /**
     * Abre o JogoTrêsLinha
     *
     * @param jogo Model do jogo
     */
    public void abrirJogoTresLinhaScene(JogoTresLinha jogo) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/treslinha/view/implementations/JogoTresLinha.fxml"));
        try {

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            stage.setScene(scene);

            EventHandler handler = (e) -> {
                jogo.terminarJogo();
            };
            stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);

            JogoTresLinhaController controller = fxmlLoader.getController();
            controller.setModel(jogo);
            controller.getBtnTerminar().setOnAction((e) -> jogo.terminarJogo());
            controller.getBtnVoltar().setOnAction((e) -> {
                jogo.terminarJogo();
                stage.removeEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);
                abrirMainMenuScene();

            });

        } catch (IOException ex) {
            throw new RuntimeException("Problema a abrir o FXML.");
        }

    }

    /**
     * Devolve o model do gestor de jogotreslinha
     *
     * @return model do gestor de jogotreslinha
     */
    public GestorDeJogosTresLinha getGestor() {
        return gestor;
    }

}
