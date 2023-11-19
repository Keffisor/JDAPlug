package com.Keffisor21.JDAExpansion.Commands;

import static net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER;
import static net.dv8tion.jda.api.interactions.commands.OptionType.USER;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.API.createCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.Command;
import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;
import com.Keffisor21.JDAExpansion.CommandHandler.ConsoleCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.SlashCommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class ReloadCommand extends createCommand {

	public ReloadCommand() {	
		super(Commands.slash("reload", "Reload the plugins of the bot. Requires permission."), "!", "reload", "rl");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			JDAExpansion.getLogger().info("Plugins reloaded!");
			JDAExpansion.getPluginManager().loadPlugins(JDAExpansion.getJDANMS());
			return;
		}
		
		if(sender.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
			JDAExpansion.getPluginManager().loadPlugins(JDAExpansion.getJDANMS());
			sendMessage(sender, "Plugins reloaded! "+sender.getMember().getAsMention());
		} else {
			sendMessage(sender, "You don't have permissions to do this, you must have the administrator permission");
		}
		
	}
	public void sendMessage(CommandSender sender, String message) {
		if(sender instanceof SlashCommand) {
			((SlashCommand)sender).reply(message).queue();
			return;
		}
		sender.sendMessage(message);
	}
}
