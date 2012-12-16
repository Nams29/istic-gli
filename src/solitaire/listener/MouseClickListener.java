package solitaire.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MouseClickListener implements MouseListener {
	
	protected boolean valid;

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public abstract void mouseEntered(MouseEvent e);

	@Override
	public abstract void mouseExited(MouseEvent e);

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public abstract void mouseReleased(MouseEvent e);

}
