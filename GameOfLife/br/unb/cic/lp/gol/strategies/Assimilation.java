/**
 * 
 */
package br.unb.cic.lp.gol.strategies;


/**
 * @author eric
 *
 * Assimilations : 4567/345
 *
 */
public class Assimilation extends StrategyTemplate {

	private final String name = "Assimilations";
	private final String rule = "4567/345";
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
				&& (n == 4 || n == 5 || n == 6 || n == 7);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 4 || n == 5);
	}

}
