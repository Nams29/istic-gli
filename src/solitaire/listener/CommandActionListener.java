package solitaire.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import solitaire.command.Command;

public class CommandActionListener implements ActionListener {
	
	private Command command;
	
	public CommandActionListener(Command command) {
		super();
		this.command = command;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command.execute();
	}
	
}
