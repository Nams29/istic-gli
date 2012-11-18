package solitaire.controle;

import solitaire.application.Sabot;
import solitaire.application.Usine;
import solitaire.presentation.PSabot;

public class CSabot extends Sabot {
		
	private PSabot presentation;
	
	private CTasDeCartes hiddenDeck;
	private CTasDeCartes visibleDeck;
	
	public CSabot(String nom, Usine usine) {
		super(nom, usine);
		this.hiddenDeck = (CTasDeCartes) this.visibles;
		this.visibleDeck = (CTasDeCartes) this.hiddenDeck;
		this.presentation = new PSabot(this, hiddenDeck.getPresentation(), visibleDeck.getPresentation());
	}
	
	/**
	 * @return the presentation
	 */
	public PSabot getPresentation() {
		return this.presentation;
	}

	/**
	 * @return the hiddenDeck
	 */
	public CTasDeCartes getHiddenDeck() {
		return hiddenDeck;
	}

	/**
	 * @return the visibleDeck
	 */
	public CTasDeCartes getVisibleDeck() {
		return visibleDeck;
	}
}
