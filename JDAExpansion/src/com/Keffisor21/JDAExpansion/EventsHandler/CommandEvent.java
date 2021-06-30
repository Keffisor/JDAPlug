package com.Keffisor21.JDAExpansion.EventsHandler;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandEvent {
	private MessageReceivedEvent e;
	
	public CommandEvent(MessageReceivedEvent e) {
		this.e = e;
		
	}
	
	public TextChannel getTextChannel() {
		return e.getTextChannel();
	}
	
	public User getAuthor() {
		return e.getAuthor();
	}
	
	public MessageChannel getChannel() {
		return e.getChannel();
	}
	
	public ChannelType getChannelType() {
		return e.getChannelType();
	}
	
	public Guild getGuild() {
		return e.getGuild();
	}
	
	public Member getMember() {
		return e.getMember();
	}
	
	public Message getMessage() {
		return e.getMessage();
	}
	
	public String getMessageId() {
		return e.getMessageId();
	}
	
	public long getMessageIdLong() {
	   return e.getMessageIdLong();
	}
	
	public PrivateChannel getPrivateChannel() {
		return e.getPrivateChannel();
	}
	
	public long getResponseNumber() {
		return e.getResponseNumber();
	}
	
	public boolean isFromGuild() {
		return e.isFromGuild();
	}
	
	public boolean isFromType(ChannelType type) {
		return e.isFromType(type);
	}
	
	public boolean isWebhookMessage() {
		return e.isWebhookMessage();
	}
	
}
