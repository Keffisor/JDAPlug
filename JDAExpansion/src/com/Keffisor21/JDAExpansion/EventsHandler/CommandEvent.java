package com.Keffisor21.JDAExpansion.EventsHandler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandEvent extends MessageReceivedEvent {

	public CommandEvent(JDA api, long responseNumber, Message message) {
		super(api, responseNumber, message);
	}

}
