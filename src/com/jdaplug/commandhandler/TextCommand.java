package com.jdaplug.commandhandler;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TextCommand extends MessageReceivedEvent implements CommandSender {
	private MessageReceivedEvent e;
	private String command;
	private String prefix;
	
	public TextCommand(MessageReceivedEvent e, String command, String prefix) {
		super(e.getJDA(), e.getResponseNumber(), e.getMessage());
		
		this.e = e;
		this.command = command;
		this.prefix = prefix;
	}

	public void sendSenderMessage(String message) {
		e.getChannel().sendMessage(message).queue();
	}

	public void replySender(String message) {
		e.getMessage().reply(message).queue();
	}

	public void replySenderEmbeds(MessageEmbed embed) { e.getMessage().replyEmbeds(embed).queue(); }

	public String getCommand() {
		return command;
	}
	
	public String getPrefix() {
		return prefix;
	}
}
