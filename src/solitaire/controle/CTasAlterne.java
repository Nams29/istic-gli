package solitaire.controle;

import java.awt.Dimension;

import javax.swing.JFrame;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesAlternees;
import solitaire.application.Usine;
import solitaire.presentation.PTasAlterne;

public class CTasAlterne extends TasDeCartesAlternees implements ICTas {
	
	private PTasAlterne presentation; 
	
	public CTasAlterne(String nom, Usine usine) {
		super(nom, usine);
		this.presentation = new PTasAlterne(this);
	}
	
	/**
	 * @return the presentation
	 */
	public PTasAlterne getPresentation() {
		return this.presentation;
	}
	
	@Override
	public void empiler(Carte c) {
		//if (this.isEmpilable(c))  {
			super.empiler(c);
			this.presentation.empiler(((CCarte)c).getPresentation());
		//}
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
	 * TEST 
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Tas De Carte Alternées");

		CUsine usine = new CUsine();

		// Creation tas alterné
		CTasAlterne tasAlt = (CTasAlterne) usine.newTasDeCartesAlternees("tas alterné test", usine);
		tasAlt.getPresentation().setDxDy(0, 30);
		
		// Cartes
		tasAlt.empiler(usine.newCarte(13, 1));
		tasAlt.empiler(usine.newCarte(12, 2));
		tasAlt.empiler(usine.newCarte(11, 3));
		tasAlt.empiler(usine.newCarte(10, 4));
		
		frame.getContentPane().add(tasAlt.getPresentation());

		// taille du conteneur
		Dimension d = new Dimension(200, 300);
		frame.setSize(d);
		frame.setPreferredSize(d);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
