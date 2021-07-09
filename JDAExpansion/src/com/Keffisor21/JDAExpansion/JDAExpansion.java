package com.Keffisor21.JDAExpansion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.MDC;

import com.Keffisor21.JDAExpansion.Commands.setupCommands;
import com.Keffisor21.JDAExpansion.ConfigManager.FileConfiguration;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleInterceptorErr;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleInterceptorOut;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ThreadConsoleReader;
import com.Keffisor21.JDAExpansion.Logs.LogsManager;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;
import com.Keffisor21.JDAExpansion.NMS.JDAType;
import com.Keffisor21.JDAExpansion.Plugins.PluginManager;

import jline.console.ConsoleReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.ShardManager;

public class JDAExpansion {
	
	public static List<Object> registratedClassPlugin = new ArrayList<Object>(); 
	private static PluginManager pluginManager = new PluginManager();
	private static LogsManager logsManager = new LogsManager();
	private static FileConfiguration configuration = new FileConfiguration(JDAExpansion.getAbsolutePath(), "JDAExpansion.yml", new JDAExpansion());
	
	public static void start(JDA jda) {
		start((Object)jda);
	}
	public static void start(ShardManager shardManager) {
		start((Object)shardManager);
	}
	private static void start(Object o) {
		setConsoleConfig();
		Console.logger.info("Loading libraries...");
		JDANMS jda = new JDANMS(getJDAType(o));
		Main.JdaNMS = jda;
		//create thread of reading the console
 	    new ThreadConsoleReader(jda, Console.reader).start();
		setupCommands.loadCommands(jda);
	    new Console().start();
	    pluginManager.loadPlugins(jda);
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
			Main.Jda = (JDA)o;
		}
		if(o instanceof ShardManager) {
			type.shardManager = (ShardManager)o;
			Main.shardManager = (ShardManager)o;
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
		return Main.JdaNMS;
	}

	public static Logger getLogger() {
		return Console.logger;
	}
	
	public static JDA getJDA() {
		return Main.Jda;
	}
	
	public static FileConfiguration getConfiguration() {
		return configuration;
	}
	
	public static ShardManager getShardManager() {
		return Main.shardManager;
	}
	public static void registerEvent(Object o) {
		if(o instanceof ListenerAdapter) {
			registratedClassPlugin.add(o);
		}
	}
	public static void registerEvents(Object... o) {
		for(Object o2 : o) {
			if(o2 instanceof ListenerAdapter) {
				registratedClassPlugin.add(o2);
			}
		}
	}
   public static String getAbsolutePath() {
	   return new File("").getAbsolutePath();
   }   
}
