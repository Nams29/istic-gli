package solitaire.controle;

public interface ICDragSource {
	
	/**
	 * Called when a drag gesture is done on the presentation
	 * @param carte the controller of the dragged card
	 */
	public void p2cDragGestureRecognized(CCarte carte);
	
	/**
	 * Called when the drag and drop succeed
	 * @param icTas
	 */
	public void p2cDragSuccess(ICTas tas);
	
	/**
	 * Called when the drag and drop failed
	 * @param icTas
	 */
	public void p2cDragFail(ICTas tas);
	
}
