package com.jdaplug;

import javax.security.auth.login.LoginException;

import com.jdaplug.configmanager.TokenConfiguration;
import com.jdaplug.consolehandler.ConsoleColor;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

public class Main extends ListenerAdapter {
	 	
	public static void main(String[] args) {
		Utils.isOpenByConsole();
		Utils.executeCommand("cls", "clear"); //for fix the console colors (w10/w11 only)
		
		String token = TokenConfiguration.getTokenFileContent();
		if(token.isEmpty()) {
			Utils.executeCommand("cls", "clear");
			System.out.println(Utils.convertToColors(ConsoleColor.RED_BRIGHT, "\n[ERROR] The token.txt is empty, please write the token of the bot"));;
			return;
		}

		JDABuilder jdaBuilder = JDABuilder.createDefault(token)
			.enableCache(Utils.convertCacheFlagList(JDAPlug.getEnabledCacheFlags()))
			.disableCache(Utils.convertCacheFlagList(JDAPlug.getDisabledCacheFlags()))

			.enableIntents(JDAPlug.getEnabledGatewayIntents())
			.disableIntents(JDAPlug.getDisabledGatewayIntents())

			.setMemberCachePolicy(JDAPlug.getMemberCachePolicy())
			.setChunkingFilter(JDAPlug.getChunkingFilter())
		;

		//JDABuilder jdaBuilder =  JDABuilder.createDefault(token).setChunkingFilter(ChunkingFilter.ALL).setMemberCachePolicy(MemberCachePolicy.ALL).setDisabledIntents(JDAExpansion.getDisabledGatewayIntents()).enableIntents(JDAExpansion.getEnabledGatewayIntents());

		try {
			if(JDAPlug.getConfiguration().getBoolean("ShardManager.Enabled"))
				JDAPlug.start(DefaultShardManagerBuilder.createDefault(token).setShardsTotal(JDAPlug.getConfiguration().getInt("ShardManager.Shards")).build());
			else JDAPlug.start(jdaBuilder.build());
		} catch(Throwable e) {
			e.printStackTrace();
			throw (Error)e;
		}
		
	}
}
