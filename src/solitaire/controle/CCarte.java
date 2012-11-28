package solitaire.controle;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

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
	
	/**
	 * Return the color corresponding the index
	 * @param i the index
	 * @return the color
	 */
	public static String getCouleur(int i) {
		return couleurs[i];
	}
	
	/**
	 * TEST 
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Carte");
		frame.getContentPane().setLayout(new GridLayout(1, 2));
		
		CUsine usine = new CUsine();
		
		// une carte visible
		CCarte carte1 = (CCarte) usine.newCarte(7, 4);
		PCarte pres1 = carte1.getPresentation();
		carte1.setFaceVisible(true);
		frame.getContentPane().add(pres1) ;

		// une carte cachée
		CCarte carte2 = (CCarte) usine.newCarte(1, 1);
		PCarte pres2 = carte2.getPresentation();
		carte2.setFaceVisible(false);
		frame.getContentPane().add(pres2) ;

		// taille du conteneur
		Dimension d = new Dimension(2 * PCarte.largeur + 40, PCarte.hauteur + 40);
		frame.setSize(d);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}