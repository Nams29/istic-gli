package solitaire.presentation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;

import javax.swing.JWindow;

import solitaire.controle.CTasAlterne;
import solitaire.controle.ICTas;

public class PTasAlterne extends PTasDeCartes implements IPDropTarget{
	
	private static final long serialVersionUID = -2298012852387398285L;
	
	
	// Drag and Drop
	private DragGestureEvent dragEvent;
	private DragSource dragSource;
	private DragSourceListener dragSourceListener;
	private DragGestureListener dragGestureListener;
	private DragSourceMotionListener dragSourceMotionListener;
	
	private JWindow dragContainer;
	private PTasDeCartes dragDeck;
	
	
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
		initDragSource();
	}
	
	/**
	 * Initiate drag and drop elements
	 */
	private void initDragSource() {
		// Listeners
		this.dragSourceListener = new TasAlterneeDragSourceListener();
		this.dragGestureListener = new TasAlterneeDragGestureListener();
		this.dragSourceMotionListener = new TasAlterneeDragSourceMotionListener();
		
		// Source
		this.dragSource = new DragSource();
		this.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this.dragGestureListener);
		this.dragSource.addDragSourceMotionListener(this.dragSourceMotionListener);
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
	private class TasAlterneeDragSourceListener implements DragSourceListener {

		@Override
		public void dragDropEnd(DragSourceDropEvent dsde) {
			
			if (!dsde.getDropSuccess()) {	
				//(PCarte)dsde.getSource();
				((CTasAlterne) controleur).p2cDragFails(dragDeck.getController());	
			}
			dragContainer.remove(dragDeck);
			dragContainer.setVisible(false);
		}

		@Override
		public void dragEnter(DragSourceDragEvent dsde) { }

		@Override
		public void dragExit(DragSourceEvent dse) { }

		@Override
		public void dragOver(DragSourceDragEvent dsde) { }

		@Override
		public void dropActionChanged(DragSourceDragEvent dsde) { }
		
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
			
			//Vérification qu'on essaye bien de draguer une carte
			
			//System.out.println(deck.getComponentAt(dge.getDragOrigin()).getClass().toString());
			
			if(deck.getComponentAt(dge.getDragOrigin()).getClass().toString().contains("PCarte")){	
				PCarte card = (PCarte) deck.getComponentAt(dge.getDragOrigin());
				((CTasAlterne) controleur).p2cDragGestureRecognized(card.getControle());
			}
			else{
				System.out.println(deck.getComponentAt(dge.getDragOrigin()).getClass().toString());
			}
		}
		
	}
	
	/**
	 * Class SabotDragSourceMotionListener
	 */
	private class TasAlterneeDragSourceMotionListener implements DragSourceMotionListener {

		@Override
		public void dragMouseMoved(DragSourceDragEvent dsde) {
			/* La position que ce serait bien de donner à la souris, 
			 * mais on peut pas parce qu'il faut être à l'extérieur de la carte */
			//dragContainer.setLocation(dsde.getX()-(dragContainer.getWidth()/2), dsde.getY()-(dragContainer.getHeight()/2));
			
			dragContainer.setLocation(dsde.getX()-(dragContainer.getWidth()/2), dsde.getY()+1);
			dragContainer.setVisible(true);
		}
		
	}
	
	
	
}
