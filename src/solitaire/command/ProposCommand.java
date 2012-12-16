package solitaire.command;

import solitaire.CSolitaireMain;

public class ProposCommand implements Command {
	
	private CSolitaireMain solitaire;
	
	public ProposCommand(CSolitaireMain solitaire) {
		this.solitaire = solitaire;
	}
	
	@Override
	public void execute() {
		this.solitaire.propos();
	}

}
