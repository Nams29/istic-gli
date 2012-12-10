package solitaire.util;

public interface Subject {
	
	public void setObserver(Observer o);
	
	public void notifyObservers();
	
}
