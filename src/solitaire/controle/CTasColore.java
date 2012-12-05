package solitaire.controle;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesColorees;
import solitaire.application.Usine;
import solitaire.presentation.PTasColore;

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

	@Override
	public void p2cDragEnter(ICTas tas) {
		// For colored deck, one card at a time
		if (tas.getNombre() == 1) {
			
			try {
				Carte card = tas.getSommet();
				
				if (this.isEmpilable(card)) {
					this.presentation.c2pDropPossible();
				}
				else {
					this.presentation.c2pDropImpossible();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {
			this.presentation.c2pDropImpossible();
		}
	}

	@Override
	public void p2cDragExit(ICTas tas) {
		this.presentation.c2pDragExit();
	}

	@Override
	public void p2cDrop(ICTas tas) {
		// For colored deck, one card at a time
		if (tas.getNombre() == 1) {
			
			try {
				Carte card = tas.getSommet();
				
				if (this.isEmpilable(card)) {
					this.empiler(card);
					this.presentation.c2pDropOK();
				}
				else {
					this.presentation.c2pDropFailed();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			this.presentation.c2pDropFailed();
		}
	}
	
	/**
	 * Called when a drag gesture is done on the presentation
	 * @param carte the controller of the card dragged
	 */
	public void p2cDragGestureRecognized(CCarte carte) {
		try {
			// Si on essaye bien de dépiler la carte du sommet
			if (carte == (CCarte) this.getSommet()) {
				this.depiler();
				
				// Envoi du deck drag à la présentation
				CTasDeCartes draggedDeck = new CTasDeCartes("draggedDeck", new CUsine());
				draggedDeck.empiler(carte);
				this.presentation.c2pDragGestureAccepted(draggedDeck.getPresentation());
			}
		} catch (Exception e) {
			System.out.println("Erreur lors du drag sabot : "+e.getMessage());
		}
	}
	
	/**
	 * Called when the drag and drop failed
	 * @param icTas
	 */
	public void p2cDragFails(ICTas icTas) {
		try {
			this.empiler(icTas.getSommet());
		} catch (Exception e) {
			e.printStackTrace();
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
