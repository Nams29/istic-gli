/*
 * Created on 31 janv. 2005
 *
 */
package solitaire.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import solitaire.application.TasDeCartes;
import solitaire.controle.CCarte;
import solitaire.controle.CColonne;
import solitaire.controle.CSabot;
import solitaire.controle.CTasAlterne;
import solitaire.controle.CTasDeCartes;
import solitaire.controle.CUsine;
import solitaire.presentation.PCarte;
import solitaire.presentation.PTasDeCartes;

/**
 * @author engel test des diff�rents objets visuel
 */
public class Test extends JFrame {
	private static final long serialVersionUID = 7243944635891289052L;

	public static void main(String[] args) {
		Test jt = new Test();

		//testCarte(jt, "Test Carte");
		//testTasDeCartes(jt, "Test TasDeCartes");
		//testSabot(jt, "Test Sabot");
		//testTasAlterne(jt, "Test TasAlterne");
		testColonne(jt, "Test Colonne");
		
		// taille de la fen�tre
		jt.pack(); 

		// centrer la fen�tre dans l'écran
		Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
		Point origine = new Point((dimEcran.width - jt.getWidth())/2,
				(dimEcran.height - jt.getHeight())/2);
		jt.setLocation(origine.x, origine.y);
		jt.setVisible(true);
	}

	/**
	 * constructeur ; initialise le panneau interne
	 */
	public Test() {

		// fermeture
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// panneau du plan de jeu
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(163, 163, 215)); // mauve p�le
	}
	/**
	 * test des Cartes
	 * 
	 * @param fenetre o� afficher les cartes
	 * @param titre de la fen�tre ci-dessus
	 */
	public static void testCarte(Test fenetre, String titre) {
		fenetre.setTitle(titre);
		
		CUsine usine = new CUsine();
		
		// une carte visible
		CCarte carte1 = (CCarte) usine.newCarte(7, 4);
		PCarte pres1 = carte1.getPresentation();
		carte1.setFaceVisible(true);
		pres1.setLocation(20, 20);
		fenetre.getContentPane().add(pres1) ;

		// une carte cach�e
		CCarte carte2 = (CCarte) usine.newCarte(1, 1);
		PCarte pres2 = carte2.getPresentation();
		carte2.setFaceVisible(false);
		pres2.setLocation(40 + PCarte.largeur, 20);
		fenetre.getContentPane().add(pres2) ;

		// taille du conteneur
		Dimension d = new Dimension(2 * PCarte.largeur + 60, PCarte.hauteur + 40);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}
	
	/**
	 * test des TasDeCartes
	 * 
	 * @param fenetre où afficher les cartes
	 * @param titre de la fenêtre ci-dessus
	 */
	public static void testTasDeCartes(Test fenetre, String titre) {
		fenetre.setTitle(titre);
		
		CUsine usine = new CUsine();
		
		// deux cartes cachées, une carte visible verticales
		CTasDeCartes tas1 = (CTasDeCartes) usine.newTasDeCartes("test_cache", usine);
		PTasDeCartes pres1 = tas1.getPresentation();
		CCarte carte = (CCarte) usine.newCarte(1, 1);
		carte.setFaceVisible(true);
		pres1.setDxDy(0, 10);
		tas1.empiler(usine.newCarte(8, 2));
		tas1.empiler(usine.newCarte(1, 3));
		tas1.empiler(usine.newCarte(12, 1));
		tas1.empiler(carte);
		pres1.setLocation(20, 20);
		fenetre.getContentPane().add(pres1) ;
		
		// trois cartes visibles horizontales
		CTasDeCartes tas2 = (CTasDeCartes) usine.newTasDeCartes("test_visible", usine);
		PTasDeCartes pres2 = tas2.getPresentation();
		pres2.setDxDy(15, 0);
		CCarte carte1 = (CCarte) usine.newCarte(1, 1);
		CCarte carte2 = (CCarte) usine.newCarte(11, 3);
		CCarte carte3 = (CCarte) usine.newCarte(4, 4);
		carte1.setFaceVisible(true);
		carte2.setFaceVisible(true);
		carte3.setFaceVisible(true);
		tas2.empiler(carte1);
		tas2.empiler(carte2);
		tas2.empiler(carte3);
		// Test depiler
		tas2.empiler(usine.newCarte(9, 2));
		tas2.depiler();
		pres2.setLocation(40 + PCarte.largeur, 20);
		fenetre.getContentPane().add(pres2) ;
		
		// taille du conteneur
		Dimension d = new Dimension(300, 200);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}
	
	public static void testSabot(Test fenetre, String titre) {
		fenetre.setTitle(titre);
		
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
		
		fenetre.getContentPane().add(sabot.getPresentation());
		
		// taille du conteneur
		Dimension d = new Dimension(2 * PCarte.largeur + 70, PCarte.hauteur + 45);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}
	
	public static void testTasAlterne(Test fenetre, String title) {
		fenetre.setTitle(title);
		
		CUsine usine = new CUsine();
		
		// Creation tas alterné
		CTasAlterne tasAlt = (CTasAlterne) usine.newTasDeCartesAlternees("tas alterné test", usine);
		
		// Cartes
		tasAlt.empiler(usine.newCarte(13, 1));
		tasAlt.empiler(usine.newCarte(12, 2));
		tasAlt.empiler(usine.newCarte(11, 3));
		tasAlt.empiler(usine.newCarte(10, 4));
		
		fenetre.getContentPane().add(tasAlt.getPresentation());
		
		// taille du conteneur
		Dimension d = new Dimension(300, 200);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}
	
	public static void testColonne(Test fenetre, String title) {
		fenetre.setTitle(title);
		
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
		
		//col.retournerCarte();
		
		fenetre.getContentPane().add(col.getPresentation());
		
		// taille du conteneur
		Dimension d = new Dimension(300, 200);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}

}
