package com.Keffisor21.JDAExpansion.EventsHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter; 

public abstract class createCommand extends ListenerAdapter {
    private String command;
    private String contentRaw = "";
    private List<String> aliases = new ArrayList<String>();
    private MessageReceivedEvent event = null;
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
      if(e.getMessage().getContentRaw().startsWith(command) || getAliases(e.getMessage().getContentRaw(), false)) {
    	  contentRaw = e.getMessage().getContentRaw();
    	  event = e;
     	  isExecuted(Args());
      }
    }
   public boolean onConsoleMessageReceived(String content) {
	   if(content.startsWith(command.replaceFirst("!", "")) || getAliases(content, true)) {
	    contentRaw = content;
	   isExecutedConsole(Args());
	   return true;
	      }
	   return false;
   }
   protected abstract void isExecuted(String[] args);
   protected abstract void isExecutedConsole(String[] args);
   
    private String[] Args() {
    	if(contentRaw.replace("  ", " ").split(" ").length != 0) {
    		return contentRaw.replace("  ", " ").split(" ");
    	}
    	return null;
    }
    public MessageReceivedEvent eventCommand() {
    	return this.event;
    }
    private boolean getAliases(String contentRaw, boolean console) {
    if(this.aliases.isEmpty()) {
    	return false;
    }
    if(contentRaw.isEmpty()) {
    	return false;
    }
    	return !this.aliases.stream().filter(sT -> {if(!console) {sT = prefix+sT;}return contentRaw.startsWith(sT);}).collect(Collectors.toList()).isEmpty();
    }
}
