package solitaire.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import solitaire.command.ExitCommand;
import solitaire.command.NewGameCommand;
import solitaire.command.OptionsCommand;
import solitaire.command.ProposCommand;
import solitaire.controle.CSolitaire;
import solitaire.listener.CommandActionListener;

public class PSolitaire extends JPanel {
	
	private static final long serialVersionUID = -6817459451699654006L;
	
	private CSolitaire controleur;

	private JMenuBar menuBar;
	private JPanel northPanel;
	private JPanel couleursPanel;
	private JPanel colonnesPanel;
	
	public static Color bg_color = new Color(0, 110, 24);
	public static Color bg_color_light = new Color(70, 150, 70);
	
	public PSolitaire(CSolitaire cSolitaire) {
		this.controleur = cSolitaire;
		
		this.initLayout();
		this.initMenu();
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
	 * Initiate the menu
	 */
	private void initMenu() {
		menuBar = new JMenuBar();
		
		JMenu mFichier = new JMenu("Fichier");
		JMenuItem miNew = new JMenuItem("Nouvelle partie");
		JMenuItem miOptions = new JMenuItem("Options");
		JMenuItem miExit = new JMenuItem("Quitter");
		
		JMenu mHelp = new JMenu("?");
		JMenuItem miPropos = new JMenuItem("À propos");
		mHelp.add(miPropos);
		
		miNew.addActionListener(new CommandActionListener(new NewGameCommand(this.controleur)));
		miOptions.addActionListener(new CommandActionListener(new OptionsCommand(this.controleur)));
		miExit.addActionListener(new CommandActionListener(new ExitCommand(this.controleur)));
		miPropos.addActionListener(new CommandActionListener(new ProposCommand(this.controleur)));
		
		mFichier.add(miNew);
		mFichier.add(miOptions);
		mFichier.add(miExit);
		
		menuBar.add(mFichier);
		menuBar.add(mHelp);
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
	
	/**
	 * @return the menu bar
	 */
	public JMenuBar getMenuBar() {
		return this.menuBar;
	}
}
