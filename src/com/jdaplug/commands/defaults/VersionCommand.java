package com.jdaplug.commands.defaults;

import com.jdaplug.JDAPlug;
import com.jdaplug.commandhandler.CommandSender;
import com.jdaplug.commands.CommandExecutor;

public class VersionCommand extends CommandExecutor {

	public VersionCommand() {
		super(null, "!", "version", "ver");
	}

	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(JDAPlug.getConfiguration().getBoolean("Commands.Version.Disabled")) {
			sender.replySender(JDAPlug.getConfiguration().getString("Commands.Version.Message"));
			return;
		}
		sender.replySender("Running JDAPlug v1.1.2");
	}

}