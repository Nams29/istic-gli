package solitaire.presentation;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

import solitaire.controle.CCarte;
import solitaire.controle.CTasColore;
import solitaire.controle.ICTas;
import solitaire.listener.DragDropEndListener;

public class PTasColore extends PTasDeCartes implements IPDropTarget {

	private static final long serialVersionUID = 3620161955979902278L;
	
	// Drag and Drop
	private DragGestureEvent dragEvent;
	private DragSource dragSource;
	private DragSourceListener dragSourceListener;
	private DragGestureListener dragGestureListener;
	private DragSourceMotionListener dragSourceMotionListener;
	
	private JWindow dragContainer;
	private PTasDeCartes dragDeck;
	
	// Background
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
		// Listeners
		this.dragSourceListener = new TasColoreDragSourceListener();
		this.dragGestureListener = new TasColoreDragGestureListener();
		this.dragSourceMotionListener = new TasColoreDragSourceMotionListener();
		this.setDropTargetActive(true);
		
		// Source
		this.dragSource = new DragSource();
		this.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this.dragGestureListener);
		this.dragSource.addDragSourceMotionListener(this.dragSourceMotionListener);
	}
	
	/**
	 * Called by the controler when a drag gesture is accepted
	 * @param tas The dragged deck to display
	 */
	public void c2pDragGestureAccepted(PTasDeCartes tas) {
		this.dragDeck = tas;
		
		// Add the deck to the drag container
		this.dragContainer = new JWindow((Frame) this.getRootPane().getParent());
		this.dragContainer.add(tas);
		this.dragContainer.pack();
		
		this.dragSource.startDrag(dragEvent, Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), dragDeck, dragSourceListener);
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
	
	@Override
	public void paintComponent(Graphics g) {
	     g.drawImage(image, 0, 0, null);
	}
	
	/**
	 * Class TasColoreDragSourceListener
	 * Listen the drop events on the colored deck
	 */
	private class TasColoreDragSourceListener extends DragDropEndListener {

		@Override
		public void dragDropEnd(DragSourceDropEvent dsde) {
			if (!dsde.getDropSuccess()) {	
				//(PCarte)dsde.getSource();
				((CTasColore)controleur).p2cDragFails(dragDeck.getController());	
			}
			dragContainer.remove(dragDeck);
			dragContainer.setVisible(false);
		}
		
	}
	
	/**
	 * Class TasColoreDragGestureListener
	 * Listen the drag gestures on the colored deck
	 */
	private class TasColoreDragGestureListener implements DragGestureListener {

		@Override
		public void dragGestureRecognized(DragGestureEvent dge) {
			dragEvent = dge;
			
			PTasDeCartes deck = (PTasDeCartes) dge.getComponent();
			
			//Vérification qu'on essaye bien de draguer une carte
			if(deck.getComponentAt(dge.getDragOrigin()).getClass().toString().contains("PCarte")){		
				PCarte card = (PCarte) deck.getComponentAt(dge.getDragOrigin());
				((CTasColore)controleur).p2cDragGestureRecognized(card.getControle());
			}
		}
		
	}
	
	/**
	 * Class TasColoreDragSourceMotionListener
	 */
	private class TasColoreDragSourceMotionListener implements DragSourceMotionListener {

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
