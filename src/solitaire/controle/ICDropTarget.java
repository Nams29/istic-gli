package solitaire.controle;

public interface ICDropTarget {
	
	/**
	 * Called when a dragged deck enters the deck
	 * @param tas the dragged deck
	 */
	public void p2cDragEnter(ICTas tas);
	
	/**
	 * Called when a dragged deck leaves the deck 
	 * @param tas the dragged deck
	 */
	public void p2cDragExit(ICTas tas);
	
	/**
	 * Called when a deck is dropped on the deck
	 * @param tas the dropped deck
	 */
	public void p2cDrop(ICTas tas);
}
