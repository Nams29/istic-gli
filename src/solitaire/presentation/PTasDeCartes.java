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

public class PTasDeCartes extends JPanel implements Transferable {

	private static final long serialVersionUID = -1583784803552208119L;

	protected ICTas controleur;
	
	private DropTarget dropTarget;
	private DropTargetEvent dropEvent;
	
	private int dx, dy;		// Shift between each cards
	private int cx, cy;		// Current position of the last card
	private Dimension dim = new Dimension(PCarte.largeur, PCarte.hauteur);

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
		//this.setOpaque(false);
		this.setBackground(PSolitaire.bg_color_light);
	}
	
	/**
	 * Initiate the drag and drop elements
	 */
	private void initDrag() {
		this.dropTarget = new DropTarget(this, new TasDropTargetListener());
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
			Transferable transf = dtde.getTransferable();
			
			try {
				sourceDeck = (PTasDeCartes) transf.getTransferData(new DataFlavor(getClass(), null));
			} catch (UnsupportedFlavorException e) {
				System.out.println("Erreur lors du drag and drop : "+e.getMessage());
			} catch (IOException e) {
				System.out.println("Erreur lors du drag and drop : "+e.getMessage());
			}
		}

		@Override
		public void dragExit(DropTargetEvent dte) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dragOver(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void drop(DropTargetDropEvent dtde) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
