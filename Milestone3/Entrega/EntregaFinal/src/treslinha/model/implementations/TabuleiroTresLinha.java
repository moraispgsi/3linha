package treslinha.model.implementations;

import treslinha.model.Events.EventoTabuleiroLinhaCheia;
import treslinha.model.Events.EventoTabuleiroPecasDestruidas;
import treslinha.model.Events.EventoTabuleiroDrop;
import treslinha.model.Events.EventoTabuleiroRestaurado;
import treslinha.model.Events.EventoTabuleiroDropsGeradas;
import engine.interfaces.Restauravel;
import java.io.Serializable;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tads.exceptions.CapacidadeNegativaException;
import tads.exceptions.LinhaCheiaException;
import tads.exceptions.LinhaTresCapacidadeImparException;
import tads.implementations.LinhaTresDinamica;
import treslinha.model.interfaces.PecaTresLinha;
import tads.interfaces.ConjuntoAleatorio;
import tads.interfaces.LinhaTres;

/**
 * Representa um tabuleiro três linhas
 *
 * @author Ricardo José Horta Morais
 */
public class TabuleiroTresLinha extends Observable implements Serializable, Restauravel {

    private final int PROBABILIDADE_PECA_ESPECIAL = 10;
    
    private static Random random = new Random();
    private final LinhaTres<PecaTresLinha>[] linhas;
    private final int numeroLinhas;
    private final int capacidadeDasLinhas;

    private ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasNormais;
    private ConjuntoAleatorio<PecaTresLinha> conjuntoDePecasEspeciais;
    
    private PecaTresLinha dropEsquerda;
    private PecaTresLinha dropDireita;

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
        
        dropEsquerda = conjuntoDePecasNormais.peek();
        dropDireita = conjuntoDePecasNormais.peek();

    }

    /**
     * Gera duas peças aleatóriamente com uma chance de uma delas ser uma peça
     * especial
     */
    public final void gerarNovasDrops() {
        
        PecaTresLinha dropEsquerdaAntiga = dropEsquerda;
        PecaTresLinha dropDireitaAntiga = dropDireita;
        
        int valor = random.nextInt(100);

        if (valor < PROBABILIDADE_PECA_ESPECIAL) {

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
        
        EventoTabuleiroDropsGeradas evento = new EventoTabuleiroDropsGeradas(dropEsquerdaAntiga, dropDireitaAntiga, dropEsquerda, dropDireita);
        setChanged();
        notifyObservers(evento);
        
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

    
        int pecasDestruidas = 0;

        linhas[indice].addFirst(dropEsquerda);
        linhas[indice].addLast(dropDireita);
        
        EventoTabuleiroDrop eventoDrop = new EventoTabuleiroDrop(dropEsquerda,dropDireita, indice, linhas[indice]);
        setChanged();
        notifyObservers(eventoDrop);
        
        pecasDestruidas += executarEsquerda(indice);
        pecasDestruidas += executarDireita(indice);

        gerarNovasDrops();

        if (isLadoCheio(indice)) {
            //TODO:Evento Linha cheia
            EventoTabuleiroLinhaCheia eventoLinhaCheia = new EventoTabuleiroLinhaCheia(indice, linhas[indice]);
            setChanged();
            notifyObservers(eventoLinhaCheia);
            throw new LinhaCheiaException();
        }
        
        if(pecasDestruidas > 0){
            
            EventoTabuleiroPecasDestruidas eventoPecasDestruidas = 
                    new EventoTabuleiroPecasDestruidas(pecasDestruidas,indice,linhas[indice]);
            setChanged();
            notifyObservers(eventoPecasDestruidas);
            
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
     * Devolve a cópia da peça de drop esquerda
     *
     * @return peça de drop esquerda
     */
    public PecaTresLinha getDropEsquerda() {
        return dropEsquerda;
    }

    /**
     * Devolve a cópia da peça de drop direita
     *
     * @return peça de drop direita
     */
    public PecaTresLinha getDropDireita() {
        return dropDireita;
    }

    /**
     * Devolve todas as linhas
     *
     * @return todas as linhas
     */
    public LinhaTres<PecaTresLinha>[] getLinhas() {
        return linhas;
    }

    @Override
    public void restaurarEstado(Restauravel restauravel) {
        
        if(restauravel instanceof TabuleiroTresLinha){
            
            TabuleiroTresLinha tabuleiro = (TabuleiroTresLinha) restauravel;
            
            if(this.capacidadeDasLinhas != tabuleiro.capacidadeDasLinhas ||
                    this.numeroLinhas != tabuleiro.numeroLinhas ||
                    this.linhas.length != tabuleiro.linhas.length)
                throw new RuntimeException("Atributos não correspondem.");
            
            System.arraycopy(tabuleiro.linhas, 0, this.linhas, 0, this.linhas.length);
            
            this.conjuntoDePecasNormais = tabuleiro.conjuntoDePecasNormais;
            this.conjuntoDePecasEspeciais = tabuleiro.conjuntoDePecasEspeciais;
            this.dropEsquerda = tabuleiro.dropEsquerda;
            this.dropDireita = tabuleiro.dropDireita;
            
            EventoTabuleiroRestaurado eventoRestaurado = new EventoTabuleiroRestaurado();
            setChanged();
            notifyObservers(eventoRestaurado);
            
        }
        
    }

}
