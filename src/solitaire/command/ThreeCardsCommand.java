package solitaire.command;

import solitaire.CSolitaireMain;

public class ThreeCardsCommand implements Command {
	private CSolitaireMain solitaire;

	public ThreeCardsCommand(CSolitaireMain controleur) {
		this.solitaire = controleur;
	}

	@Override
	public void execute() {
		this.solitaire.setNumberCards(3);
	}

}
