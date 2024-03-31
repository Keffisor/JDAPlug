package com.Keffisor21.JDAExpansion.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.Keffisor21.JDAExpansion.CommandHandler.Command;
import com.Keffisor21.JDAExpansion.CommandHandler.CommandSender;
import com.Keffisor21.JDAExpansion.CommandHandler.ConsoleCommand;
import com.Keffisor21.JDAExpansion.CommandHandler.SlashCommand;
import com.Keffisor21.JDAExpansion.EventController.EventHandler;
import com.Keffisor21.JDAExpansion.EventController.PluginListener;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData; 

public abstract class createCommand extends ListenerAdapter {
	public static List<CommandData> commandsData = new ArrayList<>();
	
	private CommandData commandData;
    private String command;
    private String contentRaw = "";
    private List<String> aliases = new ArrayList<String>();
    private Command event = null;
    private String prefix;
    
    public createCommand(CommandData commandData, String prefix, String cmd, String... args) {
    	this.commandData = commandData;
    	this.prefix = prefix;
    	this.command = prefix+cmd;
    	if(args.length != 0) {
    		this.aliases = Arrays.asList(args);
    	}
    	if(commandData != null) {
    		commandsData.add(commandData);
    	}
    }
   
    //possible events
    @EventHandler
    public void onMessageReceived(MessageReceivedEvent e) {
    	if(e.getAuthor().isBot()) return;
      if(isCommand(e.getMessage().getContentRaw(), command) || getAliases(e.getMessage().getContentRaw(), false)) {
    	  
    	  contentRaw = e.getMessage().getContentRaw();
    	  event = new Command(e, getCommand(contentRaw), getPrefix());
     	  isExecuted(getArgs(), event);
      }
    }
    
    @EventHandler
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
    	if(e.getUser().isBot() || this.commandData == null) return;
    	
    	if(e.getName().equals(this.commandData.getName())) {
    	   String[] args = e.getFullCommandName().split("/");
    	   isExecuted(args, new SlashCommand(e, getCommand(e.getName())));
    	}
   }
    
   public boolean onConsoleMessageReceived(String content) {
	   if(isCommand(content, command.replaceFirst("\\"+prefix, "")) || getAliases(content, true)) {
		   contentRaw = content;
		   isExecuted(getArgs(), new ConsoleCommand(getCommand(content)));
		   return true;
	   }
	   return false;
   }
   //
   protected abstract void isExecuted(String[] args, CommandSender sender);   
   
    private String[] getArgs() {
    	if(contentRaw.replace("  ", " ").split(" ").length != 0) {
    		String[] args = contentRaw.replace("  ", " ").split(" ");
    		return Arrays.copyOfRange(args, 1, args.length);
    	}
    	return new String[] {};
    }
    
    private boolean getAliases(String contentRaw, boolean console) {
	    if(this.aliases.isEmpty()) {
	    	return false;
	    }
	    if(contentRaw.isEmpty()) {
	    	return false;
	    }
	    return !this.aliases.stream().filter(sT -> {if(!console) {sT = prefix+sT;} return isCommand(contentRaw, sT);}).collect(Collectors.toList()).isEmpty();
    }
    
    private boolean isCommand(String message, String command) {
    	if(!message.contains(" ")) return message.equalsIgnoreCase(command);
    	else {
			String cmd = message.split(" ")[0];
			return cmd.equalsIgnoreCase(command);
		}
    }
    
    private String getCommand(String content) {
    	if(!content.contains(" ")) return content.replaceFirst("\\"+getPrefix(), "");
    	return content.split(" ")[0].replaceFirst("\\"+getPrefix(), "");
    }
    
    private String getPrefix() {
    	return prefix;
    }
    
}
