package com.Keffisor21.JDAExpansion;

import javax.security.auth.login.LoginException;

import com.Keffisor21.JDAExpansion.ConfigManager.TokenConfiguration;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main extends ListenerAdapter {
	
	public static JDA Jda = null;
 	public static ShardManager shardManager = null;
 	public static JDANMS JdaNMS = null;
 	
	public static void main(String[] args) throws LoginException {
		Utils.executeCommand("cls", "clear"); //for fix the ansi codes

		String token = TokenConfiguration.getTokenFileContent();
		if(token.isEmpty()) {
			Utils.executeCommand("cls", "clear");
			System.out.println(Utils.convertToColors(ConsoleColor.RED_BRIGHT, "\n[ERROR] The token.txt is empty, please write the token of the bot"));;
			return;
		}
       JDA jda =  JDABuilder.createDefault(token).setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS).build();
       Jda = jda;
       try {
   	   JDAExpansion.start(jda);  
       } catch(Exception e) {}
	}
}
