package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.TasDeCartes;
import solitaire.application.Usine;
import solitaire.presentation.PTasDeCartes;

public class CTasDeCartes extends TasDeCartes {
	
	private PTasDeCartes presentation;
	
	public CTasDeCartes(String nom, Usine usine) {
		super(nom, usine);
		this.presentation = new PTasDeCartes(this);
	}
	
	@Override
	public void depiler() {
		if (!this.isVide()) {
			try {
				CCarte c = (CCarte) this.getSommet();
				super.depiler();
				this.presentation.depiler(c.getPresentation());
			} catch (Exception e) {
				System.out.println("Erreur en dépilant : "+e.getMessage());
			}
		}
	}
	
	@Override
	public void empiler(Carte c) {
		if (this.isEmpilable(c)) {
			super.empiler(c);
			try {
				if (this.getSommet() == c) {
					this.presentation.empiler(((CCarte) c).getPresentation());
				}
			} catch (Exception e) {
				System.out.println("Erreur en empilant : "+e.getMessage());
			}
		}
	}
	
	/**
	 * Get the presentation
	 * @return the presentation
	 */
	public PTasDeCartes getPresentation() {
		return this.presentation;
	}
	
}
