package solitaire.presentation;

public interface IPDropTarget {
	
	/**
	 * If the drop has been accepted
	 */
	public void c2pDropOK();
	
	/**
	 * If the drop has not been accepted
	 */
	public void c2pDropFailed();
	
	/**
	 * If the drop is possible
	 */
	public void c2pDropPossible();
	
	/**
	 * If the drop is not possible
	 */
	public void c2pDropImpossible();
	
	/**
	 * When the drag cursor leaves the deck panel
	 */
	public void c2pDragExit();
}
