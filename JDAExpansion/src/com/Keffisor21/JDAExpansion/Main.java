package com.Keffisor21.JDAExpansion;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import com.Keffisor21.JDAExpansion.ConfigManager.TokenConfiguration;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends ListenerAdapter {
	 	
	public static void main(String[] args) throws LoginException {
		Utils.isOpenByConsole();
		Utils.executeCommand("cls", "clear"); //for fix the console colors (w10 only)
		
		String token = TokenConfiguration.getTokenFileContent();
		if(token.isEmpty()) {
			Utils.executeCommand("cls", "clear");
			System.out.println(Utils.convertToColors(ConsoleColor.RED_BRIGHT, "\n[ERROR] The token.txt is empty, please write the token of the bot"));;
			return;
		}

		JDABuilder jdaBuilder = JDABuilder.createDefault(token)
			.enableCache(Utils.convertCacheFlagList(JDAExpansion.getEnabledCacheFlags()))
			.disableCache(Utils.convertCacheFlagList(JDAExpansion.getDisabledCacheFlags()))

			.enableIntents(JDAExpansion.getEnabledGatewayIntents())
			.disableIntents(JDAExpansion.getDisabledGatewayIntents())

			.setMemberCachePolicy(JDAExpansion.getMemberCachePolicy())
			.setChunkingFilter(JDAExpansion.getChunkingFilter())
		;

		//JDABuilder jdaBuilder =  JDABuilder.createDefault(token).setChunkingFilter(ChunkingFilter.ALL).setMemberCachePolicy(MemberCachePolicy.ALL).setDisabledIntents(JDAExpansion.getDisabledGatewayIntents()).enableIntents(JDAExpansion.getEnabledGatewayIntents());

		try {
			if(JDAExpansion.getConfiguration().getBoolean("ShardManager.Enabled"))
				JDAExpansion.start(DefaultShardManagerBuilder.createDefault(token).setShardsTotal(JDAExpansion.getConfiguration().getInt("ShardManager.Shards")).build());
			else JDAExpansion.start(jdaBuilder.build());
		} catch(Throwable e) {
			e.printStackTrace();
			throw (Error)e;
		}
		
	}
}
