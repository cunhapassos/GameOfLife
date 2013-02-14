/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * Replicator : 1357/1357
 *
 */
public class Replicator extends StrategyTemplate {

	private final String name = "Replicator";
	private final String rule = "1357/1357";
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
				&& (n == 1 || n == 3 || n == 5 || n == 7);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 1 || n == 3 || n == 5 || n == 7);
	}

}
