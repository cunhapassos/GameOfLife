package br.unb.cic.lp.gol.strategies;



/**
 * @author eric
 *
 * Amoeba : 1358/357
 */
public class Amoeba extends StrategyTemplate {

	private final String name = "Amoeba";
	private final String rule = "1358/357";
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
				&& (n == 1 || n == 3 || n == 5 || n == 8);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 5 || n == 7);
	}

}
