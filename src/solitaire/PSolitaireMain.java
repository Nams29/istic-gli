package solitaire;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import solitaire.command.ExitCommand;
import solitaire.command.NewGameCommand;
import solitaire.command.OneCardCommand;
import solitaire.command.ProposCommand;
import solitaire.command.ThreeCardsCommand;
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
		JMenuItem miExit = new JMenuItem("Quitter");
		
		JMenu mOptions = new JMenu("Options");
		JRadioButtonMenuItem  miOneCard = new JRadioButtonMenuItem("Tirer une carte");
		JRadioButtonMenuItem  miThreeCards = new JRadioButtonMenuItem("Tirer trois cartes");
		
		JMenu mHelp = new JMenu("?");
		JMenuItem miPropos = new JMenuItem("À propos");
		mHelp.add(miPropos);
		
		miNew.addActionListener(new CommandActionListener(new NewGameCommand(this.controleur)));
		miExit.addActionListener(new CommandActionListener(new ExitCommand(this.controleur)));
		miOneCard.addActionListener(new CommandActionListener(new OneCardCommand(this.controleur)));
		miThreeCards.addActionListener(new CommandActionListener(new ThreeCardsCommand(this.controleur)));
		miPropos.addActionListener(new CommandActionListener(new ProposCommand(this.controleur)));
		
		ButtonGroup cardsOptions = new ButtonGroup();
		cardsOptions.add(miOneCard);
		cardsOptions.add(miThreeCards);
		
		mOptions.add(miOneCard);
		mOptions.add(miThreeCards);
		miThreeCards.setSelected(true);
		
		mFichier.add(miNew);
		mFichier.add(mOptions);
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
	
	/**
	 * Display the information panel
	 */
	public void c2pPropos() {
		
	}
	
}
