package solitaire.command;

import solitaire.controle.CSolitaire;

public class NewGameCommand implements Command {
	
	private CSolitaire solitaire;
	
	public NewGameCommand(CSolitaire solitaire) {
		this.solitaire = solitaire;
	}
	
	@Override
	public void execute() {
		this.solitaire.newGame();
	}

}
