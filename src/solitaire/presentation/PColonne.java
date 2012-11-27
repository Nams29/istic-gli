package solitaire.presentation ;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
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
		this.alternateDeck.setBackground(Color.PINK);
		this.alternateDeck.setOpaque(false);
		
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
		
		// Listener
		this.hiddenDeck.addMouseListener(new RetournerCarteListener());
	}
	
	/**
	 * Set the size of the hidden deck
	 */
	public void c2pFlipCard() {
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
		
		// TODO : faire une methode dans ptasdecarte (activercurseur)
		/*if (nbVisible == 0) {
			this.hiddenDeck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else {
			this.hiddenDeck.setCursor(null);
		}*/
	}
	
	/**
	 * Class RetournerCarteListener
	 * Listen the clicks on the hidden deck
	 */
	private class RetournerCarteListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			controleur.p2cHiddenDeckClick();
		}

		@Override
		public void mousePressed(MouseEvent e) { }

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }
		
	}
		
} 
