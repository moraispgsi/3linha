package pt.ips.pa.fase2.model.treslinha.implementations;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ips.pa.fase2.model.treslinha.interfaces.PecaTresLinha;
import pt.ips.pa.fase1.tads.exceptions.CapacidadeNegativaException;
import pt.ips.pa.fase1.tads.exceptions.LinhaCheiaException;
import pt.ips.pa.fase1.tads.exceptions.LinhaTresCapacidadeImparException;
import pt.ips.pa.fase1.tads.implementations.LinhaTresDinamica;
import pt.ips.pa.fase1.tads.interfaces.ConjuntoAleatorio;
import pt.ips.pa.fase1.tads.interfaces.LinhaTres;
import pt.ips.pa.fase2.utils.implementations.UtilsSerializacao;

/**
 * Representa um tabuleiro três linhas
 *
 * @author Ricardo José Horta Morais
 */
public class TabuleiroTresLinha implements Serializable {

    private LinhaTres<PecaTresLinha>[] linhas;
    private int numeroLinhas;
    private int capacidadeDasLinhas;

    private ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasNormais;
    private ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasEspeciais;
    private PecaTresLinha dropEsquerda;
    private PecaTresLinha dropDireita;
    private int linhaSelecionada;

    private final int probabilidadePecaEspecial = 10;

