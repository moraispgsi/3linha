package treslinha.utils;

/**
 * Tipo de pontuação referente a um jogo
 *
 * @author Ricardo José Horta Morais
 */
public enum TipoPontuacao {

    /**
     * Pontuação base
     */
    BASE,
    /**
     * Pontuação corrida
     */
    CORRIDA;

    @Override
    public String toString() {

        switch (this) {

            case BASE:
                return "Base";
            case CORRIDA:
                return "Corrida";
            default:
                return "";

        }

    }

}
