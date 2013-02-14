package br.unb.cic.lp.gol.strategies;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Anota a estrategia usada em modo default.
 * Se houver mais de uma classe anotada default, eh indefinido qual delas serah escolhida
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultStrategy {
}
