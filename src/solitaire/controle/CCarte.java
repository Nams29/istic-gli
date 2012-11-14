package solitaire.controle;

import solitaire.application.Carte;
import solitaire.presentation.PCarte;

public class CCarte extends Carte {
	
	private PCarte presentation;
	
	/**
	 * Constructor
	 * @param valeur	The value of the card
	 * @param couleur	The color of the card
	 */
	public CCarte(int valeur, int couleur) {
		super(Math.min(NbCartesParCouleur, Math.max(1,valeur)), 
				Math.min(NbCouleurs, Math.max(1,couleur)));
		this.presentation = new PCarte(valeurs[this.getValeur()-1]+
									   couleurs[this.getCouleur()-1]);
		this.presentation.setFaceVisible(false);
	}
	
	@Override
	public void setFaceVisible(boolean v) {
		super.setFaceVisible(v);
		this.presentation.setFaceVisible(v);
	}
	
	/**
	 * Get the presentation
	 * @return the presentation
	 */
	public PCarte getPresentation() {
		return this.presentation;
	}
}