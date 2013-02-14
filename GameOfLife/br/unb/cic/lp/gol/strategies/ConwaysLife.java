package br.unb.cic.lp.gol.strategies;


/**
 * @author eric
 * 
 * Conway's Life : 23/3
 *
 */
@DefaultStrategy
public class ConwaysLife extends StrategyTemplate {

	private final String name = "Conway's Life";
	private final String rule = "23/3";
	private final String character = "Chaotic";


	public String getName() {
		return name;
	}
	
	public String getRule() {
		return rule;
	}
	
	public String getCharacter() {
		return character;
	}
	
	@Override
	protected boolean shouldKeepAlive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (cells[i][j].isAlive())
				&& (n == 2 || n == 3);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3);
	}


}
