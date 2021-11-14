package com.Keffisor21.JDAExpansion.Commands;

import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;
import com.Keffisor21.JDAExpansion.Event.API.createCommand;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class VersionCommand extends createCommand {

	public VersionCommand() {
		super(null, "!", "version", "ver");
	}

	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		sender.replyMessage("Running JDAExpansion BETA v2.0");
	}

}