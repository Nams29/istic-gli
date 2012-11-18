package solitaire.controle;

import solitaire.application.*;

public class CUsine extends Usine {
	
	public CUsine() {
		super();
	}
	
	@Override
	public Carte newCarte(int valeur, int couleur) {
		return new CCarte(valeur, couleur);
	}
	
	@Override
	public TasDeCartes newTasDeCartes(String nom, Usine usine) {
		return new CTasDeCartes(nom, usine);
	}
	
	@Override
	public Sabot newSabot(String nom, Usine usine) {
		return new CSabot(nom, usine);
	}
	
	@Override
	public TasDeCartesAlternees newTasDeCartesAlternees(String nom, Usine usine) {
		return new CTasAlterne(nom, usine);
	}
	
	@Override
	public Colonne newColonne(String nom, Usine usine) {
		return new CColonne(nom, usine);
	}
}
