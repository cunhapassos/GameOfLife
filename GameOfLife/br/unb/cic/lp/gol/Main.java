package br.unb.cic.lp.gol;

public class Main {

	public static void main(String args[]) {
		GameController controller = new GameController();
		
		GameEngine engine = new GameEngine(10, 10);
		
		Statistics statistics = new Statistics(engine);
		
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		GameView board = new GameView(controller);
		
		controller.setBoard(board);
		
		controller.start();
	}
}
