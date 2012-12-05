package solitaire.presentation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import solitaire.controle.CCarte;
import solitaire.controle.CTasColore;
import solitaire.controle.ICTas;

public class PTasColore extends PTasDeCartes implements IPDropTarget {

	private static final long serialVersionUID = 3620161955979902278L;

	private Image image;

	public PTasColore(ICTas c, int couleur) {
		super(c);
		this.controleur = (CTasColore) c;

		this.initLayout(couleur);
		this.initDrag();
	}
	
	/**
	 * Initialize graphic components
	 * @param color the color of the deck
	 */
	private void initLayout(int color) {
		String chaine = "res/0"+CCarte.getCouleur(color-1)+".gif";
		image = new ImageIcon(chaine).getImage();
		
		Dimension size = new Dimension(PCarte.largeur, PCarte.hauteur);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
		
		this.setLayout(null);
	}
	
	/**
	 * Initialize drag and drop listener
	 */
	private void initDrag() {
		this.setDropTargetActive(true);
	}
	
	@Override
	public void c2pDropOK() {
		super.c2pDropOK();
	}

	@Override
	public void c2pDropFailed() {
		super.c2pDropFailed();
	}

	@Override
	public void c2pDropPossible() {
		super.c2pDropPossible();
	}

	@Override
	public void c2pDropImpossible() {
		super.c2pDropImpossible();
	}

	@Override
	public void c2pDragExit() {
		super.c2pDragExit();
	}
	
	@Override
	public void paintComponent(Graphics g) {
	     g.drawImage(image, 0, 0, null);
	}

}
