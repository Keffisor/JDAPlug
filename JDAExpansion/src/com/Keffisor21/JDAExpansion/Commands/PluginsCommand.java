package com.Keffisor21.JDAExpansion.Commands;

import java.util.List;
import java.util.stream.Collectors;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;
import com.Keffisor21.JDAExpansion.CommandHandler.ConsoleCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.SlashCommand;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.Event.EventHandler;
import com.Keffisor21.JDAExpansion.Event.API.createCommand;
import com.Keffisor21.JDAExpansion.Plugins.Plugin;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class PluginsCommand extends createCommand {

	public PluginsCommand() {	
		super(new CommandData("plugins", "See the list of plugins installed."), "!", "plugins", "pl");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		List<String> plugins = JDAExpansion.getPluginManager().getInstalledPlugins().stream().map(Plugin::getName).collect(Collectors.toList());
		if(sender instanceof ConsoleCommand && Utils.hasSupportColors()) {
			JDAExpansion.getLogger().info("Plugins ("+ConsoleColor.GREEN_BRIGHT+plugins.size()+ConsoleColor.RESET+"): "
				+ConsoleColor.GREEN_BRIGHT+plugins.toString().replace("[", "").replace("]", "")+ConsoleColor.RESET);
			return;
		}
		if(JDAExpansion.getConfiguration().getBoolean("HidePlugins")) {
			sendMessage(sender, "You don't have permission to do this "+sender.getMember().getAsMention());
			return;
		}
		sendMessage(sender, "Plugins ("+plugins.size()+"): "+plugins.toString().replace("[", "").replace("]", ""));
	}
	public void sendMessage(CommandSender sender, String message) {
		if(sender instanceof SlashCommand) {
			((SlashCommand)sender).reply(message).queue();
			return;
		}
		sender.sendMessage(message);
	}
	
}
