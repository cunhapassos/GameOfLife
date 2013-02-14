/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * Serviettes : /234
 *
 */
public class Serviettes extends StrategyTemplate {

	private final String name = "Serviettes";
	private final String rule = "/234";
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
		return !(cells[i][j].isAlive());	// every living cell dies :(
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 2 || n == 3 || n == 4);
	}

}
