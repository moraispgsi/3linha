package treslinha.model.implementations;

import treslinha.loggers.LoggerJogo;
import treslinha.loggers.LoggerGestor;
import treslinha.utils.EventoJogo;
import treslinha.utils.TipoPontuacao;
import treslinha.utils.RegistoHistorico;
import treslinha.utils.RegistoRanking;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import engine.implementations.EstrategiaPontuacaoBase;
import engine.implementations.EstrategiaPontuacaoCorrida;
import engine.interfaces.EstrategiaPontuacao;
import tads.exceptions.CapacidadeNegativaException;
import tads.exceptions.LinhaTresCapacidadeImparException;
import tads.implementations.RankingDinamico;
import tads.interfaces.Comparador;
import tads.interfaces.Ranking;
import engine.implementations.Jogador;
import tads.implementations.HistoricoDinamico;
import tads.interfaces.Historico;
import utils.implementations.UtilsSerializacao;

/**
 *
 * Representa um gestor de jogos tres linha. Esta classe guarda informação sobre
 * todos os jogos tres linha criados apartir do gestor. A class também está
 * preparada para gerir multiplos jogos em simultaneo.
 *
 *
 * @author Ricardo José Horta Morais
 */
public class GestorDeJogosTresLinha implements Observer, Serializable {

    static final long serialVersionUID = -526570903711758783L;

    private final int capacidadeHistorico = 20;
    private final int numeroLinhas = 8;
    private final int numeroCapacidadeLinha = 10;
    private final AtomicInteger geradorIdJogos = new AtomicInteger();

    private final LoggerJogo loggerJogos;
    private final LoggerGestor logger;

    private Comparador comparador;
    private Ranking<RegistoRanking> rankingBase;
    private Ranking<RegistoRanking> rankingCorrida;
    private List<Jogador> jogadores;
    private List<JogoTresLinha> jogosConcluidos;
    private List<Historico<RegistoHistorico>> historicosJogadores;

    private final EstrategiaPontuacao estrategiaBase = new EstrategiaPontuacaoBase();
    private final EstrategiaPontuacao estrategiaCorrida = new EstrategiaPontuacaoCorrida();

    private Map<JogoTresLinha, Ranking<RegistoRanking>> mapJogoParaRanking;
    private Map<JogoTresLinha, Jogador> mapJogoParaJogador;
    private Map<Jogador, List<JogoTresLinha>> mapJogadorParaJogo;
    private Map<TipoPontuacao, List<JogoTresLinha>> mapTipoPontuacaoParaJogo;
    private Map<TipoPontuacao, Ranking<RegistoRanking>> mapTipoPontucaoParaRanking;
    private Map<TipoPontuacao, EstrategiaPontuacao> mapTipoPontucaoParaEstrategia;
    private Map<Jogador, Historico<RegistoHistorico>> mapJogadorParaHistorico;
    private Map<TipoJogo, List<JogoTresLinha>> mapTipoJogoParaJogo;

    /**
     * Construtor
     *
     * @param diretorio diretorio onde serão guardados os ficheiros de log
     */
    public GestorDeJogosTresLinha(String diretorio) {

        loggerJogos = new LoggerJogo(diretorio, "jogos", true, true, true, true);
        logger = new LoggerGestor(diretorio, true, true, true);

        comparador = ComparadorRankingPorPontuacao.getComparador();
        rankingBase = new RankingDinamico<>(comparador);
        rankingCorrida = new RankingDinamico<>(comparador);

        mapTipoPontuacaoParaJogo = new HashMap();
        mapTipoPontuacaoParaJogo.put(TipoPontuacao.BASE, new LinkedList());
        mapTipoPontuacaoParaJogo.put(TipoPontuacao.CORRIDA, new LinkedList());

        mapTipoPontucaoParaRanking = new HashMap();
        mapTipoPontucaoParaRanking.put(TipoPontuacao.BASE, rankingBase);
        mapTipoPontucaoParaRanking.put(TipoPontuacao.CORRIDA, rankingCorrida);

        mapTipoPontucaoParaEstrategia = new HashMap();
        mapTipoPontucaoParaEstrategia.put(TipoPontuacao.BASE, estrategiaBase);
        mapTipoPontucaoParaEstrategia.put(TipoPontuacao.CORRIDA, estrategiaCorrida);

        mapJogadorParaHistorico = new HashMap();
        mapJogoParaRanking = new HashMap();
        mapJogoParaJogador = new HashMap();
        mapJogadorParaJogo = new HashMap();

        mapTipoJogoParaJogo = new HashMap();
        mapTipoJogoParaJogo.put(TipoJogo.NORMAL, new LinkedList());
        mapTipoJogoParaJogo.put(TipoJogo.RAPIDO, new LinkedList());

        jogosConcluidos = new LinkedList();
        historicosJogadores = new LinkedList();
        jogadores = new LinkedList();

    }

