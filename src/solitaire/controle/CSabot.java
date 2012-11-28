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
		this.hiddenDeck = (CTasDeCartes) this.cachees;
		this.visibleDeck = (CTasDeCartes) this.visibles;
		this.presentation = new PSabot(this, hiddenDeck.getPresentation(), visibleDeck.getPresentation());
	}
	
	@Override
	public void depiler() throws Exception {
		if (!isVide()) {
			super.depiler();
		}
	}

	@Override
	public void retourner() throws Exception {
		if (isRetournable()) {
			super.retourner();
			this.presentation.setSize();
		}
	}

	@Override
	public void retournerCarte() throws Exception {
		if (isCarteRetournable()) {
			super.retournerCarte();
			this.presentation.setSize();
		}
	}
	
	/**
	 * React to a click on the hidden deck
	 */
	public void p2cHiddenDeckClick() {
		try {
			if (isRetournable()) {
				this.retourner();
			}
			else {
				for (int i=0; i<CSolitaire.GAME_MODE; i++) {
					this.retournerCarte();
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur en retournant une carte : "+e.getMessage());
		}
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
