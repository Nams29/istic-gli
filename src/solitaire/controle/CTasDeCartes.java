package solitaire.controle;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import solitaire.application.Carte;
import solitaire.application.TasDeCartes;
import solitaire.application.Usine;
import solitaire.presentation.PCarte;
import solitaire.presentation.PTasDeCartes;

public class CTasDeCartes extends TasDeCartes implements ICTas {

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
			this.presentation.empiler(((CCarte) c).getPresentation());
		}
	}
	
	
	@Override
	public void p2cDragEnter(ICTas controller) {
		if(this.isEmpilable(controller)){
			System.out.println("OK");
		}
		else{
			System.out.println("PAS ok");
		}	
	}

	@Override
	public void p2cDragExit(ICTas controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void p2cDrop(ICTas controller) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the presentation
	 */
	public PTasDeCartes getPresentation() {
		return this.presentation;
	}

	/**
	 * TEST 
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Tas De Carte");
		frame.getContentPane().setLayout(new GridLayout(1, 2));

		CUsine usine = new CUsine();

		// deux cartes cachées, une carte visible verticales
		CTasDeCartes tas1 = (CTasDeCartes) usine.newTasDeCartes("test_cache", usine);
		PTasDeCartes pres1 = tas1.getPresentation();
		CCarte carte = (CCarte) usine.newCarte(1, 1);
		carte.setFaceVisible(true);
		pres1.setDxDy(0, 10);
		tas1.empiler(usine.newCarte(8, 2));
		tas1.empiler(usine.newCarte(1, 3));
		tas1.empiler(usine.newCarte(12, 1));
		tas1.empiler(carte);
		pres1.setLocation(20, 20);
		frame.getContentPane().add(pres1) ;

		// trois cartes visibles horizontales
		CTasDeCartes tas2 = (CTasDeCartes) usine.newTasDeCartes("test_visible", usine);
		PTasDeCartes pres2 = tas2.getPresentation();
		pres2.setDxDy(15, 0);
		CCarte carte1 = (CCarte) usine.newCarte(1, 1);
		CCarte carte2 = (CCarte) usine.newCarte(11, 3);
		CCarte carte3 = (CCarte) usine.newCarte(4, 4);
		carte1.setFaceVisible(true);
		carte2.setFaceVisible(true);
		carte3.setFaceVisible(true);
		tas2.empiler(carte1);
		tas2.empiler(carte2);
		tas2.empiler(carte3);
		
		tas2.empiler(tas1,2);
		
		// Test depiler
		tas2.empiler(usine.newCarte(9, 2));
		tas2.depiler();
		pres2.setLocation(40 + PCarte.largeur, 20);
		frame.getContentPane().add(pres2) ;

		// taille du conteneur
		Dimension d = new Dimension(300, 200);
		frame.setSize(d);
		frame.setPreferredSize(d);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
