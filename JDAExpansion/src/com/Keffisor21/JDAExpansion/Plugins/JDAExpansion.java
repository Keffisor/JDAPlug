package com.Keffisor21.JDAExpansion.Plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.Keffisor21.JDAExpansion.Main;
import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.setupCommands;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleReader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;

public class JDAExpansion {
	
	public static List<Object> registratedClassPlugin = new ArrayList<Object>(); 
	
	public static void start(JDA jda) {
		Utils.clearConsole();
	    new ConsoleReader(jda).run();
		Console.logger.info("Loading libraries...");
		setupCommands.loadCommands(jda);
		new Console().start();
	    Plugin.loadPlugins(jda);
	}
	public static Logger getLogger() {
		return Console.logger;
	}
	public static JDA getJDA() {
		return Main.Jda;
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
