package solitaire.controle;

import java.awt.Dimension;

import javax.swing.JFrame;

import solitaire.application.Carte;
import solitaire.application.Colonne;
import solitaire.application.Tas;
import solitaire.application.Usine;
import solitaire.presentation.PColonne;
import solitaire.presentation.PTasAlterne;
import solitaire.presentation.PTasDeCartes;
import solitaire.util.Observer;

public class CColonne extends Colonne implements Observer {
	
	private PColonne presentation;
	
	private CTasDeCartes hiddenDeck;
	private CTasAlterne alternateDeck;
	
	public CColonne(String nom, Usine usine) {
		super(nom, usine);
		this.hiddenDeck = (CTasDeCartes) this.cachees;
		this.alternateDeck = (CTasAlterne) this.visibles;
		this.presentation = new PColonne(this, hiddenDeck.getPresentation(), 
				((PTasAlterne) this.alternateDeck.getPresentation()));
		
		this.alternateDeck.setObserver(this);
	}
	
	@Override
	public void retournerCarte() {
		if (isCarteRetournable()) {
			try {
				super.retournerCarte();
				this.presentation.c2pFlipCard();
			} catch (Exception e) {
				System.out.println("Erreur en retournant une carte : "+e.getMessage());
			}
		}
	}
	
	@Override
	public void empiler(Carte c) {
		if (this.isEmpilable(c)) {
			super.empiler(c);
			this.presentation.c2pFlipCard();
		}
	}
	
	@Override
	public void empiler(Tas tas) {
		if (this.isEmpilable(tas)) {
			super.empiler(tas);
			this.presentation.c2pFlipCard();
		}
	}
	
	@Override
	public void empiler(Tas tas, int n) {
		if (this.isEmpilable(tas, n)) {
			super.empiler(tas, n);
			this.presentation.c2pFlipCard();
		}
	}
	
	@Override
	public void depiler() {
		if (!this.isVide()) {
			try {
				super.depiler();
			} catch (Exception e) {
				System.out.println("Erreur en dépilant : "+e.getMessage());
			}
		}
	}
	
	@Override
	public void setReserve(Tas tas) {
		super.setReserve(tas);
		this.presentation.c2pFlipCard();
	}
	
	/**
	 * React to a click on the hidden deck
	 */
	public void p2cHiddenDeckClick() {
		this.retournerCarte();
	}
	
	@Override
	public void update() {
		this.retournerCarte();
	}
	
	/**
	 * Return the presentation
	 * @return the presentation
	 */
	public PColonne getPresentation() {
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
	public CTasAlterne getAlternateDeck() {
		return alternateDeck;
	}
	
	/**
	 * @return the number of hidden cards
	 */
	public int getNombreCache() {
		return hiddenDeck.getNombre();
	}
	
	/**
	 * TEST 
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Tas De Carte Alternées");

		CUsine usine = new CUsine();

		// Creation colonne
		CColonne col = (CColonne) usine.newColonne("colonne test", usine);
			CTasDeCartes tas1 = (CTasDeCartes) usine.newTasDeCartes("caché", usine);
			PTasDeCartes pres1 = tas1.getPresentation();
			pres1.setDxDy(0, 10);
			tas1.empiler(usine.newCarte(8, 2));
			tas1.empiler(usine.newCarte(1, 3));
			tas1.empiler(usine.newCarte(12, 1));
			tas1.empiler(usine.newCarte(7, 1));
		col.setReserve(tas1);
				
		frame.getContentPane().add(col.getPresentation());
		
		// taille du conteneur
		Dimension d = new Dimension(200, 300);
		frame.setSize(d);
		frame.setPreferredSize(d);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
