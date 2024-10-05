package com.jdaplug.commandhandler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;

import javax.annotation.Nullable;

public interface CommandSender {
		
	JDA getJDA();
	
	void sendSenderMessage(String message);

	void replySender(String message);

	void replySenderEmbeds(MessageEmbed embed);

	@Nullable Member getMember();
	
	String getCommand();

	default TextCommand getAsTextCommand() {
		return (TextCommand) this;
	}

	default SlashCommand getAsSlashCommand() {
		return (SlashCommand) this;
	}

	default ConsoleCommand getAsConsoleCommand() {
		return (ConsoleCommand) this;
	}

}