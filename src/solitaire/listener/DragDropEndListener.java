package solitaire.listener;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

public abstract class DragDropEndListener implements DragSourceListener {

	@Override
	public abstract void dragDropEnd(DragSourceDropEvent arg0);

	@Override
	public void dragEnter(DragSourceDragEvent arg0) { }

	@Override
	public void dragExit(DragSourceEvent arg0) { }

	@Override
	public void dragOver(DragSourceDragEvent arg0) { }

	@Override
	public void dropActionChanged(DragSourceDragEvent arg0) { }

}
