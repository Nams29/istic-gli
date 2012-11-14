package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.Sabot;
import solitaire.application.TasDeCartes;
import solitaire.application.Usine;

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
}
