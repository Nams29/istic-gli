package solitaire.command;

import solitaire.CSolitaireMain;

public class NewGameCommand implements Command {
	
	private CSolitaireMain solitaire;
	
	public NewGameCommand(CSolitaireMain controleur) {
		this.solitaire = controleur;
	}
	
	@Override
	public void execute() {
		this.solitaire.newGame();
	}

}
