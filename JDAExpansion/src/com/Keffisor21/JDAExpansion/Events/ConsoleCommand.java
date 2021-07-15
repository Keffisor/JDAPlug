package com.Keffisor21.JDAExpansion.Events;

import com.Keffisor21.JDAExpansion.JDAExpansion;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class ConsoleCommand implements CommandSender {
	
	public JDA getJDA() {
		return JDAExpansion.getJDA();
	}

	public void sendMessage(String message) {
		JDAExpansion.getLogger().info(message);
	}
	
	public void replyMessage(String message) {
		JDAExpansion.getLogger().info(message);
	}
	
	public Member getMember() {
		return null;
	}
}
