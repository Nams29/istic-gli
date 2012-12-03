package solitaire.command;

import solitaire.controle.CSolitaire;

public class OptionsCommand implements Command {
	
	private CSolitaire solitaire;
	
	public OptionsCommand(CSolitaire solitaire) {
		this.solitaire = solitaire;
	}
	
	@Override
	public void execute() {
		this.solitaire.options();
	}

}
