package br.unb.cic.lp.gol.strategies;

import java.util.ArrayList;
import java.util.List;

import br.unb.cic.lp.gol.Cell;
import br.unb.cic.lp.gol.GameEngine;

/**
 * Calcula uma nova geracao do ambiente. Padrao Template Method
 */

public abstract class StrategyTemplate implements Strategy {
	
	GameEngine engine;		//Toda estrategia depende de alguns parametros especificos do engine
	Cell[][] cells;
	List<Cell> mustRevive = new ArrayList<Cell>();
	List<Cell> mustKill = new ArrayList<Cell>();
	
	public void init(GameEngine engine, Cell[][] cells) {
		this.engine = engine;
		this.cells = cells;
	}
	
	
	public void nextGeneration(int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (shouldRevive(i, j)) {
					mustRevive.add(cells[i][j]);
				} else if ((!shouldKeepAlive(i, j)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		for (Cell cell : mustRevive) {
			cell.revive();
		}

		for (Cell cell : mustKill) {
			cell.kill();
		}
	}
	
	protected boolean validPosition(int a, int b) {
		return engine.validPosition(a, b);
	}
	
	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	protected int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (validPosition(a, b) && (!(a == i && b == j))
						&& cells[a][b].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}
	
	//Os dois metodos abaixo sao implementados pelas estrategias concretas
	
	protected abstract boolean shouldRevive(int i, int j);
	protected abstract boolean shouldKeepAlive(int i, int j);
	
	
	

}
