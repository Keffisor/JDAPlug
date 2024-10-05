package com.jdaplug.commandhandler;

import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public class SlashCommand extends SlashCommandInteractionEvent implements CommandSender {

	private SlashCommandInteractionEvent e;
	private String command;
	
	public SlashCommand(SlashCommandInteractionEvent e, String command) {
		super(e.getJDA(), e.getResponseNumber(), e.getInteraction());

		this.e = e;
		this.command = command;
	}

	public void sendSenderMessage(String message) { e.getChannel().sendMessage(message).queue(); }

	public void replySender(String message) { e.reply(message).queue(); }

	public void replySenderEmbeds(MessageEmbed embed) { e.replyEmbeds(embed).queue(); }

	public String getCommand() {
		return command;
	}
}
