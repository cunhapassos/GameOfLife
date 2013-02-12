public class Cell {
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
	}
	
	public void revive() {
		this.alive = true;
	}
}
