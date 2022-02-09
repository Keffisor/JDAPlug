package com.Keffisor21.JDAExpansion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import com.Keffisor21.JDAExpansion.API.createCommand;
import com.Keffisor21.JDAExpansion.Commands.ClearConsoleCommand;
import com.Keffisor21.JDAExpansion.Commands.PluginsCommand;
import com.Keffisor21.JDAExpansion.Commands.ReloadCommand;
import com.Keffisor21.JDAExpansion.Commands.StopCommand;
import com.Keffisor21.JDAExpansion.Commands.VersionCommand;
import com.Keffisor21.JDAExpansion.ConfigManager.FileConfiguration;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ThreadConsoleReader;
import com.Keffisor21.JDAExpansion.ConsoleInterceptor.ConsoleInterceptorErr;
import com.Keffisor21.JDAExpansion.ConsoleInterceptor.ConsoleInterceptorOut;
import com.Keffisor21.JDAExpansion.EventController.EventsRegistration;
import com.Keffisor21.JDAExpansion.EventController.PluginListener;
import com.Keffisor21.JDAExpansion.Exceptions.InvalidIntentException;
import com.Keffisor21.JDAExpansion.Logs.LogsManager;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;
import com.Keffisor21.JDAExpansion.NMS.JDAType;
import com.Keffisor21.JDAExpansion.Plugins.PluginManager;

import jline.console.ConsoleReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;

public class JDAExpansion {
	
	private static JDA Jda = null;
 	private static ShardManager shardManager = null;
 	private static JDANMS JdaNMS = null;
	
	public static List<Object> registratedClassPlugin = new ArrayList<Object>(); 
	private static PluginManager pluginManager = new PluginManager();
	private static LogsManager logsManager = new LogsManager();
	private static FileConfiguration configuration = new FileConfiguration(new File(JDAExpansion.class.getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20", " ")), JDAExpansion.getAbsolutePath(), "JDAExpansion.yml", new JDAExpansion());
	
	public static void start(JDA jda) {
		start((Object)jda);
	}
	
	public static void start(ShardManager shardManager) {
		start((Object)shardManager);
	}
	
	private static void start(Object o) {
		setConsoleConfig();
		Console.logger.info("Loading libraries...");
		if(o instanceof JDA)
			try { ((JDA)o).awaitReady(); } catch (InterruptedException e) { e.printStackTrace(); }
		JDANMS jda = new JDANMS(getJDAType(o));
		JdaNMS = jda;
		//create thread of reading the console
 	    new ThreadConsoleReader(jda, Console.reader).start();
		getJDA().addEventListener(new EventsRegistration(), new ClearConsoleCommand(), new PluginsCommand(), new ReloadCommand(), new StopCommand(), new VersionCommand());
		new Console().start();
	    pluginManager.loadPlugins(jda);
	    //load commanddata for slashcommands
	    getJDA().updateCommands().addCommands(createCommand.commandsData).queue();
	}
	
	private static void setConsoleConfig() {
		Utils.disableAccessWarnings(); //disable the illegal access warn
		System.setErr(new ConsoleInterceptorErr(System.err));
		Console.previousPrintStream = System.out;
		System.setOut(new ConsoleInterceptorOut(System.out));

		try {
			Console.reader =  new ConsoleReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static JDAType getJDAType(Object o) {
		JDAType type = new JDAType();
		if(o instanceof JDA) {
			type.jda = (JDA)o;
			Jda = (JDA)o;
		}
		if(o instanceof ShardManager) {
			type.shardManager = (ShardManager)o;
			shardManager = (ShardManager)o;
		}
		return type;
	}
	
	public static PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public static LogsManager getLogsManager() {
		return logsManager;
	}
	
	public static JDANMS getJDANMS() {
		return JdaNMS;
	}

	public static Logger getLogger() {
		return Console.logger;
	}
	
	public static JDA getJDA() {
		return Jda;
	}
	
	public static FileConfiguration getConfiguration() {
		return configuration;
	}
	
	public static ShardManager getShardManager() {
		return shardManager;
	}
	
	public static void registerEvent(Object o) {
		if(o instanceof ListenerAdapter)
			registratedClassPlugin.add(o);
		if(o instanceof PluginListener)
			EventsRegistration.loadEvent((PluginListener)o);
	}
	
	public static void registerEvents(Object... o) {
		for(Object o2 : o) {
			if(o2 instanceof ListenerAdapter)
				registratedClassPlugin.add(o2);
			if(o2 instanceof PluginListener)
				EventsRegistration.loadEvent((PluginListener)o2);
		}
	}
	
	public static void addEventListener(Object... o) {
		registerEvents(o);
	}
	
	public static List<GatewayIntent> getEnabledGatewayIntents() {
		return getConfiguration().getStringList("Intents.Enabled").stream().filter(s -> {
			try {
				GatewayIntent.valueOf(s);
				return true;
			} catch (Exception e) {
				new InvalidIntentException(Utils.convertToColors(ConsoleColor.RED, "Invalid gateway intent \"" + s + "\". Options: GUILD_MEMBERS, GUILD_BANS, GUILD_EMOJIS, GUILD_WEBHOOKS, GUILD_INVITES, GUILD_VOICE_STATES, GUILD_PRESENCES, GUILD_MESSAGES, GUILD_MESSAGE_REACTIONS, GUILD_MESSAGE_TYPING, DIRECT_MESSAGES, DIRECT_MESSAGE_REACTIONS, DIRECT_MESSAGE_TYPING")).printStackTrace();
				return false;
			}
		}).map(GatewayIntent::valueOf).collect(Collectors.toList());
	}
	
	public static List<GatewayIntent> getDisabledGatewayIntents() {
		return getConfiguration().getStringList("Intents.Disabled").stream().filter(s -> {
			try {
				GatewayIntent.valueOf(s);
				return true;
			} catch (Exception e) {
				new InvalidIntentException(Utils.convertToColors(ConsoleColor.RED, "Invalid gateway intent \"" + s + "\". Options: GUILD_MEMBERS, GUILD_BANS, GUILD_EMOJIS, GUILD_WEBHOOKS, GUILD_INVITES, GUILD_VOICE_STATES, GUILD_PRESENCES, GUILD_MESSAGES, GUILD_MESSAGE_REACTIONS, GUILD_MESSAGE_TYPING, DIRECT_MESSAGES, DIRECT_MESSAGE_REACTIONS, DIRECT_MESSAGE_TYPING")).printStackTrace();
				return false;
			}
		}).map(GatewayIntent::valueOf).collect(Collectors.toList());
	}
	
	public static String getAbsolutePath() {
		return new File("").getAbsolutePath();
	} 
}
