package pt.ips.pa.bonus.treslinha.implementations;

import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.bonus.treslinha.consola.implementations.Frame;
import pt.ips.pa.bonus.treslinha.consola.interfaces.Cartucho;
import pt.ips.pa.fase2.controller.treslinha.implementations.GestorDeJogosTresLinha;
import pt.ips.pa.fase3.view.treslinha.implementations.Tema;
import pt.ips.pa.fase2.controller.treslinha.implementations.TipoPontuacao;
import pt.ips.pa.fase2.model.treslinha.implementations.JogoTresLinha;
import pt.ips.pa.fase2.model.treslinha.implementations.TipoJogo;

/**
 *
 * @author Morai
 */
public class CartuchoEscolherJogador implements Cartucho, Observer {

    private boolean isTerminado = false;
    private Frame frame;
    private TecladoLetras teclado = new TecladoLetras();
    private GestorDeJogosTresLinha gestor;
    private String nome;

    private Cartucho cartuchoJogo;

    public CartuchoEscolherJogador(GestorDeJogosTresLinha gestor) {

        this.gestor = gestor;
        this.frame = new Frame("");
        nome = "";
        gerarConteudoFrame();

    }

    @Override
    public boolean isTerminado() {
        return isTerminado;
    }

    @Override
    public Frame getFrame() {
        return frame;
    }

    @Override
    public String inputLetra(Character caracter) {

        if (cartuchoJogo != null && !cartuchoJogo.isTerminado()) {

            String feedback = cartuchoJogo.inputLetra(caracter);
            if (cartuchoJogo.isTerminado()) {
                desconetar();
            }

            return feedback;
        }

        if (isTerminado) {
            return "";
        }

        switch (Character.toLowerCase(caracter)) {

            case 'q':
                desconetar();
                return "Jogo terminado";

            case 's':
                teclado.moverParaBaixo();
                break;
            case 'w':
                teclado.moverParaCima();
                break;
            case 'd':
                teclado.moverParaDireita();
                break;
            case 'a':
                teclado.moverParaEsquerda();
                break;
            case 'f':
                nome += teclado.getLetraSelecionada();
                break;
            case 't':
                return iniciarJogo();

        }

        gerarConteudoFrame();

        return "";

    }

    private String iniciarJogo() {

        JogoTresLinha jogo = gestor.criarJogo(nome, Tema.HALLOWEEN, TipoPontuacao.BASE, TipoJogo.NORMAL);
        cartuchoJogo = new CartuchoJogarJogo(new HalloweenPecaCharacterParser(), jogo);
        cartuchoJogo.getFrame().addObserver(this);
        frame.setFrame(cartuchoJogo.getFrame().getText());
        return "";

    }

    private void gerarConteudoFrame() {

        frame.setFrame("Insira o nome: \n"
                + teclado.toString()
                + "\n>" + nome
        );

    }

    @Override
    public void desconetar() {

        isTerminado = true;

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Frame) {
            Frame fr = (Frame) o;
            System.out.println(fr.getText());
        }
    }

}
