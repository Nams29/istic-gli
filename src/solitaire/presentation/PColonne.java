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

	private static int DX_hidden = 0;
	private static int DY_hidden = 15;
	private static int DX_visible = 0;
	private static int DY_visible = 30;
	
	public PColonne(CColonne cColonne, PTasDeCartes tas1, PTasAlterne tas2) {
		this.controleur = cColonne;
		this.hiddenDeck = tas1;
		this.alternateDeck = tas2;
		this.alternateDeck.setBackground(Color.PINK);
		this.alternateDeck.setOpaque(false);
		
		this.initLayout();
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		this.hiddenDeck.setDxDy(DX_hidden, DY_hidden);
		this.alternateDeck.setDxDy(DX_visible, DY_visible);
		
		this.setLayout(null);
		this.setOpaque(false);

		this.add(alternateDeck);
		this.add(hiddenDeck);
	}
	
	/**
	 * Set the size of the hidden deck
	 */
	public void c2pFlipCard() {
		int nbHidden = controleur.getNombreCache();
		int nbVisible = controleur.getNombre();
		
		// Decalage des cartes visibles
		this.alternateDeck.setLocation(nbHidden*DX_hidden, nbHidden*DY_hidden);
		
		// Taille colonne
		int x = nbHidden*DX_hidden + ((nbVisible-1)*DX_visible) + PCarte.largeur;
		int y = nbHidden*DY_hidden + ((nbVisible-1)*DY_visible) + PCarte.hauteur;
		Dimension dimension = new Dimension(x, y);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		
		this.repaint();
	}
		
} 
