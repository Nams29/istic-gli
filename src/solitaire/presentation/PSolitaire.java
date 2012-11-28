package solitaire.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import solitaire.controle.CSolitaire;

public class PSolitaire extends JPanel {
	
	private static final long serialVersionUID = -6817459451699654006L;
	
	@SuppressWarnings("unused")
	private CSolitaire controleur;

	private JPanel northPanel;
	private JPanel couleursPanel;
	private JPanel colonnesPanel;
	
	private Color bg_color = new Color(0, 110, 24);
	
	public PSolitaire(CSolitaire cSolitaire) {
		this.controleur = cSolitaire;
		
		this.initLayout();
	}
	
	/**
	 * Initiate the graphic components
	 */
	private void initLayout() {
		this.setLayout(new BorderLayout());
		this.setBackground(bg_color);
		
		this.couleursPanel = new JPanel();
		this.couleursPanel.setOpaque(false);
		
		this.northPanel = new JPanel(new BorderLayout());
		this.northPanel.add(couleursPanel, BorderLayout.EAST);
		this.northPanel.setOpaque(false);
		this.add(northPanel, BorderLayout.NORTH);
		
		this.colonnesPanel = new JPanel(new GridLayout(1, 7));
		this.colonnesPanel.setOpaque(false);
		this.colonnesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
		this.add(colonnesPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Set the sabot
	 * @param sabot
	 */
	public void setSabot(PSabot sabot) {
		this.northPanel.add(sabot, BorderLayout.WEST);
	}
	
	/**
	 * Add a colored deck
	 * @param tasColore the colored deck
	 */
	public void addTasColore(PTasColore tasColore) {
		this.couleursPanel.add(tasColore);
	}
	
	/**
	 * Add a column
	 * @param colonne the column
	 */
	public void addColonne(PColonne colonne) {
		this.colonnesPanel.add(colonne);
	}
}
