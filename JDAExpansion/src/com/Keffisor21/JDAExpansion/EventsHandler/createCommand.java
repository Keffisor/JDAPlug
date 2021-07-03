package com.Keffisor21.JDAExpansion.EventsHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.Keffisor21.JDAExpansion.JDAExpansion;

import ch.qos.logback.core.joran.conditional.ElseAction;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command; 

public abstract class createCommand extends ListenerAdapter {
    private String command;
    private String contentRaw = "";
    private List<String> aliases = new ArrayList<String>();
    private CommandEvent event = null;
    private String prefix;
    
    public createCommand(String prefix, String cmd, String... args) {
    	this.prefix = prefix;
    	this.command = prefix+cmd;
    	if(args.length != 0) {
    	this.aliases = Arrays.asList(args);
    	}
    }
   
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
    	if(e.getAuthor().isBot()) return;
      if(isCommand(e.getMessage().getContentRaw(), command) || getAliases(e.getMessage().getContentRaw(), false)) {
    	  
    	  contentRaw = e.getMessage().getContentRaw();
    	  event = new CommandEvent(e);
    	  
     	  isExecuted(Args(), event);
      }
    }
   public boolean onConsoleMessageReceived(String content) {
	   if(isCommand(content, command.replaceFirst("\\"+prefix, "")) || getAliases(content, true)) {
	    contentRaw = content;
	   isExecutedConsole(Args());
	   return true;
	      }
	   return false;
   }
   
   protected abstract void isExecuted(String[] args, CommandEvent e);
   protected abstract void isExecutedConsole(String[] args);
   
    private String[] Args() {
    	if(contentRaw.replace("  ", " ").split(" ").length != 0) {
    		return contentRaw.replace("  ", " ").split(" ");
    	}
    	return null;
    }
    public CommandEvent eventCommand() {
    	return this.event;
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
}
