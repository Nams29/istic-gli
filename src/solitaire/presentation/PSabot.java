package solitaire.presentation ;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import solitaire.controle.CSabot;

public class PSabot extends JPanel {
	
	private static final long serialVersionUID = 5119385230762345237L;
	
	private CSabot controleur;
	
	private PTasDeCartes hiddenDeck;
	private PTasDeCartes visibleDeck;
	
	private final int INSET_X = 15;
	private final int INSET_Y = 0;
	
	public PSabot(CSabot cSabot, PTasDeCartes hiddenDeck, PTasDeCartes visibleDeck) {
		this.controleur = cSabot;
		this.hiddenDeck = hiddenDeck;
		this.visibleDeck = visibleDeck;
		
		// Layout
		this.initLayout();
		this.setOpaque(false);
		
		// Dimension
		this.setSize(2*PCarte.largeur+INSET_X, PCarte.hauteur+INSET_Y);
		this.setPreferredSize(getSize());
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		// Tas de cartes
		this.hiddenDeck.setDxDy(0, 0);
		this.visibleDeck.setDxDy(15, 0);
		
		// Affichage
		this.setLayout(new BorderLayout());
		this.add(hiddenDeck, BorderLayout.WEST);
		this.add(visibleDeck, BorderLayout.EAST);
		
		// Listener
		this.hiddenDeck.addMouseListener(new RetournerCarteListener());
	}
	
	/**
	 * Set the size of the component
	 */
	public void setSize() {
		Dimension dim = new Dimension(
				this.hiddenDeck.getWidth()+this.visibleDeck.getWidth()+INSET_X, 
				this.getHeight());
		this.setSize(dim);
		this.setPreferredSize(dim);
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
