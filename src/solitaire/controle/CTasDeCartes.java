package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.TasDeCartes;
import solitaire.application.Usine;
import solitaire.presentation.PTasDeCartes;

public class CTasDeCartes extends TasDeCartes {
	
	private PTasDeCartes presentation;
	
	public CTasDeCartes(String arg0, Usine arg1) {
		super(arg0, arg1);
		this.presentation = new PTasDeCartes(this);
	}
	
	@Override
	public void depiler() {
		try {
			CCarte c = (CCarte) this.getSommet();
			super.depiler();
			this.presentation.depiler(c.getPresentation());
		} catch (Exception e) {
			System.out.println("Erreur en dépilant : la pile est vide");
		}
	}
	
	@Override
	public void empiler(Carte c) {
		CCarte cc = (CCarte) c;
		super.empiler(cc);
		this.presentation.empiler(cc.getPresentation());
	}
	
	/**
	 * Get the presentation
	 * @return the presentation
	 */
	public PTasDeCartes getPresentation() {
		return this.presentation;
	}
	
}
