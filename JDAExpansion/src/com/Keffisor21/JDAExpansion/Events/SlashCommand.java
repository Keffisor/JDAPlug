package com.Keffisor21.JDAExpansion.Events;

import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;

public class SlashCommand implements CommandSender, CommandInteraction {

	private SlashCommandEvent e;
	private String command;
	
	public SlashCommand(SlashCommandEvent e, String command) {
		this.e = e;
		this.command = command;
	}
	
	public JDA getJDA() {
		return e.getJDA();
	}

	public int getTypeRaw() {
		return e.getTypeRaw();
	}

	public String getToken() {
		return e.getToken();
	}

	public Guild getGuild() {
		return e.getGuild();
	}

	public User getUser() {
		return e.getUser();
	}

	public Member getMember() {
		return e.getMember();
	}

	public InteractionHook getHook() {
		return e.getHook();
	}

	public boolean isAcknowledged() {
		return e.isAcknowledged();
	}

	public ReplyAction deferReply() {
		return e.deferReply();
	}

	public long getIdLong() {
		return e.getIdLong();
	}

	public String getName() {
		return e.getName();
	}

	public String getSubcommandName() {
		return e.getSubcommandName();
	}

	public String getSubcommandGroup() {
		return e.getSubcommandGroup();
	}

	public MessageChannel getChannel() {
		return e.getChannel();
	}

	public long getCommandIdLong() {
		return e.getCommandIdLong();
	}

	public List<OptionMapping> getOptions() {
		return e.getOptions();
	}

	public void sendMessage(String message) {
		e.getChannel().sendMessage(message).queue();
	}
	
	public void replyMessage(String message) {
		e.reply(message).queue();
	}

	public String getCommand() {
		return command;
	}
	
}
