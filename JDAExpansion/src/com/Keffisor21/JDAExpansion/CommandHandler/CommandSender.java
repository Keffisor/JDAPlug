package com.Keffisor21.JDAExpansion.CommandHandler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;

public interface CommandSender {
		
	public JDA getJDA();
	
	public void sendMessage(String message);
	
	public void replyMessage(String message);
	
	public void replyMessageEmbeds(MessageEmbed embed);
	
	public Member getMember();
	
	public String getCommand();
			
}
