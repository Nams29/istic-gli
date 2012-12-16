package solitaire.controle;

import java.awt.Dimension;

import javax.swing.JFrame;

import solitaire.application.Colonne;
import solitaire.application.Solitaire;
import solitaire.application.TasDeCartesColorees;
import solitaire.application.Usine;
import solitaire.presentation.PSabot;
import solitaire.presentation.PSolitaire;
import solitaire.presentation.PTasColore;

public class CSolitaire extends Solitaire {

	private PSolitaire presentation;
	
	public static int GAME_MODE = 3;

	public CSolitaire(String nom, Usine usine) {
		super (nom, usine);
		this.presentation = new PSolitaire(this);
	}

	@Override
	public void initialiser() {
		super.initialiser();

		PSabot pSabot = ((CSabot) this.sabot).getPresentation();
		this.presentation.setSabot(pSabot);

		for (TasDeCartesColorees tas : this.pilesColorees) {
			PTasColore pTas = ((CTasColore) tas).getPresentation();
			this.presentation.addTasColore(pTas);
		}

		for (Colonne c : this.pilesAlternees) {
			this.presentation.addColonne(((CColonne) c).getPresentation());
		}
	}
	
	/**
	 * Change the number of cards flipped on the sabot
	 * @param n
	 */
	public void setNumberCards(int n) {
		GAME_MODE = n;
	}
	
	/**
	 * @return the presentation
	 */
	public PSolitaire getPresentation() {
		return this.presentation;
	}
	
	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		CSolitaire solitaire = new CSolitaire("CSolitaire", new CUsine());
		solitaire.initialiser();

		JFrame frame = new JFrame("Test CSolitaire");
		frame.getContentPane().add(solitaire.getPresentation());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setJMenuBar(solitaire.getPresentation().getMenuBar());
		
		Dimension dim = new Dimension(840, 650);
		frame.setMinimumSize(dim);
		frame.setSize(dim);
		frame.setVisible(true);
		frame.setLocationRelativeTo(frame.getParent());

		solitaire.run();
	}
}
