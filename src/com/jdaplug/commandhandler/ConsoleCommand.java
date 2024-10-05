package com.jdaplug.commandhandler;

import com.jdaplug.JDAPlug;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class ConsoleCommand implements CommandSender {
	private final String command;
	
	public ConsoleCommand(String command) {
		this.command = command;
	}
	
	public JDA getJDA() {
		return JDAPlug.getJDA();
	}

	public void sendSenderMessage(String message) {
		JDAPlug.getLogger().info(message);
	}

	public void replySender(String message) {
		JDAPlug.getLogger().info(message);
	}
	
	@SuppressWarnings("unused")
	public void replySenderEmbeds(MessageEmbed embed) {}
	
	@SuppressWarnings("unused")
	public Member getMember() {
		return null;
	}

	public String getCommand() {
		return command;
	}
	
}
