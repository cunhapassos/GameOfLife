/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * Seeds (2) : /2
 *
 */
public class Seeds2 extends StrategyTemplate {

	private final String name = "Seeds 2";
	private final String rule = "/2";
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
		return !(cells[i][j].isAlive());	//every living cell dies :(
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n == 2);
	}

}