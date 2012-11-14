package solitaire.presentation ;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import solitaire.controle.CColonne;
import solitaire.controle.CSabot;

/**
 * Composant Présentation d'une carte
 */
public class PColonne extends JPanel {
	
	private static final long serialVersionUID = 5119385230762345237L;
	
	
	private PTasDeCartes cache;
	private PTasDeCartes visible;
	private CColonne controleur;
	
	public PColonne(CColonne cColonne, PTasDeCartes tas1,
			PTasDeCartes tas2) { 
		
		
		this.controleur = cColonne;
		
		// Dimension
		this.setBackground(Color.GRAY);
		this.setSize(2*PCarte.largeur+40, PCarte.hauteur+10);
		this.setPreferredSize(getSize());
		
		
		
		this.controleur = cColonne;
		this.cache = tas1;
		this.visible = tas2;
		
		// Tas de cartes
		this.cache.setDxDy(0, 15);
		this.visible.setDxDy(0, 15);
		
		// Affichage
		this.setLayout(new BorderLayout());
		this.add(cache, BorderLayout.WEST);
		this.visible.setBackground(Color.PINK);
		this.add(visible, BorderLayout.CENTER);
		
		// Dimension
		this.setBackground(Color.GRAY);
		this.setSize(2*PCarte.largeur+40, PCarte.hauteur+10);
		this.setPreferredSize(getSize());
		
	}
		
} 
