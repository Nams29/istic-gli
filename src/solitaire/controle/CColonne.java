package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.Colonne;
import solitaire.application.Tas;
import solitaire.application.Usine;
import solitaire.presentation.PColonne;
import solitaire.presentation.PTasAlterne;

public class CColonne extends Colonne {
	
	private PColonne presentation;
	
	private CTasDeCartes hiddenDeck;
	private CTasAlterne alternateDeck;
	
	public CColonne(String nom, Usine usine) {
		super(nom, usine);
		this.hiddenDeck = (CTasDeCartes) this.cachees;
		this.alternateDeck = (CTasAlterne) this.visibles;
		this.presentation = new PColonne(this, hiddenDeck.getPresentation(), 
				((PTasAlterne) this.alternateDeck.getPresentation()));
	}
	
	@Override
	public void retournerCarte() {
		if (isCarteRetournable()) {
			try {
				super.retournerCarte();
				this.presentation.c2pFlipCard();
			} catch (Exception e) {
				System.out.println("Erreur en retournant une carte : "+e.getMessage());
			}
		}
	}
	
	@Override
	public void empiler(Carte c) {
		if (this.isEmpilable(c)) {
			super.empiler(c);
		}
	}
	
	@Override
	public void empiler(Tas tas) {
		if (this.isEmpilable(tas)) {
			super.empiler(tas);
		}
	}
	
	@Override
	public void empiler(Tas tas, int n) {
		if (this.isEmpilable(tas, n)) {
			super.empiler(tas, n);
		}
	}
	
	@Override
	public void depiler() {
		if (!this.isVide()) {
			try {
				super.depiler();
			} catch (Exception e) {
				System.out.println("Erreur en dépilant : "+e.getMessage());
			}
		}
	}
	
	@Override
	public void setReserve(Tas tas) {
		super.setReserve(tas);
		this.presentation.c2pFlipCard();
	}
	
	/**
	 * React to a click on the hidden deck
	 */
	public void p2cHiddenDeckClick() {
		this.retournerCarte();
	}
	
	/**
	 * Return the presentation
	 * @return the presentation
	 */
	public PColonne getPresentation() {
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
	public CTasAlterne getAlternateDeck() {
		return alternateDeck;
	}
	
	/**
	 * @return the number of hidden cards
	 */
	public int getNombreCache() {
		return hiddenDeck.getNombre();
	}
}