    /**
     * Construtor
     *
     * @param numeroLinhas número de linhas do tabuleiro
     * @param capacidadeDasLinhas capacidade de cada linha
     * @param conjuntoDePecasNormais conjunto aleatório de peças normais
     * @param conjuntoDePecasEspeciais conjunto aleatório de peças especiais
     * @throws CapacidadeNegativaException ocorre quando a capacidade é negativa
     * @throws LinhaTresCapacidadeImparException ocorre quando a capacidade é um
     * número impar
     */
    public TabuleiroTresLinha(int numeroLinhas, int capacidadeDasLinhas, ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasNormais, ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasEspeciais) throws CapacidadeNegativaException, LinhaTresCapacidadeImparException {

        this.conjuntoDePecasNormais = conjuntoDePecasNormais;
        this.conjuntoDePecasEspeciais = conjuntoDePecasEspeciais;

        if (numeroLinhas < 1) {
            throw new RuntimeException("Deve haver pelo menos uma linha.");
        }

        if (capacidadeDasLinhas < 4) {
            throw new RuntimeException("A capacidade deve ser maior ou igual a 4.");
        }

        this.numeroLinhas = numeroLinhas;
        this.capacidadeDasLinhas = capacidadeDasLinhas;

        linhas = new LinhaTresDinamica[numeroLinhas];
        for (int i = 0; i < numeroLinhas; i++) {

            linhas[i] = new LinhaTresDinamica(capacidadeDasLinhas);
            try {
                linhas[i].addFirst(conjuntoDePecasNormais.peek());
                linhas[i].addLast(conjuntoDePecasNormais.peek());
            } catch (LinhaCheiaException ex) {
                Logger.getLogger(TabuleiroTresLinha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        linhaSelecionada = 0;

        gerarNovasPecas();

    }

    /**
     * Gera duas peças aleatóriamente com uma chance de uma delas ser uma peça
     * especial
     */
    public final void gerarNovasPecas() {
        Random random = new Random();

        int valor = random.nextInt(100);

        if (valor < probabilidadePecaEspecial) {

            if (random.nextBoolean()) {
                dropEsquerda = conjuntoDePecasEspeciais.peek();
                dropDireita = conjuntoDePecasNormais.peek();

            } else {

                dropEsquerda = conjuntoDePecasNormais.peek();
                dropDireita = conjuntoDePecasEspeciais.peek();
            }

        } else {
            dropEsquerda = conjuntoDePecasNormais.peek();
            dropDireita = conjuntoDePecasNormais.peek();
        }

    }

    /**
     * Deixa as duas peças na linha atualmente selecionada
     *
     * @return número de peças destruidas
     * @throws LinhaCheiaException ocorre quando a linha fica cheia de um lado
     */
    public int drop() throws LinhaCheiaException {
        return drop(linhaSelecionada);
    }

    /**
     * Deixa as duas peças na linha indicada no indice
     *
     * @param indice indice da linha
     * @return número de peças destruidas
     * @throws LinhaCheiaException ocorre quando a linha fica cheia de um lado
     */
    public final int drop(int indice) throws LinhaCheiaException {

        if (indice >= numeroLinhas || indice < 0) {
            throw new RuntimeException("A linha " + indice + "não existe.");
        }

        linhaSelecionada = indice;
        int pecasDestruidas = 0;

        linhas[indice].addFirst(dropEsquerda);
        linhas[indice].addLast(dropDireita);

        pecasDestruidas += executarEsquerda(indice);
        pecasDestruidas += executarDireita(indice);

        gerarNovasPecas();

        if (isLadoCheio(indice)) {
            throw new LinhaCheiaException();
        }

        return pecasDestruidas;

    }

    private boolean isLadoCheio(int i) {

        int sizeEsquerdo = linhas[i].sizeEsquerdo();
        int sizeDireito = linhas[i].sizeDireito();
        int capacidadeLado = linhas[i].getCapacidadeLado();
        return capacidadeLado == sizeEsquerdo || capacidadeLado == sizeDireito;

    }

    private int executarEsquerda(int i) {

        int sizeAntigo = linhas[i].size();
        dropEsquerda.executarEsquerda(linhas[i]);
        int sizeNovo = linhas[i].size();

        int destruidas = sizeAntigo - sizeNovo;
        destruidas = destruidas > 0 ? destruidas : 0;

        return destruidas;
    }

    private int executarDireita(int indice) {

        int sizeAntigo = linhas[indice].size();
        dropDireita.executarDireita(linhas[indice]);
        int sizeNovo = linhas[indice].size();

        int destruidas = sizeAntigo - sizeNovo;
        destruidas = destruidas > 0 ? destruidas : 0;

        return destruidas;

    }

    /**
     * Seleciona a linha abaixo da linha atualmente seleciona caso seja possível
     */
    public void descerDeLinha() {

        if (linhaSelecionada < numeroLinhas - 1) {
            linhaSelecionada++;
        }

    }

    /**
     * Seleciona a linha acima da linha atualmente seleciona caso seja possível
     */
    public void subirDeLinha() {

        if (linhaSelecionada > 0) {
            linhaSelecionada--;
        }

    }

    /**
     * Devolve o número de linhas
     *
     * @return número de linhas
     */
    public int getNumeroLinhas() {
        return numeroLinhas;
    }

    /**
     * Devolve a capacidade das linhas
     *
     * @return capacidade das linhas
     */
    public int getCapacidadeDasLinhas() {
        return capacidadeDasLinhas;
    }

    /**
     * Devolve o indice da linha selecionada
     *
     * @return indice da linha selecionada
     */
    public int getLinhaSelecionada() {
        return linhaSelecionada;
    }

    /**
     * Seleciona uma linha através do seu indice
     *
     * @param indice indice da linha a selecionar
     */
    public void selecionarLinha(int indice) {

        if (indice >= numeroLinhas || indice < 0) {
            throw new RuntimeException("Linha " + indice + " não existe.");
        }

        linhaSelecionada = indice;
    }

    /**
     * Devolve a cópia da peça de drop esquerda
     *
     * @return peça de drop esquerda
     */
    public PecaTresLinha getDropEsquerda() {
        return UtilsSerializacao.copiaSerializada(dropEsquerda);
    }

    /**
     * Devolve a cópia da peça de drop direita
     *
     * @return peça de drop direita
     */
    public PecaTresLinha getDropDireita() {
        return UtilsSerializacao.copiaSerializada(dropDireita);
    }

    /**
     * Devolve a cópia de todas as linhas
     *
     * @return cópia de todas as linhas
     */
    public LinhaTres<PecaTresLinha>[] getLinhas() {
        return UtilsSerializacao.copiaSerializada(linhas);
    }

}
