package com.Keffisor21.JDAExpansion.Commands;

import static net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER;
import static net.dv8tion.jda.api.interactions.commands.OptionType.USER;

import com.Keffisor21.JDAExpansion.API.createCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.Command;
import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;
import com.Keffisor21.JDAExpansion.CommandHandler.ConsoleCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.SlashCommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class StopCommand extends createCommand {

	public StopCommand() {	
		super(new CommandData("stop", "Stop the bot. Requires permission."), "!", "stop", "shutdown");
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
