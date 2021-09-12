package com.Keffisor21.JDAExpansion.Commands;

import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.Events.CommandSender;
import com.Keffisor21.JDAExpansion.Events.ConsoleCommand;
import com.Keffisor21.JDAExpansion.EventsHandler.createCommand;

import ch.qos.logback.classic.pattern.Util;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

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