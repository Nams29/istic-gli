package solitaire.presentation;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.JPanel;

import solitaire.controle.ICTas;

/**
 * Class PTasDeCartes
 * Presentation for a deck
 * @author Nolwenn Stephan
 * @author Vianney Hubert
 */
public class PTasDeCartes extends JPanel implements Transferable, IPDropTarget {

	private static final long serialVersionUID = -1583784803552208119L;
	
	// Controller
	protected ICTas controleur;
	
	// Drag and drop
	private DropTarget dropTarget;
	private DropTargetDropEvent dropEvent;
	
	// Display
	private int dx, dy;		// Shift between each cards
	private int cx, cy;		// Current position of the last card
	private Dimension dim = new Dimension(PCarte.largeur, PCarte.hauteur);
	
	/**
	 * Create a new PTasDeCarte
	 * @param c The controller
	 */
	public PTasDeCartes(ICTas c) {
		this.controleur = c;

		this.initLayout();
		this.initDrag();
	}

	/**
	 * Initiate graphic elements
	 */
	private void initLayout() {
		// Taille
		this.setSize(dim);
		this.setPreferredSize(dim);
		
		// Layout
		this.setLayout(null);
		this.setBackground(PSolitaire.bg_color_light);
	}
	
	/**
	 * Initiate the drag and drop elements
	 */
	private void initDrag() {
		this.dropTarget = new DropTarget(this, new TasDropTargetListener());
		this.setDropTargetActive(false);
	}
	
	/**
	 * Add a card to the stack
	 * @param p the card to add
	 */
	public void empiler(PCarte p) {
		// New size of the deck
		Dimension dimension = new Dimension((this.controleur.getNombre()-1)*this.dx+PCarte.largeur, 
											(this.controleur.getNombre()-1)*this.dy+PCarte.hauteur);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		
		// Position of the new card
		if (this.controleur.getNombre() > 1) {
			this.cx = this.cx + this.dx;
			this.cy = this.cy + this.dy;
		}
		else {
			this.cx = 0;
			this.cy = 0;
		}
		
		// Adding the card
		this.add(p, 0);
		p.setLocation(this.cx, this.cy);
		
		// Update the display
		this.validate();
		this.repaint();
	}
	
	/**
	 * Remove a card from the stack
	 * @param p the card to remove 
	 */
	public void depiler(PCarte p) {
		// Removing the card
		this.remove(p);
		
		// New size of the deck
		Dimension dimension;
		if (this.controleur.getNombre() > 1) {
			dimension = new Dimension(this.getWidth()-this.dx, 
									  this.getHeight()-this.dy);
		}
		else {
			// If there is one or no card left, the deck size is a card size
			dimension = new Dimension(PCarte.largeur, 
					  				  PCarte.hauteur);
		}
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		
		// Position of the current front card
		this.cx = this.cx - this.dx;
		this.cy = this.cy - this.dy;
		
		// Update the display 
		this.validate();
		this.repaint();
	}
	
	@Override
	public void c2pDropOK() {
		dropEvent.dropComplete(true);
		this.repaint();
	}

	@Override
	public void c2pDropFailed() {
		this.repaint();
	}

	@Override
	public void c2pDropPossible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void c2pDropImpossible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void c2pDragExit() {
		// TODO Auto-generated method stub
		
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
	 * Return the controller
	 * @return the controller
	 */
	public ICTas getController() {
		return this.controleur;
	}
	
	/**
	 * Activate or desactivate the drop target listener of the deck
	 * @param b
	 */
	public void setDropTargetActive(boolean b) {
		this.dropTarget.setActive(b);
	}
	
	@Override
	public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
		return this;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor df) {
		return true;
	}
	
	/**
	 * Class TasDropTargetListener
	 */
	private class TasDropTargetListener implements DropTargetListener {
		private PTasDeCartes sourceDeck;
		
		@Override
		public void dragEnter(DropTargetDragEvent dtde) {
			//System.out.println("dragEnter "+PTasDeCartes.this.getController().getNom());
			Transferable transf = dtde.getTransferable();
			try {
				
				sourceDeck = (PTasDeCartes) transf.getTransferData(new DataFlavor(getClass(), null));
				controleur.p2cDragEnter(sourceDeck.getController());
				
			} catch (UnsupportedFlavorException e) {
				System.out.println("Erreur lors du drag and drop : "+e.getMessage());
			} catch (IOException e) {
				System.out.println("Erreur lors du drag and drop : "+e.getMessage());
			}
		}

		@Override
		public void dragExit(DropTargetEvent dte) {
			//System.out.println("dragExit "+PTasDeCartes.this.getController().getNom());
			controleur.p2cDragExit(sourceDeck.getController());
		}

		@Override
		public void drop(DropTargetDropEvent dtde) {
			dropEvent = dtde;
			controleur.p2cDrop(sourceDeck.getController());
		}

		@Override
		public void dragOver(DropTargetDragEvent dtde) { }

		@Override
		public void dropActionChanged(DropTargetDragEvent dtde) { }
		
	}

}
