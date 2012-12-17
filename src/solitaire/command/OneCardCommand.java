package solitaire.command;

import solitaire.CSolitaireMain;

public class OneCardCommand implements Command {
	private CSolitaireMain solitaire;
	
	public OneCardCommand(CSolitaireMain controleur) {
		this.solitaire = controleur;
	}
	
	@Override
	public void execute() {
		this.solitaire.setNumberCards(1);
	}

}
