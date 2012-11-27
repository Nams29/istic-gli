package solitaire.presentation;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import solitaire.controle.CCarte;
import solitaire.controle.CTasColore;
import solitaire.controle.ICTas;

public class PTasColore extends PTasDeCartes {

	private static final long serialVersionUID = 3620161955979902278L;

	private ImageIcon image;

	public PTasColore(ICTas c, int couleur) {
		super(c);
		this.controleur = (CTasColore) c;

		this.initLayout(couleur);
	}

	private void initLayout(int couleur) {
		String chaine = "cartesCSHD/0"+CCarte.getCouleur(couleur-1)+".gif";
		image = new ImageIcon(ClassLoader.getSystemResource(chaine));
		
		JLabel fond = new JLabel(image);
		this.add(fond);
		
		Dimension size = new Dimension(PCarte.largeur, PCarte.hauteur);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
		
		this.setLayout(new GridLayout(1, 0));
	}

}
