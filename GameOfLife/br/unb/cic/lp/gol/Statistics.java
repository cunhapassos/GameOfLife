package br.unb.cic.lp.gol;
	import java.util.Observable;
	import java.util.Observer;

	/**
	 * Essa tambem eh uma classe com baixa coesao, pois mistura caracteristicas
	 * de Model (as propriedades) com caracteristicas de view (metodo display())
	 * 
	 * Nao eh uma boa implementacao.
	 * 
	 * @author rodrigobonifacio
	 */
	public class Statistics implements Observer{
		/*
		 * private int revivedCells; private int killedCells;
		 */

		/**
		 * @uml.property  name="revivedCells"
		 */
		private int revivedCells;
		/**
		 * @uml.property  name="killedCells"
		 */
		private int killedCells;
		/**
		 * @uml.property  name="gameEngine"
		 */
		Observable gameEngine;

		public Statistics(Observable gameEngine) {
			this.gameEngine = gameEngine;
			gameEngine.addObserver(this);
			revivedCells = 0;
			killedCells = 0;
		}

		public void update(Observable obs, Object arg) {
			if (arg == "revive") {
				this.recordRevive();
			}
			if (arg == "kill") {
				this.recordKill();
			}
		}

		/*
		 * public Statistics() { revivedCells = 0; killedCells = 0; }
		 */

		/**
		 * @return
		 * @uml.property  name="revivedCells"
		 */
		public int getRevivedCells() {
			return revivedCells;
		}

		public void recordRevive() {
			this.revivedCells++;
		}

		/**
		 * @return
		 * @uml.property  name="killedCells"
		 */
		public int getKilledCells() {
			return killedCells;
		}

		public void recordKill() {
			this.killedCells++;
		}
		/* Codigo Original
		public void display() {
			System.out.println("=================================");
			System.out.println("           Statistics            ");
			System.out.println("=================================");
			System.out.println("Revived cells: " + revivedCells);
			System.out.println("Killed cells: " + killedCells);
			System.out.println("=================================");
		}
		*/
	}
