/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * 34 Life : 34/34
 *
 */
public class Strat34Life extends StrategyTemplate {

	private final String name = "34 Life";
	private final String rule = "34/34";
	private final String character = "Exploding";

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
				&& (n == 3 || n == 4);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 4);
	}

}
