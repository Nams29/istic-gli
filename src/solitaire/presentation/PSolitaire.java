package solitaire.presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import solitaire.controle.CSolitaire;

public class PSolitaire extends JPanel {
	
	private static final long serialVersionUID = -6817459451699654006L;
	
	private CSolitaire controleur;
	
	private JPanel sabotPanel;
	private JPanel couleursPanel;
	private JPanel colonnesPanel;
	
	public PSolitaire(CSolitaire cSolitaire) {
		this.controleur = cSolitaire;
		
		this.initLayout();
	}
	
	/**
	 * Initiate the graphic components
	 */
	private void initLayout() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTH;
		
		this.sabotPanel = new JPanel();
		this.add(sabotPanel, gbc);
		
		gbc.gridx = 1;
		this.couleursPanel = new JPanel();
		this.add(couleursPanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.colonnesPanel = new JPanel();
		this.add(colonnesPanel, gbc);
	}
	
	/**
	 * Set the sabot
	 * @param sabot
	 */
	public void setSabot(PSabot sabot) {
		this.sabotPanel.add(sabot);
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
