package com.Keffisor21.JDAExpansion.Events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public interface CommandSender {
		
	public JDA getJDA();
	
	public void sendMessage(String message);
	
	public void replyMessage(String message);
	
	public Member getMember();
		
}
