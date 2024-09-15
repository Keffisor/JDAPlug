package com.jdaplug.commands;

import com.jdaplug.JDAPlug;
import com.jdaplug.api.createCommand;
import com.jdaplug.commandhandler.CommandSender;
import com.jdaplug.commandhandler.ConsoleCommand;
import com.jdaplug.commandhandler.SlashCommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class ReloadCommand extends createCommand {

	public ReloadCommand() {	
		super(Commands.slash("reload", "Reload the plugins of the bot. Requires permission."), "!", "reload", "rl");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			JDAPlug.getLogger().info("Plugins reloaded!");
			JDAPlug.getPluginManager().loadPlugins(JDAPlug.getJDANMS());
			return;
		}
		
		if(sender.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
			JDAPlug.getPluginManager().loadPlugins(JDAPlug.getJDANMS());
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
