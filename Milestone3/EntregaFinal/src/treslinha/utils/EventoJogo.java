package treslinha.utils;

/**
 * Representa um evento de um jogo
 *
 * @author Ricardo José Horta Morais
 */
public class EventoJogo {

    /**
     * Representa o tipo de evento
     */
    public enum Tipo {

        /**
         * Jogo foi iniciado
         */
        JOGO_INICIADO,
        /**
         * Jogo terminou
         */
        JOGO_TERMINADO,
        /**
         * Jogada executada
         */
        JOGADA_EXECUTADA,
        /**
         * Jogada retrocedida
         */
        JOGADA_RETROCEDIDA

    }

    private Tipo evento;
    private String descricao;

    /**
     * Construtor
     *
     * @param evento tipo de evento
     * @param descricao descrição do evento
     */
    public EventoJogo(Tipo evento, String descricao) {
        this.evento = evento;
        this.descricao = descricao;
    }

    /**
     * Devolve o tipo de evento
     *
     * @return tipo de evento
     */
    public Tipo getEvento() {
        return evento;
    }

    /**
     * Devolve a descricao do evento
     *
     * @return descricao do evento
     */
    public String getDescricao() {
        return descricao;
    }

}
