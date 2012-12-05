package solitaire.controle;

import java.awt.Dimension;

import javax.swing.JFrame;

import solitaire.application.Sabot;
import solitaire.application.TasDeCartes;
import solitaire.application.Usine;
import solitaire.presentation.PCarte;
import solitaire.presentation.PSabot;
import solitaire.presentation.PTasDeCartes;

public class CSabot extends Sabot {
		
	private PSabot presentation;
	
	private CTasDeCartes hiddenDeck;
	private CTasDeCartes visibleDeck;
	
	public CSabot(String nom, Usine usine) {
		super(nom, usine);
		this.hiddenDeck = (CTasDeCartes) this.cachees;
		this.visibleDeck = (CTasDeCartes) this.visibles;
		this.presentation = new PSabot(this, hiddenDeck.getPresentation(), visibleDeck.getPresentation());
	}
	
	@Override
	public void depiler() throws Exception {
		if (!isVide()) {
			super.depiler();
		}
	}

	@Override
	public void retourner() throws Exception {
		if (isRetournable()) {
			super.retourner();
			this.presentation.setSize();
		}
	}

	@Override
	public void retournerCarte() throws Exception {
		if (isCarteRetournable()) {
			super.retournerCarte();
			this.presentation.setSize();
		}
	}
	
	/**
	 * React to a click on the hidden deck
	 */
	public void p2cHiddenDeckClick() {
		try {
			if (isRetournable()) {
				this.retourner();
			}
			else {
				for (int i=0; i<CSolitaire.GAME_MODE; i++) {
					this.retournerCarte();
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur en retournant une carte : "+e.getMessage());
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
	 * @return the presentation
	 */
	public PSabot getPresentation() {
		return this.presentation;
	}
	
	/**
	 * @return the hiddenDeck
	 */
	public CTasDeCartes getHiddenDeck() {
		return hiddenDeck;
	}
	
	/**
	 * @return the visibleDeck
	 */
	public CTasDeCartes getVisibleDeck() {
		return visibleDeck;
	}
	
	/**
	 * TEST 
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Sabot");

		CUsine usine = new CUsine();

		// creation sabot
		CSabot sabot = (CSabot) usine.newSabot("sabot", usine);
		
		// Tas de cartes
		TasDeCartes tas = usine.newTasDeCartes("sabot test", usine);
		tas.empiler(usine.newCarte(11, 3));
		tas.empiler(usine.newCarte(7, 1));
		tas.empiler(usine.newCarte(5, 1));
		tas.empiler(usine.newCarte(1, 4));
		tas.empiler(usine.newCarte(9, 2));
		sabot.setReserve(tas);
		
		frame.getContentPane().add(sabot.getPresentation());

		// taille du conteneur
		Dimension d = new Dimension(2 * PCarte.largeur + 80, PCarte.hauteur + 50);
		frame.setSize(d);
		frame.setPreferredSize(d);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void p2cDragFails(ICTas icTas) {
		// TODO Auto-generated method stub
		try {
			visibleDeck.empiler(icTas.getSommet());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
