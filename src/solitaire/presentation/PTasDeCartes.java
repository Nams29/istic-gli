package solitaire.presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import solitaire.controle.CTasDeCartes;

public class PTasDeCartes extends JPanel {
	
	private static final long serialVersionUID = -1583784803552208119L;
	
	private CTasDeCartes controleur;
	
	private Dimension minSize = new Dimension(PCarte.largeur, PCarte.hauteur);
	
	private int dx, dy;		// Shift between each cards
	private int cx, cy;		// Current position of the last card
	
	public PTasDeCartes(CTasDeCartes c) {
		this.controleur = c;
		
		this.initLayout();
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		this.setBackground(Color.LIGHT_GRAY);
		
		this.setSize(minSize);
		this.setPreferredSize(minSize);
		this.setMinimumSize(minSize);
		
		this.setLayout(null);
	}
	
	/**
	 * Remove a card from the stack
	 * @param p the card to remove 
	 */
	public void depiler(PCarte p) {
		this.remove(p);
		
		Dimension dimension = new Dimension(this.getWidth()-this.dx, 
											this.getHeight()-this.dy);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		
		this.cx = this.cx - this.dx;
		this.cy = this.cy - this.dy;
		
		this.validate();
		this.repaint();
	}
	
	/**
	 * Add a card to the stack
	 * @param p the card to add
	 */
	public void empiler(PCarte p) {
		if (controleur.getNombre() > 1) {
			Dimension dimension = new Dimension(this.getWidth()+this.dx, 
												this.getHeight()+this.dy);
			this.setSize(dimension);
			this.setPreferredSize(dimension);
			
			this.cx = this.cx + this.dx;
			this.cy = this.cy + this.dy;
		}
		
		this.add(p, 0);
		p.setLocation(this.cx, this.cy);
		
		
		this.validate();
		this.repaint();
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
