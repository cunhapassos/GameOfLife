/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * Coagulations : 235678/378
 *
 */
public class Coagulations extends StrategyTemplate {

	private final String name = "Coagulations";
	private final String rule = "235678/378";
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
				&& (n == 2 || n == 3 || n == 5 || n == 6 || n == 7 || n == 8);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 3 || n == 7 || n == 8);
	}

}
