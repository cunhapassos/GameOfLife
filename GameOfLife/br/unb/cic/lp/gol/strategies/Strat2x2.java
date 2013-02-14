/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 *
 * 2x2 : 125/36
 *
 */
public class Strat2x2 extends StrategyTemplate {

	private final String name = "2x2";
	private final String rule = "125/36";
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
				&& (n == 1 || n == 2 || n == 5);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 6);
	}

}
