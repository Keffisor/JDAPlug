package com.jdaplug.commands;

import com.jdaplug.api.createCommand;
import com.jdaplug.commandhandler.CommandSender;
import com.jdaplug.commandhandler.ConsoleCommand;
import com.jdaplug.commandhandler.SlashCommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class StopCommand extends createCommand {

	public StopCommand() {	
		super(Commands.slash("stop", "Stop the bot. Requires permission."), "!", "stop", "shutdown");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			System.exit(0);
			return;
		}
		String message = "You don't have permissions to do this, you must have the administrator permission";
		
		if(sender.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
			System.exit(0);
		} else {
			if(sender instanceof SlashCommand) {
				((SlashCommand)sender).reply(message).queue();
				return;
			}
			sender.sendMessage(message);
		}
	}
}
