package solitaire.presentation ;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import solitaire.controle.CSabot;

public class PSabot extends JPanel {
	
	private static final long serialVersionUID = 5119385230762345237L;
	
	private CSabot controleur;
	
	private PTasDeCartes hiddenDeck;
	private PTasDeCartes visibleDeck;
	
	public PSabot(CSabot cSabot, PTasDeCartes hiddenDeck, PTasDeCartes visibleDeck) {
		this.controleur = cSabot;
		this.hiddenDeck = hiddenDeck;
		this.visibleDeck = visibleDeck;
		
		// Layout
		this.initLayout();
		
		// Dimension
		this.setBackground(Color.GRAY);
		this.setSize(2*PCarte.largeur+40, PCarte.hauteur+10);
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
		this.setLayout(new FlowLayout());
		this.add(hiddenDeck);
		this.add(visibleDeck);
	}
	
} 
