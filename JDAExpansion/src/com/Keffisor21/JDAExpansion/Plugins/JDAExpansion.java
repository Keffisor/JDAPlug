package com.Keffisor21.JDAExpansion.Plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.Keffisor21.JDAExpansion.Main;
import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleReader;
import com.Keffisor21.JDAExpansion.EventsHandler.setupCommands;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;
import com.Keffisor21.JDAExpansion.NMS.JDAType;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.ShardManager;

public class JDAExpansion {
	
	public static List<Object> registratedClassPlugin = new ArrayList<Object>(); 
	
	public static void start(JDA jda) {
		start((Object)jda);
	}
	public static void start(ShardManager shardManager) {
		start((Object)shardManager);
	}
	private static void start(Object o) {
		Utils.clearConsole();
		JDANMS jda = new JDANMS(getJDAType(o));
		Main.JdaNMS = jda;
 	    new ConsoleReader(jda).run();
		Console.logger.info("Loading libraries...");
		setupCommands.loadCommands(jda);
	    new Console().start();
	    Plugin.loadPlugins(jda);
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
	public static JDANMS getJDANMS() {
		return Main.JdaNMS;
	}

	public static Logger getLogger() {
		return Console.logger;
	}
	public static JDA getJDA() {
		return Main.Jda;
	}
	public static ShardManager getShardManager() {
		return Main.shardManager;
	}
	public static void registerEvent(Object o) {
		if(o instanceof ListenerAdapter) {
			registratedClassPlugin.add(o);
		}
	}
   public static String getAbsolutePath() {
	   return new File("").getAbsolutePath();
   }   
}
