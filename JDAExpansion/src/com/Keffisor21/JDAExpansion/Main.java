package com.Keffisor21.JDAExpansion;

import java.io.File; 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.annotation.meta.When;
import javax.security.auth.login.LoginException;

import com.Keffisor21.JDAExpansion.ConfigManager.TokenConfiguration;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleReader;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;
import com.Keffisor21.JDAExpansion.Plugins.JDAExpansion;
import com.Keffisor21.JDAExpansion.Plugins.Plugin;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Main extends ListenerAdapter {
	public static JDA Jda = null;
 	public static ShardManager shardManager = null;
 	public static JDANMS JdaNMS = null;
 	
	public static void main(String[] args) throws LoginException {
		String token = TokenConfiguration.getTokenFileContent();
		if(token.isEmpty()) {
			Utils.clearConsole();
			Console.logger.info(ConsoleColor.RED_BRIGHT+"The token.txt is empty, please write the token of the bot"+ConsoleColor.RESET);
			return;
		}
       JDA jda = new JDABuilder(AccountType.BOT).setToken(token).build();
       Jda = jda;
   	   JDAExpansion.start(jda);  
	}
}
