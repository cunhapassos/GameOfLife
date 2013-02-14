/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * Diamoeba : 5678/35678
 *
 */
public class Diamoeba extends StrategyTemplate {

	private final String name = "Diamoeba";
	private final String rule = "5678/35678";
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
				&& (n == 5 || n == 6 || n == 7 || n == 8);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 5 || n == 6 || n == 7 || n == 8);
	}

}
