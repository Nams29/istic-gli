package solitaire.presentation ;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;

import solitaire.controle.CSabot;
import solitaire.listener.MouseClickListener;

/**
 * Class PSabot
 * Presentation for the sabot
 * @author Nolwenn Stephan
 * @author Vianney Hubert
 */
public class PSabot extends JPanel {
	
	private static final long serialVersionUID = 5119385230762345237L;
	
	// Controller
	private CSabot controleur;
	
	// Drag and Drop
	private DragGestureEvent dragEvent;
	private DragSource dragSource;
	private DragSourceListener dragSourceListener;
	private DragGestureListener dragGestureListener;
	private DragSourceMotionListener dragSourceMotionListener;
	
	private JWindow dragContainer;
	private PTasDeCartes dragDeck;
	
	// Decks
	private PTasDeCartes hiddenDeck;
	private PTasDeCartes visibleDeck;
	
	// Display
	private final int INSET_X = 20;
	private final int INSET_Y = 0;
	
	/**
	 * Create a new PSabot
	 * @param cSabot The controller
	 * @param hiddenDeck The hidden deck presentation
	 * @param visibleDeck The visible deck presentation
	 */
	public PSabot(CSabot cSabot, PTasDeCartes hiddenDeck, PTasDeCartes visibleDeck) {
		this.controleur = cSabot;
		this.hiddenDeck = hiddenDeck;
		this.visibleDeck = visibleDeck;
		
		// Layout
		this.initLayout();
		
		// Drag and drop
		this.initDrag();
	}
	
	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		// Affichage
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Tas de cartes
		this.hiddenDeck.setDxDy(0, 0);
		this.visibleDeck.setDxDy(15, 0);
		this.add(hiddenDeck, BorderLayout.WEST);
		this.add(visibleDeck, BorderLayout.EAST);
		
		// Dimension
		Dimension dim = new Dimension (2*PCarte.largeur+INSET_X, PCarte.hauteur+INSET_Y);
		this.setSize(dim);
		this.setPreferredSize(dim);
		
		// Listener
		this.hiddenDeck.addMouseListener(new RetournerCarteListener());
	}
	
	/**
	 * Initiate drag and drop elements
	 */
	private void initDrag() {
		// Listeners
		this.dragSourceListener = new SabotDragSourceListener();
		this.dragGestureListener = new SabotDragGestureListener();
		this.dragSourceMotionListener = new SabotDragSourceMotionListener();
		
		// Source
		this.dragSource = new DragSource();
		this.dragSource.createDefaultDragGestureRecognizer(visibleDeck, DnDConstants.ACTION_COPY, this.dragGestureListener);
		this.dragSource.addDragSourceMotionListener(this.dragSourceMotionListener);
	}
	
	/**
	 * Set the size of the component
	 */
	public void setSize() {
		Dimension dim = new Dimension(
				this.hiddenDeck.getWidth()+this.visibleDeck.getWidth()+INSET_X, 
				this.getHeight());
		this.setSize(dim);
		this.setPreferredSize(dim);
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
	
	/**
	 * Class RetournerCarteListener
	 * Listen the clicks on the hidden deck
	 */
	private class RetournerCarteListener extends MouseClickListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			controleur.p2cHiddenDeckClick();
		}
		
	}
	
	/**
	 * Class SabotDragSourceListener
	 * Listen the drop events on the sabot
	 */
	private class SabotDragSourceListener implements DragSourceListener {

		@Override
		public void dragDropEnd(DragSourceDropEvent dsde) {
			if (!dsde.getDropSuccess()) {	
				//(PCarte)dsde.getSource();
				controleur.p2cDragFails(dragDeck.getController());	
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
	private class SabotDragGestureListener implements DragGestureListener {

		@Override
		public void dragGestureRecognized(DragGestureEvent dge) {
			dragEvent = dge;
			
			PTasDeCartes deck = (PTasDeCartes) dge.getComponent();
			
			//Vérification qu'on essaye bien de draguer une carte
			if(deck.getComponentAt(dge.getDragOrigin()).getClass().toString().contains("PCarte")){		
				PCarte card = (PCarte) deck.getComponentAt(dge.getDragOrigin());
				controleur.p2cDragGestureRecognized(card.getControle());
			}
		}
		
	}
	
	/**
	 * Class SabotDragSourceMotionListener
	 */
	private class SabotDragSourceMotionListener implements DragSourceMotionListener {

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
