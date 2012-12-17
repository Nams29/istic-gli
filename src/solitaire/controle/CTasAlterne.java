package solitaire.controle;

import java.awt.Dimension;

import javax.swing.JFrame;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesAlternees;
import solitaire.application.Usine;
import solitaire.presentation.PTasAlterne;
import solitaire.util.Observer;
import solitaire.util.Subject;

public class CTasAlterne extends TasDeCartesAlternees implements ICTas, ICDragSource, Subject {

	private PTasAlterne presentation;
	
	private Observer observer;

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
	
	/**
	 * Return the index of a card in the deck
	 * @param c The card
	 * @return The index of the card
	 */
	public int nombre(CCarte c){
		int nbcarteTas=this.getNombre();

		if(c!=null){
			for(int i=1; i<=nbcarteTas;i++){
				try {
					//System.out.println("i:"+i+ "| carte:" + getCarte(i).getValeur());
					if(getCarte(i)==c){
						return i;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	@Override
	public void p2cDragEnter(ICTas tas) {
		if(this.isEmpilable(tas)){
			this.presentation.c2pDropPossible();
		}
		else{
			this.presentation.c2pDropImpossible();
		}
	}
	
	@Override
	public void p2cDragExit(ICTas tas) {
		this.presentation.c2pDragExit();
	}

	@Override
	public void p2cDrop(ICTas tas) {
		try {			
			if (this.isEmpilable(tas)) {
				this.empiler(tas);
				this.presentation.c2pDropOK();
			}
			else{
				this.presentation.c2pDropFailed();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void p2cDragGestureRecognized(CCarte carte) {
		try {
			// Si on essaye bien de dépiler la carte du sommet
			CTasDeCartes draggedDeck = new CTasDeCartes("draggedDeck", new CUsine());
			draggedDeck.getPresentation().setDxDy(0, 30);
			
			// Si c'est un drag avec une seul carte
			if (carte == (CCarte) this.getSommet()) {
				this.depiler();
				// Envoi du deck drag à la présentation
				draggedDeck.empiler(carte);
				this.presentation.c2pDragGestureAccepted(draggedDeck.getPresentation());
			}
			//Si c'est un drag d'un tas
			else{			
				//on split le tas alternée en deux tas
				int numCarte = this.nombre(carte) ;
				
				//on copie les cartes dans le nouveau tas
				for(int i=numCarte; i>=1; i--){
					draggedDeck.empiler(this.getCarte(i));
				}
				
				//on supprime les cartes du tas
				for(int i=numCarte; i>=1; i-- ){
					this.depiler();
				}
				
				this.presentation.c2pDragGestureAccepted(draggedDeck.getPresentation());
			}
		} catch (Exception e) {
			System.out.println("Erreur lors du drag sabot : "+e.getMessage());
		}
		
	}
	
	@Override
	public void p2cDragSuccess(ICTas tas) {
		this.notifyObservers();
	}
	
	@Override
	public void p2cDragFail(ICTas icTas) {
		try {
			this.empiler(icTas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setObserver(Observer o) {
		this.observer = o;
	}

	@Override
	public void notifyObservers() {
		this.observer.update();
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
