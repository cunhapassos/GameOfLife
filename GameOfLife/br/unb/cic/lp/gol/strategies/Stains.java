/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * Stains : 235678/3678
 *
 */
public class Stains extends StrategyTemplate {

	private final String name = "Stains";
	private final String rule = "235678/3678";
	private final String character = "Stable";


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
				&& (n == 2 || n == 3 || n == 5 || n == 6 || n == 7 || n == 8);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 6 || n == 7 || n == 8);
	}

}
