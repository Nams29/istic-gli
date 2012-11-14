package solitaire.presentation;

import java.awt.Color;

import javax.swing.JPanel;

import solitaire.controle.CTasDeCartes;

public class PTasDeCartes extends JPanel {
	
	private static final long serialVersionUID = -1583784803552208119L;
	
	private CTasDeCartes controleur;
	
	private int dx, dy;		// Shift between each cards
	private int cx, cy;		// Current position of the last card
	
	public PTasDeCartes(CTasDeCartes c) {
		this.controleur = c;
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(PCarte.largeur+10, PCarte.hauteur+10);
		this.setPreferredSize(getSize());
		this.setLayout(null);
	}
	
	/**
	 * Remove a card from the stack
	 * @param p the card to remove 
	 */
	public void depiler(PCarte p) {
		this.remove(p);
		this.cx = this.cx - this.dx;
		this.cy = this.cy - this.dy;
	}
	
	/**
	 * Add a card to the stack
	 * @param p the card to add
	 */
	public void empiler(PCarte p) {
		this.add(p, 0);
		this.cx = this.cx + this.dx;
		this.cy = this.cy + this.dy;
		p.setLocation(this.cx, this.cy);
		p.setVisible(true);
		this.setSize(this.getWidth()+dx, this.getHeight()+dy);
	}
	
	/**
	 * Set the location of the stack
	 * @param dx the horizontal location
	 * @param dy the vertical location
	 */
	public void setDxDy(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
}
