package com.jdaplug.commands;

import com.jdaplug.JDAPlug;
import com.jdaplug.api.createCommand;
import com.jdaplug.commandhandler.CommandSender;

public class VersionCommand extends createCommand {

	public VersionCommand() {
		super(null, "!", "version", "ver");
	}

	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(JDAPlug.getConfiguration().getBoolean("Commands.Version.Disabled")) {
			sender.replyMessage(JDAPlug.getConfiguration().getString("Commands.Version.Message"));
			return;
		}
		sender.replyMessage("Running JDAPlug v1.0");
	}

}