package solitaire;

import solitaire.controle.CSolitaire;
import solitaire.controle.CUsine;

public class CSolitaireMain {
	
	private static CSolitaire solitaire;
	
	private PSolitaireMain presentation;
	
	private Thread gameThread;
	
	/**
	 * @param args
	 */
	public void startGame() {
		solitaire = new CSolitaire("Solitaire", new CUsine());
		
		solitaire.initialiser();
		
		if (presentation == null) {
			presentation = new PSolitaireMain(this);
		}
		presentation.displayGame(solitaire.getPresentation());
		
		this.gameThread = new Thread(solitaire);
		this.gameThread.start();
	}
	
	/**
	 * Create a new game
	 */
	@SuppressWarnings("deprecation")
	public void newGame() {
		this.gameThread.stop();
		this.startGame();
	}
	
	/**
	 * Exit the game
	 */
	public void exit() {
		System.exit(0);
	}
	
	/**
	 * Display the informations panel
	 */
	public void propos() {
		this.presentation.c2pPropos();
	}
	
	/**
	 * Change the number of cards flipped on the sabot
	 * @param n
	 */
	public void setNumberCards(int n) {
		startGame();
		solitaire.setNumberCards(n);
	}
	
	/**
	 * MAIN
	 */
	public static void main(String[] args) {
		CSolitaireMain main = new CSolitaireMain();
		
		main.startGame();
	}

}