    /**
     * Cria um jogo tres linha com supervisão do gestor
     *
     * @param nick nick do jogador, será criado um jogador novo caso não exista
     * ainda
     * @param tipoPontuacao tipo de calculo de pontuação
     * @param tipoJogo tipo de jogo
     * @return um jogo tres linha iniciado
     */
    public JogoTresLinha criarJogo(String nick, TipoPontuacao tipoPontuacao, TipoJogo tipoJogo) {

        try {

            EstrategiaPontuacao estrategiaPontuacao = mapTipoPontucaoParaEstrategia.get(tipoPontuacao);
            Ranking<RegistoRanking> ranking = mapTipoPontucaoParaRanking.get(tipoPontuacao);

            Jogador jogador = registarJogador(nick);

            JogoTresLinha jogo = new JogoTresLinha(
                    geradorIdJogos.incrementAndGet(),
                    numeroLinhas, numeroCapacidadeLinha,
                    tipoJogo,
                    estrategiaPontuacao,
                    new ConjuntoAleatorioPecasNormais(),
                    new ConjuntoAleatorioPecasEspeciais()
            );

            logger.registarJogoCriado(jogo, jogo.getTipo().toString(), jogador.getNick());

            mapTipoPontuacaoParaJogo.get(tipoPontuacao).add(jogo);
            mapTipoJogoParaJogo.get(tipoJogo).add(jogo);
            mapJogoParaRanking.put(jogo, ranking);
            mapJogoParaJogador.put(jogo, jogador);
            mapJogadorParaJogo.get(jogador).add(jogo);

            jogo.addObserver(this);
            jogo.addObserver(loggerJogos);

            return jogo;

        } catch (CapacidadeNegativaException | LinhaTresCapacidadeImparException ex) {
            Logger.getLogger(GestorDeJogosTresLinha.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema a criar jogo.");
        }

    }

    private Jogador registarJogador(String nick) {

        if (!GestorDeJogosTresLinha.isNickJogadorValido(nick)) {
            throw new RuntimeException("Nick do jogador é inválido");
        }

        Jogador jogador = new Jogador(nick.trim());

        if (!jogadores.contains(jogador)) {

            mapJogadorParaJogo.put(jogador, new LinkedList());
            jogadores.add(jogador);
            logger.registarJogadorCriado(jogador.getNick());

            Historico<RegistoHistorico> historico = new HistoricoDinamico(capacidadeHistorico);

            historicosJogadores.add(historico);
            mapJogadorParaHistorico.put(jogador, historico);

            return jogador;

        } else {

            int i = jogadores.indexOf(jogador);
            return jogadores.get(i);

        }

    }

    /**
     * Verifica se o nick do jogador é valido
     *
     * @param nick nome do jogador
     * @return true se o nick do jogador é valido, false senão
     */
    public static boolean isNickJogadorValido(String nick) {
        nick = nick.trim();
        return nick.length() > 3 && nick.matches("^[a-zA-z ]*$");

    }

    /**
     * Getter para o número de linhas que cada jogo gerado pelo gestor terá
     *
     * @return número de linhas que cada jogo gerado pelo gestor terá
     */
    public int getNumeroLinhas() {
        return numeroLinhas;
    }

    /**
     * Getter para a capacidade das linhas que cada jogo gerado pelo gestor terá
     *
     * @return capacidade das linhas que cada jogo gerado pelo gestor terá
     */
    public int getNumeroCapacidadeLinha() {
        return numeroCapacidadeLinha;
    }

    /**
     * Getter para a copia do ranking base
     *
     * @return copia do ranking base
     */
    public Ranking<RegistoRanking> getRankingBase() {
        return UtilsSerializacao.copiaSerializada(rankingBase);
    }

    /**
     * Getter para a copia do ranking corrida
     *
     * @return copia do ranking corrida
     */
    public Ranking<RegistoRanking> getRankingCorrida() {
        return UtilsSerializacao.copiaSerializada(rankingCorrida);
    }

    /**
     * Getter para a copia da lista dos jogadores
     *
     * @return copia da lista dos jogadores
     */
    public List<Jogador> getJogadores() {
        return UtilsSerializacao.copiaSerializada(jogadores);
    }

    /**
     * Getter para a copia da lista dos jogos
     *
     * @return copia da lista dos jogos
     */
    public List<JogoTresLinha> getJogosConcluidos() {
        return UtilsSerializacao.copiaSerializada(jogosConcluidos);
    }

    /**
     * Devolve o número de jogos concluidos
     *
     * @param tipoJogo tipo de jogos a filtrar
     * @param tipoPontuacao tipo de pontuação a filtrar
     * @return número de jogos concluidos
     */
    public long getNumeroJogosConcluidos(TipoJogo tipoJogo, TipoPontuacao tipoPontuacao) {

        return mapTipoPontuacaoParaJogo
                .get(tipoPontuacao)
                .stream()
                .filter((jogo) -> {

                    return mapTipoJogoParaJogo.get(tipoJogo).contains(jogo);

                }).count();

    }

    /**
     * Devolve o número de jogos (todos os jogados) dos 10 melhores jogadores de
     * uma categoria de pontuação
     *
     * @param tipoPontuacao tipo de pontuação
     * @return mapa chave: nick do jogador e valor: número de jogos
     */
    public Map<String, Integer> getNumeroJogos10MelhoresJogadores(TipoPontuacao tipoPontuacao) {

        Map<String, Integer> resultado = new LinkedHashMap();

        mapTipoPontucaoParaRanking
                .get(tipoPontuacao)
                .stream()
                .limit(10)
                .forEachOrdered(registo -> {

                    Jogador jogador = registo.getJogador();

                    int numeroJogos = mapJogadorParaJogo.get(jogador).size();

                    resultado.put(jogador.getNick(), numeroJogos);

                });

        return resultado;

    }

    /**
     * Devolve o média de tempo de jogos (de uma categoria de pontuação) dos 10
     * melhores jogadores de uma categoria de pontuação
     *
     * @param tipoPontuacao tipo de pontuação
     * @return mapa chave: nick do jogador e valor: tempo médio
     */
    public Map<String, Double> getMediaTempoPorJogo10Melhores(TipoPontuacao tipoPontuacao) {

        Map<String, Double> resultado = new LinkedHashMap();

        mapTipoPontucaoParaRanking
                .get(tipoPontuacao)
                .stream()
                .limit(10)
                .forEachOrdered(registo -> {
                    Jogador jogador = registo.getJogador();

                    Double media = mapTipoPontuacaoParaJogo
                    .get(tipoPontuacao)
                    .stream()
                    .filter(jogo -> {

                        return mapJogoParaJogador.get(jogo).equals(jogador);

                    })
                    .mapToInt(jogo -> {

                        return jogo.getSegundosDeJogo();
                    })
                    .average()
                    .getAsDouble();

                    resultado.put(jogador.getNick(), media);
                });

        return resultado;

    }

    /**
     * Pontuacao máxima dos 10 melhores jogadores de uma categoria de pontuação
     *
     * @param tipoPontuacao tipo de pontuacao
     * @return mapa chave: nick do jogador e valor: pontuação
     */
    public Map<String, Integer> getPontuacaoMaxima10Melhores(TipoPontuacao tipoPontuacao) {

        Map<String, Integer> resultado = new LinkedHashMap();

        mapTipoPontucaoParaRanking
                .get(tipoPontuacao)
                .stream()
                .limit(10)
                .forEachOrdered(registo -> {

                    resultado.put(registo.getJogador().getNick(), registo.getPontuacao());

                });

        return resultado;

    }

    /**
     * Devolve o histórico de um jogador
     *
     * @param jogador jogador
     * @return histórico do jogador
     */
    public Historico<RegistoHistorico> getHistorico(Jogador jogador) {

        if (!mapJogadorParaHistorico.containsKey(jogador)) {
            throw new RuntimeException("Não existe jogador.");
        }

        return mapJogadorParaHistorico.get(jogador);

    }

    /**
     * Regista um jogador no ranking relativo a um jogo
     *
     * @param jogo jogo que terminou
     */
    protected synchronized void registarRanking(JogoTresLinha jogo) {

        jogo.getPontuacao();
        Ranking<RegistoRanking> ranking = mapJogoParaRanking.get(jogo);

        Jogador jogador = mapJogoParaJogador.get(jogo);
        RegistoRanking registo = new RegistoRanking(jogador, jogo.getPontuacao());

        if (ranking.hasElement(registo)) {

            int indice = ranking.indexOf(registo);

            if (comparador.comparar(ranking.get(indice), registo)) {

                ranking.remover(indice);
                ranking.adicionar(registo);

            }

        } else {
            ranking.adicionar(registo);
        }
    }

    private synchronized void registarHistorico(JogoTresLinha jogo) {

        Jogador jogador = mapJogoParaJogador.get(jogo);

        RegistoHistorico registo = new RegistoHistorico(
                jogo.getPontuacao(),
                jogo.getSegundosDeJogo(),
                jogo.getTipo().toString(),
                jogo.getNumeroDePecasDestruidas(),
                jogo.getNumeroRonda()
        );

        mapJogadorParaHistorico.get(jogador).add(registo);

    }

    /**
     * Devolve o logger dos jogos
     *
     * @return logger dos jogos
     */
    public LoggerJogo getLoggerJogos() {
        return loggerJogos;
    }

    /**
     * Devolve o logger do gestor
     *
     * @return logger do gestor
     */
    public LoggerGestor getLogger() {
        return logger;
    }

    /**
     * Trata de um jogo que terminou
     *
     * @param jogo jogo terminado
     */
    protected void handleFimJogo(JogoTresLinha jogo) {

        jogosConcluidos.add(jogo);
        registarRanking(jogo);
        registarHistorico(jogo);
        jogo.deleteObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof JogoTresLinha) {

            JogoTresLinha jogo = (JogoTresLinha) o;

            if (arg instanceof EventoJogo) {

                EventoJogo evento = (EventoJogo) arg;

                switch (evento.getEvento()) {

                    case JOGO_TERMINADO:
                        handleFimJogo(jogo);
                        break;

                }

            }

        }

    }

}
