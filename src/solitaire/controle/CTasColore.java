package solitaire.controle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesColorees;
import solitaire.application.Usine;
import solitaire.presentation.PTasColore;
import solitaire.presentation.PTasDeCartes;

public class CTasColore extends TasDeCartesColorees implements ICTas {
	
	private PTasColore presentation;
	
	public CTasColore(String nom, int couleur, Usine usine) {
		super(nom, couleur, usine);
		this.presentation = new PTasColore(this, couleur);
	}
	
	@Override
	public void empiler(Carte c) {
		if (this.isEmpilable(c))  {
			super.empiler(c);
			this.presentation.empiler(((CCarte)c).getPresentation());
		}
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
	
	/**
	 * @return the presentation
	 */
	public PTasColore getPresentation() {
		return this.presentation;
	}
	
	/**
	 * TEST
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test TasColore");
		
		CUsine usine = new CUsine();
		
		// tas coloré
		CTasColore tasColore = 
				(CTasColore) usine.newTasDeCartesColorees("tas coloré", 3, usine);
		
		frame.getContentPane().add(tasColore.getPresentation());
		
		// taille
		Dimension dim = new Dimension(500, 300);
		frame.setSize(dim);
		frame.setPreferredSize(dim);
		
		// panneau du plan de jeu
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(163, 163, 215));
		
		// fermeture
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// afficahge
		frame.setVisible(true);
	}
	
}
