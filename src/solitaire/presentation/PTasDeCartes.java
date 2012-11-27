package solitaire.presentation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import solitaire.application.Carte;
import solitaire.controle.CCarte;
import solitaire.controle.CTasDeCartes;
import solitaire.controle.ICTas;

public class PTasDeCartes extends JPanel {

	private static final long serialVersionUID = -1583784803552208119L;

	protected ICTas controleur;

	private int dx, dy;		// Shift between each cards
	private int cx, cy;		// Current position of the last card

	public PTasDeCartes(ICTas c) {
		this.controleur = c;

		this.initLayout();
	}

	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		this.setBackground(Color.LIGHT_GRAY);

		this.setLayout(null);
	}
	
	/**
	 * Add a card to the stack
	 * @param p the card to add
	 */
	public void empiler(PCarte p) {
		Dimension dimension = new Dimension((this.controleur.getNombre()-1)*this.dx+PCarte.largeur, 
				(this.controleur.getNombre()-1)*this.dy+PCarte.hauteur);
		this.setSize(dimension);
		this.setPreferredSize(dimension);

		if (this.controleur.getNombre() > 1) {
			this.cx = this.cx + this.dx;
			this.cy = this.cy + this.dy;
		}
		else {
			this.cx = 0;
			this.cy = 0;
		}

		this.add(p, 0);
		p.setLocation(this.cx, this.cy);


		this.validate();
		this.repaint();
	}
	
	/**
	 * Remove a card from the stack
	 * @param p the card to remove 
	 */
	public void depiler(PCarte p) {
		this.remove(p);
		
		Dimension dimension;
		if (this.controleur.getNombre() > 1) {
			dimension = new Dimension(this.getWidth()-this.dx, 
									  this.getHeight()-this.dy);
		}
		else {
			dimension = new Dimension(PCarte.largeur, 
					  				  PCarte.hauteur);
		}
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
	
	/**
	 * Class FocusCarteListener
	 */
	public class FocusCarteListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) { }

		@Override
		public void mouseMoved(MouseEvent e) {
			try {
				Carte c = ((CTasDeCartes)controleur).getSommet();
				PCarte p = ((CCarte) c).getPresentation();
				p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
