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
	
	public static int GAME_MODE = 1;

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
	 * Start a new game
	 */
	public void newGame() {
		System.out.println("New game");
	}
	
	/**
	 * Start a new game
	 */
	public void options() {
		System.out.println("Options");
	}
	
	/**
	 * Start a new game
	 */
	public void propos() {
		System.out.println("À propos");
	}
	
	/**
	 * Exit the game
	 */
	public void exit() {
		System.exit(0);
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
		
		Dimension dim = new Dimension(600, 650);
		frame.setMinimumSize(dim);
		frame.setSize(dim);
		frame.setVisible(true);
		frame.setLocationRelativeTo(frame.getParent());

		solitaire.run();
	}
}
