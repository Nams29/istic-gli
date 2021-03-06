package solitaire.presentation;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;

import javax.swing.JWindow;

import solitaire.controle.CTasAlterne;
import solitaire.controle.ICTas;
import solitaire.listener.DragDropEndListener;

public class PTasAlterne extends PTasDeCartes {
	
	private static final long serialVersionUID = -2298012852387398285L;
	
	// Drag and Drop
	private DragGestureEvent dragEvent;
	private DragSource dragSource;
	private DragSourceListener dragSourceListener;
	private DragGestureListener dragGestureListener;
	private DragSourceMotionListener dragSourceMotionListener;
	
	// Feedback panels
	private JWindow dragContainer;
	private PTasDeCartes dragDeck;
	
	public PTasAlterne(ICTas c) {
		super(c);
		this.controleur = (CTasAlterne) c;
		
		this.initLayout();
		this.initDrag();
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {		
		this.setLayout(null);
	}
	
	/**
	 * Initialize drag and drop listener
	 */
	private void initDrag() {
		// Listeners
		this.dragSourceListener = new TasAlterneeDragSourceListener();
		this.dragGestureListener = new TasAlterneeDragGestureListener();
		this.dragSourceMotionListener = new TasAlterneeDragSourceMotionListener();
		
		// Source
		this.dragSource = new DragSource();
		this.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this.dragGestureListener);
		this.dragSource.addDragSourceMotionListener(this.dragSourceMotionListener);
		
		this.setDropTargetActive(true);
	}
	
	@Override
	public void empiler(PCarte p) {
		super.empiler(p);
	}
	
	@Override
	public void depiler(PCarte p) {
		super.depiler(p);
	}
	
	@Override
	public void c2pDropOK() {
		super.c2pDropOK();
		
		this.remove(validPanel);
		this.remove(errorPanel);
		this.repaint();
	}

	@Override
	public void c2pDropFailed() {
		super.c2pDropFailed();
		
		this.remove(validPanel);
		this.remove(errorPanel);
		this.repaint();
	}

	@Override
	public void c2pDropPossible() {
		super.c2pDropPossible();
		
		if (!this.getController().isVide()) {
			this.add(validPanel, 0);
			this.repaint();
		}
	}

	@Override
	public void c2pDropImpossible() {
		super.c2pDropImpossible();
		if (!this.getController().isVide()) {
			this.add(errorPanel, 0);
			this.repaint();
		}
	}

	@Override
	public void c2pDragExit() {
		super.c2pDragExit();
		
		this.remove(validPanel);
		this.remove(errorPanel);
		this.repaint();
	}
	
	/**
	 * Called by the controler when a drag gesture is accepted
	 * @param tas The dragged deck to display
	 */
	public void c2pDragGestureAccepted(PTasDeCartes tas) {
		this.dragDeck = tas;
		this.dragDeck.setDropTargetActive(false);
		
		// Add the deck to the drag container
		this.dragContainer = new JWindow((Frame) this.getRootPane().getParent());

		this.dragContainer.add(tas);

		this.dragContainer.pack();
		
		this.dragSource.startDrag(dragEvent, Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), dragDeck, dragSourceListener);
	}
	
	/**
	 * Class SabotDragSourceListener
	 * Listen the drop events on the sabot
	 */
	private class TasAlterneeDragSourceListener extends DragDropEndListener {

		@Override
		public void dragDropEnd(DragSourceDropEvent dsde) {
			
			if (dsde.getDropSuccess()) {
				((CTasAlterne) controleur).p2cDragSuccess(dragDeck.getController());
			}
			else {
				((CTasAlterne) controleur).p2cDragFail(dragDeck.getController());
			}
			dragContainer.remove(dragDeck);
			dragContainer.setVisible(false);
		}
		
	}
	
	/**
	 * Class SabotDragGestureListener
	 * Listen the drag gestures on the sabot
	 */
	private class TasAlterneeDragGestureListener implements DragGestureListener {

		@Override
		public void dragGestureRecognized(DragGestureEvent dge) {
			dragEvent = dge;
			
			PTasDeCartes deck = (PTasDeCartes) dge.getComponent();		
			
			// Check if there is a card to drag
			if(deck.getComponentAt(dge.getDragOrigin()).getClass().toString().contains("PCarte")){	
				PCarte card = (PCarte) deck.getComponentAt(dge.getDragOrigin());
				((CTasAlterne) controleur).p2cDragGestureRecognized(card.getControle());
			}
		}
		
	}
	
	/**
	 * Class SabotDragSourceMotionListener
	 * Listen the mouse movements during the drag 
	 */
	private class TasAlterneeDragSourceMotionListener implements DragSourceMotionListener {

		@Override
		public void dragMouseMoved(DragSourceDragEvent dsde) {
			/* La position que ce serait bien de donner à la souris, 
			 * mais on peut pas parce que le curseur doit être à l'extérieur de la carte */
			//dragContainer.setLocation(dsde.getX()-(dragContainer.getWidth()/2), dsde.getY()-(dragContainer.getHeight()/2));
			
			dragContainer.setLocation(dsde.getX()-(dragContainer.getWidth()/2), dsde.getY()+1);
			dragContainer.setVisible(true);
		}
		
	}
	
}
