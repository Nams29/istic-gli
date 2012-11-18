package solitaire.presentation ;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import solitaire.controle.CColonne;

public class PColonne extends JPanel {
	
	private static final long serialVersionUID = 5119385230762345237L;
	
	private CColonne controleur;
	
	private PTasDeCartes hiddenDeck;
	private PTasAlterne alternateDeck;
	
	private static int DX = 0;
	private static int DY = 30;
	
	public PColonne(CColonne cColonne, PTasDeCartes tas1, PTasAlterne tas2) { 
		this.controleur = cColonne;
		this.hiddenDeck = tas1;
		this.alternateDeck = tas2;
		
		this.initLayout();
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		this.hiddenDeck.setDxDy(DX, DY);
		this.alternateDeck.setDxDy(DX, DY);
		
		this.setLayout(null);

		this.add(alternateDeck);
		this.add(hiddenDeck);
	}
	
	/**
	 * Set the size of the hidden deck
	 * @param nbCartes the cards on the hidden deck
	 */
	public void setSize() {
		int nbHidden = controleur.getNombreCache();
		int nbVisible = controleur.getNombre();
		
		// Decalage des cartes visibles
		this.alternateDeck.setLocation(nbHidden*DX, nbHidden*DY);
		
		// Taille colonne
		int x = nbHidden*DX + nbVisible*DX - DX + PCarte.largeur;
		int y = nbHidden*DY + nbVisible*DY - DY + PCarte.hauteur;
		Dimension dimension = new Dimension(x, y);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
	}
	
	
		
} 
