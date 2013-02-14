package br.unb.cic.lp.gol;

/**
 * @author eric
 *
 */

public enum ObserverMessages {
	CELL_KILL("kill"),
	CELL_REVIVE("revive");
	
	
	private String message;
	
	private ObserverMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}


