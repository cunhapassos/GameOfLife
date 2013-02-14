/**
 * 
 */
package br.unb.cic.lp.gol.strategies;

/**
 * @author eric
 * 
 * WalledCities :2345/45678
 *
 */
public class WalledCities extends StrategyTemplate {

	private final String name = "Walled Cities";
	private final String rule = "2345/45678";
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
				&& (n >= 2 && n <= 5);
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int n = numberOfNeighborhoodAliveCells(i, j);
		return (!cells[i][j].isAlive())
				&& (n >= 4 && n <= 8);
	}

}
