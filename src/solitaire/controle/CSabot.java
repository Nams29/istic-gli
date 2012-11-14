package solitaire.controle;

import solitaire.application.Sabot;
import solitaire.application.Usine;
import solitaire.presentation.PSabot;

public class CSabot extends Sabot {
	
	private static String sabot_cache = "cache";
	private static String sabot_visible = "visible";
	
	private PSabot presentation;
	private CTasDeCartes cache;
	private CTasDeCartes visible;
	
	public CSabot(String nom, Usine usine) {
		super(nom, usine);
		this.cache = (CTasDeCartes) ((CUsine)usine).newTasDeCartes(sabot_cache, usine);
		this.visible = (CTasDeCartes) ((CUsine)usine).newTasDeCartes(sabot_visible, usine);
		this.presentation = new PSabot(this, ((CTasDeCartes)cache).getPresentation(), 
				((CTasDeCartes)visible).getPresentation());
	}
	
	/**
	 * Return the presentation
	 * @return the presentation
	 */
	public PSabot getPresentation() {
		return this.presentation;
	}
	
}
