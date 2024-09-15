package com.jdaplug.commands;

import com.jdaplug.Utils;
import com.jdaplug.api.createCommand;
import com.jdaplug.commandhandler.CommandSender;
import com.jdaplug.commandhandler.ConsoleCommand;

public class ClearConsoleCommand extends createCommand {

	public ClearConsoleCommand() {
		super(null, "!", "cls", "clear");
	}

	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			ConsoleCommand e = (ConsoleCommand)sender;
			Utils.executeCommand("cls", "clear");
		}
	}
	
}