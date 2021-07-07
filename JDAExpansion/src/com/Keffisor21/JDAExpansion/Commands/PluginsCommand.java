package com.Keffisor21.JDAExpansion.Commands;

import static net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER;
import static net.dv8tion.jda.api.interactions.commands.OptionType.USER;

import java.util.List;
import java.util.stream.Collectors;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.Events.Command;
import com.Keffisor21.JDAExpansion.Events.CommandSender;
import com.Keffisor21.JDAExpansion.Events.ConsoleCommand;
import com.Keffisor21.JDAExpansion.Events.SlashCommand;
import com.Keffisor21.JDAExpansion.EventsHandler.createCommand;
import com.Keffisor21.JDAExpansion.Plugins.Plugin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class PluginsCommand extends createCommand {

	public PluginsCommand() {	
		super(new CommandData("plugins", "See the list of plugins installed."), "!", "plugins", "pl");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		List<String> plugins = JDAExpansion.getPluginManager().getInstalledPlugins().stream().map(Plugin::getName).collect(Collectors.toList());
		if(sender instanceof ConsoleCommand) {
			JDAExpansion.getLogger().info("Plugins ("+ConsoleColor.GREEN_BRIGHT+plugins.size()+ConsoleColor.RESET+"): "
				+ConsoleColor.GREEN_BRIGHT+plugins.toString().replace("[", "").replace("]", "")+ConsoleColor.RESET);
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
