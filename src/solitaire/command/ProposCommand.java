package solitaire.command;

import solitaire.controle.CSolitaire;

public class ProposCommand implements Command {
	
	private CSolitaire solitaire;
	
	public ProposCommand(CSolitaire solitaire) {
		this.solitaire = solitaire;
	}
	
	@Override
	public void execute() {
		this.solitaire.propos();
	}

}
