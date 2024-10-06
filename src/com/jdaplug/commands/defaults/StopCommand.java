package com.jdaplug.commands;

import com.jdaplug.commandhandler.CommandSender;
import com.jdaplug.commandhandler.ConsoleCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class StopCommand extends CommandExecutor {

	public StopCommand() {	
		super(Commands.slash("stop", "Stop the bot. Requires permission.").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)), "!", "stop", "shutdown");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			System.exit(0);
			return;
		}

		if(sender.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
			System.exit(0);
			return;
		}

		sender.sendSenderMessage("You don't have permissions to do this, you must have the administrator permission");
	}
}
