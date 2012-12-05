package solitaire.presentation;

import java.awt.Color;

import solitaire.controle.CTasAlterne;
import solitaire.controle.ICTas;

public class PTasAlterne extends PTasDeCartes implements IPDropTarget{
	
	private static final long serialVersionUID = -2298012852387398285L;
	
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
		this.initDrag();
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
	
}
