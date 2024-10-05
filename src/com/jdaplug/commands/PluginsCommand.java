package com.jdaplug.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.jdaplug.JDAPlug;
import com.jdaplug.Utils;
import com.jdaplug.api.createCommand;
import com.jdaplug.commandhandler.CommandSender;
import com.jdaplug.commandhandler.ConsoleCommand;
import com.jdaplug.consolehandler.ConsoleColor;
import com.jdaplug.plugins.Plugin;

import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class PluginsCommand extends createCommand {

	public PluginsCommand() {
		super(Commands.slash("plugins", "See the list of plugins installed."), "!", "plugins", "pl");
	}
	
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		List<String> plugins = JDAPlug.getPluginManager().getInstalledPlugins().stream().map(Plugin::getName).collect(Collectors.toList());

		String pluginsMessage = "Plugins (" + plugins.size() + "): " + plugins.toString().replace("[", "").replace("]", "");

		if(sender instanceof ConsoleCommand) {
			if(Utils.hasSupportColors())
				JDAPlug.getLogger().info("Plugins ("+ConsoleColor.GREEN_BRIGHT+plugins.size()+ConsoleColor.RESET+"): "
					+ConsoleColor.GREEN_BRIGHT+plugins.toString().replace("[", "").replace("]", "")+ConsoleColor.RESET);
			else
				JDAPlug.getLogger().info(pluginsMessage);
			return;
		}
		if(JDAPlug.getConfiguration().getBoolean("Commands.Plugins.Disabled")) {
			sender.replySender(JDAPlug.getConfiguration().getString("Commands.Plugins.Message"));
			return;
		}
		sender.replySender(pluginsMessage);
	}
	
}
