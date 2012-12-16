package solitaire.command;

import solitaire.CSolitaireMain;

public class ExitCommand implements Command {
	
	private CSolitaireMain solitaire;
	
	public ExitCommand(CSolitaireMain solitaire) {
		this.solitaire = solitaire;
	}
	
	@Override
	public void execute() {
		this.solitaire.exit();
	}

}
