package solitaire.presentation ;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import solitaire.controle.CSabot;

/**
 * Composant Pr�sentation d'une carte
 */
public class PSabot extends JPanel {
	
	private static final long serialVersionUID = 5119385230762345237L;
	
	private CSabot controleur;
	
	private PTasDeCartes cache;
	private PTasDeCartes visible;
	
	public PSabot(CSabot cSabot, PTasDeCartes tas1, PTasDeCartes tas2) {
		this.controleur = cSabot;
		this.cache = tas1;
		this.visible = tas2;
		
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
		this.cache.setDxDy(0, 0);
		this.visible.setDxDy(15, 0);
		this.visible.setBackground(Color.PINK);
		
		// Affichage
		this.setLayout(new BorderLayout());
		this.add(cache, BorderLayout.WEST);
		this.add(visible, BorderLayout.CENTER);
	}
	
	/**
	 * Add a card at the top of the hidden cards
	 * @param card the card to add
	 */
	public void empiler(PCarte card) {
		this.cache.add(card);
	}
	
} 
