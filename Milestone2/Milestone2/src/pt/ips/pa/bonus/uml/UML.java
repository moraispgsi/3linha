/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.bonus.uml;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Morai
 */
public class UML {

    private Set<Class<?>> classes = new HashSet();

    public UML(Set<Class<?>> classes) {

        this.classes.addAll(classes);

    }

    public String gerarNumNumlStyle() {

        return "#arrowSize: 2\n"
                + "#fontSize: 12\n"
                + "#spacing: 40\n"
                + "#padding: 10\n"
                + "#edges: hard\n"
                + "#direction: down\n"
                + "#leading: 1.5\n"
                + "#lineWidth: 1\n"
                + "#bendSize: 0.3";

    }

    public String gerarNumNumlAgregacoes() {

        String agregacoes = "";
        for (Class<?> classe : classes) {

            for (Field field : classe.getDeclaredFields()) {

                if (!field.getType().isPrimitive()
                        && classes.contains(field.getType())) {

                    String agregacao = "[" + classe.getSimpleName() + "]o-[" + field.getType().getSimpleName() + "]\n";
                    if (!agregacoes.contains(agregacao)) {
                        agregacoes += agregacao;
                    }
                }

            }
        }
        return agregacoes;
    }

    public String gerarNumNumlGeneralizacoes() {

        String agregacoes = "";
        for (Class<?> classe : classes) {

            if (!classe.isPrimitive()
                    && classes.contains(classe.getSuperclass())) {
                agregacoes += "[" + classe.getSimpleName() + "]-:>[" + classe.getSuperclass().getSimpleName() + "]\n";
            }

            for (Class<?> interfaceImplementada : classe.getInterfaces()) {

                if (!interfaceImplementada.isPrimitive()
                        && classes.contains(interfaceImplementada)) {
                    agregacoes += "[" + classe.getSimpleName() + "]--:>[" + interfaceImplementada.getSimpleName() + "]\n";
                }

            }

        }
        return agregacoes;
    }

    public String gerarNumNumlEntidades() {

        String resultadoEntidades = "";

        for (Class<?> classe : classes) {

            String str = "";

            if (classe.isInterface()) {
                str += "<interface>";
            } else if (Modifier.isAbstract(classe.getModifiers())) {
                str += "<abstract>";
            }

            str += classe.getSimpleName() + "|\n";

            for (Field field : classe.getDeclaredFields()) {

                if (Modifier.isPrivate(field.getModifiers())) {
                    str += "-";
                } else if (Modifier.isPublic(field.getModifiers())) {
                    str += "+";
                } else if (Modifier.isProtected(field.getModifiers())) {
                    str += " ";
                }

                str += field.getName() + ":" + field.getType().getSimpleName() + ";\n";

            }
            str = str.substring(0, str.length() - 2) + "\n";

            str += "|";

            for (Method method : classe.getDeclaredMethods()) {

                if (method.isSynthetic() || method.isBridge()) {
                    continue;
                }

                if (Modifier.isPrivate(method.getModifiers())) {
                    str += "-";
                } else if (Modifier.isPublic(method.getModifiers())) {
                    str += "+";
                } else if (Modifier.isProtected(method.getModifiers())) {
                    str += " ";
                }

                str += method.getName() + "(";

                for (Class<?> tipo : method.getParameterTypes()) {
                    str += tipo.getSimpleName() + ",";
                }
                if (method.getParameterTypes().length > 0) {
                    str = str.substring(0, str.length() - 1);
                }

                str += "):" + method.getReturnType().getSimpleName() + ";\n";

            }

            str = str.substring(0, str.length() - 2);

            str = str.replace("[", "\\[").replace("]", "\\]");

            str = "[" + str + "]\n";

            resultadoEntidades += str;
        }
        return resultadoEntidades;
    }

}
/*
 Set<Class<?>> classes = new HashSet();
        
 classes.add(Observer.class);
 classes.add(Observable.class);
        
 classes.add(Jogo.class);
 classes.add(Jogador.class);
        
 classes.add(LoggerSimples.class);
 classes.add(Logger.class);
 classes.add(JogoTresLinha.class);
 classes.add(LoggerJogo.class);
 classes.add(TabuleiroTresLinha.class);
 classes.add(GestorDeJogosTresLinha.class);
 classes.add(LoggerGestor.class);
 classes.add(EventoJogo.class);
        
 classes.add(ConjuntoAleatorio.class);
 classes.add(ConjuntoAleatorioPecasEspeciais.class);
 classes.add(ConjuntoAleatorioPecasNormais.class);
        
        
 classes.add(EstrategiaPontuacao.class);
 classes.add(EstrategiaPontuacaoBase.class);
 classes.add(EstrategiaPontuacaoCorrida.class);

 classes.add(ConjuntoAleatorio.class);
 classes.add(Ranking.class);
 classes.add(LinhaTres.class);
 classes.add(Historico.class);
        
 classes.add(PecaCaveira.class);
 classes.add(PecaSimples.class);
 classes.add(PecaTresLinha.class);
        
 classes.add(UtilsSerializacao.class);

 UML uml = new UML(classes);
 System.out.println(uml.gerarNumNumlStyle());
 System.out.println(uml.gerarNumNumlEntidades());
 System.out.println(uml.gerarNumNumlAgregacoes());
 System.out.println(uml.gerarNumNumlGeneralizacoes());
 */
