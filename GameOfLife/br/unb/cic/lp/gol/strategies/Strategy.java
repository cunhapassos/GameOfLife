package br.unb.cic.lp.gol.strategies;

import br.unb.cic.lp.gol.Cell;
import br.unb.cic.lp.gol.GameEngine;

public interface Strategy {
	
	public void init(GameEngine engine, Cell[][] cells);
	public String getName();
	public void nextGeneration(int height, int width);
	public String getRule();
	public String getCharacter();
}
