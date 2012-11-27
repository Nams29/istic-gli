package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesAlternees;
import solitaire.application.Usine;
import solitaire.presentation.PTasAlterne;
import solitaire.presentation.PTasDeCartes;

public class CTasAlterne extends TasDeCartesAlternees implements ICTas {
	
	private PTasAlterne presentation; 
	
	public CTasAlterne(String nom, Usine usine) {
		super(nom, usine);
		this.presentation = new PTasAlterne(this);
	}
	
	/**
	 * @return the presentation
	 */
	public PTasAlterne getPresentation() {
		return this.presentation;
	}
	
	@Override
	public void empiler(Carte c) {
		//if (this.isEmpilable(c))  {
			super.empiler(c);
			this.presentation.empiler(((CCarte)c).getPresentation());
		//}
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
}
