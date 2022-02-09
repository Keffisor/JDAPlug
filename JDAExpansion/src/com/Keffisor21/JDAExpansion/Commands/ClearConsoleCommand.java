package com.Keffisor21.JDAExpansion.Commands;

import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.API.createCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;
import com.Keffisor21.JDAExpansion.CommandHandler.ConsoleCommand;

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