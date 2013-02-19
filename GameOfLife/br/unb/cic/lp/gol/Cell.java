package br.unb.cic.lp.gol;

import java.util.Observable;
import br.unb.cic.lp.gol.ObserverMessages;

public class Cell extends Observable{
	/**
	 * @uml.property  name="alive"
	 */
	private boolean alive = false;

	/**
	 * @return
	 * @uml.property  name="alive"
	 */
	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		this.alive = false;
		setChanged(); // indica que o estado do objeto mudou.
		notifyObservers(ObserverMessages.CELL_REVIVE.getMessage());
	}
	
	public void revive() {
		this.alive = true;
		setChanged(); // indica que o estado do objeto mudou.
		notifyObservers(ObserverMessages.CELL_KILL.getMessage());
	}
}
