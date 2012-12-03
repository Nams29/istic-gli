package solitaire.controle;

import solitaire.application.Tas;
import solitaire.presentation.PTasDeCartes;

public interface ICTas extends ICDropTarget, Tas {
	
	public PTasDeCartes getPresentation();
	
	public int getNombre();
	
}
