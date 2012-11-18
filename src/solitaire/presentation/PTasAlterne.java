package solitaire.presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import solitaire.controle.CTasAlterne;

public class PTasAlterne extends JPanel {
	
	private static final long serialVersionUID = -2298012852387398285L;
	
	private CTasAlterne controleur;
	
	private Dimension minSize = new Dimension(PCarte.largeur, PCarte.hauteur);
	
	private int dx, dy;		// Shift between each cards
	private int cx, cy;		// Current position of the last card
	
	public PTasAlterne(CTasAlterne c) {
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
		
		this.setLayout(null);
	}
	
	/**
	 * Add a card to the top of the deck
	 * @param p The card to add
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
	 * Remove a card of the deck
	 * @param p The card to remove
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
	 * Set the location of the stack
	 * @param dx the horizontal location
	 * @param dy the vertical location
	 */
	public void setDxDy(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
}
