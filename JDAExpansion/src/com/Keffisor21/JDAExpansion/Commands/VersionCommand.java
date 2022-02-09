package com.Keffisor21.JDAExpansion.Commands;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.API.createCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class VersionCommand extends createCommand {

	public VersionCommand() {
		super(null, "!", "version", "ver");
	}

	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(JDAExpansion.getConfiguration().getBoolean("Commands.Version.Disabled")) {
			sender.replyMessage(JDAExpansion.getConfiguration().getString("Commands.Version.Message"));
			return;
		}
		sender.replyMessage("Running JDAExpansion BETA v2.0");
	}

}