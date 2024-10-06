package com.jdaplug;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.meta.Exclusive;

import com.jdaplug.commands.defaults.*;
import org.slf4j.Logger;

import com.jdaplug.commands.CommandExecutor;
import com.jdaplug.configmanager.FileConfiguration;
import com.jdaplug.consolehandler.Console;
import com.jdaplug.consolehandler.ConsoleColor;
import com.jdaplug.consolehandler.ThreadConsoleReader;
import com.jdaplug.consoleinterceptor.ConsoleInterceptorErr;
import com.jdaplug.consoleinterceptor.ConsoleInterceptorOut;
import com.jdaplug.eventcontroller.EventsRegistration;
import com.jdaplug.eventcontroller.PluginListener;
import com.jdaplug.exceptions.InvalidCacheFlagException;
import com.jdaplug.exceptions.InvalidIntentException;
import com.jdaplug.logs.LogsManager;
import com.jdaplug.nms.JDANMS;
import com.jdaplug.nms.JDAType;
import com.jdaplug.plugins.PluginManager;

import jline.console.ConsoleReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class JDAPlug {
	
	private static JDA Jda = null;
 	private static ShardManager shardManager = null;
 	private static JDANMS JdaNMS = null;
	
	public static List<Object> registratedClassPlugin = new ArrayList<Object>(); 
	private static PluginManager pluginManager = new PluginManager();
	private static LogsManager logsManager = new LogsManager();
	private static FileConfiguration configuration = new FileConfiguration(new File(JDAPlug.class.getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20", " ")), JDAPlug.getAbsolutePath(), "JDAPlug.yml", new JDAPlug());
	
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
	    getJDA().updateCommands().addCommands(CommandExecutor.commandsData).queue();
	}
	
	private static void setConsoleConfig() {
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
	
	@Exclusive
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
	
	public static List<CacheFlag> getEnabledCacheFlags() {
		return getConfiguration().getStringList("CacheFlags.Enabled").stream().filter(s -> {
			try {
				CacheFlag.valueOf(s);
				return true;
			} catch (Exception e) {
				new InvalidCacheFlagException(Utils.convertToColors(ConsoleColor.RED, "Invalid cache flag \"" + s + "\". Options: ACTIVITY, VOICE_STATE, EMOTE, CLIENT_STATUS, MEMBER_OVERRIDES, ROLE_TAGS, ONLINE_STATUS")).printStackTrace();
				return false;
			}
		}).map(CacheFlag::valueOf).collect(Collectors.toList());
	}
	
	public static List<CacheFlag> getDisabledCacheFlags() {
		return getConfiguration().getStringList("CacheFlags.Disabled").stream().filter(s -> {
			try {
				CacheFlag.valueOf(s);
				return true;
			} catch (Exception e) {
				new InvalidCacheFlagException(Utils.convertToColors(ConsoleColor.RED, "Invalid cache flag \"" + s + "\". Options: ACTIVITY, VOICE_STATE, EMOTE, CLIENT_STATUS, MEMBER_OVERRIDES, ROLE_TAGS, ONLINE_STATUS")).printStackTrace();
				return false;
			}
		}).map(CacheFlag::valueOf).collect(Collectors.toList());
	}
	
	public static MemberCachePolicy getMemberCachePolicy() {
		String s = getConfiguration().getString("MemberCachePolicy");
		MemberCachePolicy memberCachePolicy = MemberCachePolicy.ALL;
		
		try {
			Class<?> clazz = MemberCachePolicy.class;
			Field field = clazz.getDeclaredField(s);
			memberCachePolicy = (MemberCachePolicy) field.get(clazz);
		} catch (Exception e) {
			new RuntimeException(Utils.convertToColors(ConsoleColor.RED, "Invalid MemberCachePolicy \"" + s + "\". Options: NONE, ALL, OWNER, ONLINE, VOICE, BOOSTER, PENDING, DEFAULT"));
		}
		
		return memberCachePolicy;
	}
	
	public static ChunkingFilter getChunkingFilter() {
		String s = getConfiguration().getString("ChunkingFilter");
		ChunkingFilter chunkingFilter = ChunkingFilter.ALL;
		
		try {
			Class<?> clazz = ChunkingFilter.class;
			Field field = clazz.getDeclaredField(s);
			chunkingFilter = (ChunkingFilter) field.get(clazz);
		} catch (Exception e) {
			new RuntimeException(Utils.convertToColors(ConsoleColor.RED, "Invalid ChunkingFilter \"" + s + "\". Options: NONE, ALL"));
		}
		
		return chunkingFilter;
	}
	
	public static String getAbsolutePath() {
		return new File("").getAbsolutePath();
	} 
}
