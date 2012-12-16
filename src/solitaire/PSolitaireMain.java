package solitaire;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import solitaire.command.ExitCommand;
import solitaire.command.NewGameCommand;
import solitaire.command.OptionsCommand;
import solitaire.command.ProposCommand;
import solitaire.listener.CommandActionListener;
import solitaire.presentation.PSolitaire;

public class PSolitaireMain extends JFrame {

	private static final long serialVersionUID = 3375461047751753414L;
	
	private CSolitaireMain controleur;
	
	public static Color bg_color = new Color(0, 110, 24);
	public static Color bg_color_light = new Color(70, 150, 70);
	
	public PSolitaireMain(CSolitaireMain c) {
		super();
		
		this.controleur = c;
		
		this.setTitle("Solitaire");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.initLayout();
		this.initMenu();
	}
	
	/**
	 * Initiate the graphic components
	 */
	private void initLayout() {
		Dimension dim = new Dimension(840, 650);
		this.setMinimumSize(dim);
		this.setSize(dim);
		this.setVisible(true);
		this.setLocationRelativeTo(this.getParent());
	}
	
	/**
	 * Initiate the menu
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		
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
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Display the game
	 */
	public void displayGame(PSolitaire solitaire) {
		this.setContentPane(solitaire);
		
		this.validate();
		this.repaint();
	}
	
}
