package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Classe que atua como um controlador do padrao MVC, separando os componentes
 * da camada de apresentacao e model.
 * 
 * @author rbonifacio
 */
public class GameController {

	/**
	 * @uml.property name="engine"
	 * @uml.associationEnd
	 */
	private GameEngine engine;
	/**
	 * @uml.property name="board"
	 * @uml.associationEnd inverse="controller:GameView"
	 */
	private GameView board;
	/**
	 * @uml.property name="statistics"
	 * @uml.associationEnd
	 */
	private Statistics statistics;

	/**
	 * @return
	 * @uml.property name="engine"
	 */
	public GameEngine getEngine() {
		return engine;
	}

	/**
	 * @param engine
	 * @uml.property name="engine"
	 */
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	/**
	 * @return
	 * @uml.property name="board"
	 */
	public GameView getBoard() {
		return board;
	}

	/**
	 * @param board
	 * @uml.property name="board"
	 */
	public void setBoard(GameView board) {
		this.board = board;
	}

	/**
	 * @param statistics
	 * @uml.property name="statistics"
	 */
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public void start() {
		board.update();
	}
	/* Codigo Original
	public void halt() {
		// oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	*/
	//acrescentado p/ adequar ao padr‹o arquitetural MVC
	public int getKilledCells(){
		return statistics.getKilledCells();
	}
	
	public int getRevivedCells() {
		return statistics.getRevivedCells();
	}
	//acrescentado p/ adequar ao padr‹o arquitetural MVC
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			board.update();
		} catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}

	public void nextGeneration() {
		engine.nextGeneration();
		board.update();
	}

	public int getHeight() {
		return engine.getHeight();
	}

	public int getWidth() {
		return engine.getWidth();
	}

	public boolean isCellAlive(int i, int j) {
		return engine.isCellAlive(i, j);
	}

	public String getStrategyName() {
		return engine.getCurrentStrategyName();
	}

	// Encapsula a regra e o carater concatenados
	public String getStrategyDescriptionByName(String name) {
		return engine.getStrategyDescriptionByName(name);
	}

	public List<String> getAvailableStrategyNames() {
		return engine.getAvailableStrategyNames();
	}

	public void setCurrentStrategy(String name) {
		engine.setCurrentStrategyByName(name);
	}

}
