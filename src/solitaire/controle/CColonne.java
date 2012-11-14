package solitaire.controle;

import solitaire.application.Colonne;
import solitaire.application.Usine;
import solitaire.presentation.PColonne;
import solitaire.presentation.PSabot;

public class CColonne extends Colonne {
	
	
	
	private static String sabot_cache = "cache";
	private static String sabot_visible = "visible";
	
	private PColonne presentation;
	private CTasDeCartes cache;
	private CTasDeCartes visible;
	
	public CColonne(String nom, Usine usine) {
		super(nom, usine);
		this.cache = (CTasDeCartes) ((CUsine)usine).newTasDeCartes(sabot_cache, usine);
		this.visible = (CTasDeCartes) ((CUsine)usine).newTasDeCartes(sabot_visible, usine);
		this.presentation = new PColonne(this, ((CTasDeCartes)cache).getPresentation(), 
				((CTasDeCartes)visible).getPresentation());
	}
	
	/**
	 * Return the presentation
	 * @return the presentation
	 */
	public PColonne getPresentation() {
		return this.presentation;
	}
	
}
