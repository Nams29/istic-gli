package solitaire.command;

import solitaire.CSolitaireMain;

public class OptionsCommand implements Command {
	
	private CSolitaireMain solitaire;
	
	public OptionsCommand(CSolitaireMain controleur) {
		this.solitaire = controleur;
	}
	
	@Override
	public void execute() {
		this.solitaire.options();
	}

}
