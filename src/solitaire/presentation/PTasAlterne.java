package solitaire.presentation;

import java.awt.Color;
import java.awt.Dimension;

import solitaire.controle.CTasAlterne;
import solitaire.controle.ICTas;

public class PTasAlterne extends PTasDeCartes {
	
	private static final long serialVersionUID = -2298012852387398285L;
	
	private int cx, cy;		// Current position of the last card
	
	public PTasAlterne(ICTas c) {
		super(c);
		this.controleur = (CTasAlterne) c;
		
		this.initLayout();
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		this.setBackground(Color.PINK);
		
		this.setLayout(null);
	}
	
}
