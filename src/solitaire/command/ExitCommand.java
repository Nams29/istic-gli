package solitaire.command;

import solitaire.controle.CSolitaire;

public class ExitCommand implements Command {
	
	private CSolitaire solitaire;
	
	public ExitCommand(CSolitaire solitaire) {
		this.solitaire = solitaire;
	}
	
	@Override
	public void execute() {
		this.solitaire.exit();
	}

}
