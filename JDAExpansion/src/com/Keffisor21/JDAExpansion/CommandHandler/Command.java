package com.Keffisor21.JDAExpansion.CommandHandler;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Command extends MessageReceivedEvent implements CommandSender {
	private MessageReceivedEvent e;
	private String command;
	private String prefix;
	
	public Command(MessageReceivedEvent e, String command, String prefix) {
		super(e.getJDA(), e.getResponseNumber(), e.getMessage());
		
		this.e = e;
		this.command = command;
		this.prefix = prefix;
	}

	public void sendMessage(String message) {
		e.getChannel().sendMessage(message).queue();
	}

	public void replyMessage(String message) {
		e.getMessage().reply(message).queue();
	}

	public void replyMessageEmbeds(MessageEmbed embed) {
		e.getMessage().replyEmbeds(embed).queue();
	}

	public String getCommand() {
		return command;
	}
	
	public String getPrefix() {
		return prefix;
	}
}
