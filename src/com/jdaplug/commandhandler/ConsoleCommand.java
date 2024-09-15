package com.jdaplug.commandhandler;

import com.jdaplug.JDAPlug;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class ConsoleCommand implements CommandSender {
	private String command;
	
	public ConsoleCommand(String command) {
		this.command = command;
	}
	
	public JDA getJDA() {
		return JDAPlug.getJDA();
	}

	public void sendMessage(String message) {
		JDAPlug.getLogger().info(message);
	}

	public void replyMessage(String message) {
		JDAPlug.getLogger().info(message);
	}
	
	@SuppressWarnings("unused")
	public void replyMessageEmbeds(MessageEmbed embed) {}
	
	@SuppressWarnings("unused")
	public Member getMember() {
		return null;
	}

	public String getCommand() {
		return command;
	}
	
}
