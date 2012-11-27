package solitaire.controle;

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
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(frame.getParent());

		solitaire.run();
	}
}
