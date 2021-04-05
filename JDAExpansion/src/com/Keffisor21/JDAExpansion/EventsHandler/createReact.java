package com.Keffisor21.JDAExpansion.EventsHandler;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class createReact extends ListenerAdapter {
	
    private String emote = null;
    private MessageReactionAddEvent event = null;
    private final Message message;
    
    public createReact(String emote, Message me) {
    	this.emote = emote;
    	this.message = me;
    }
    
     @Override
	 public void onMessageReactionAdd(MessageReactionAddEvent e) {
       if(e.getReactionEmote().getName().replace("RE:", ":").equals(emote)) {
    	   if(!e.getMember().getUser().isBot()) {
    		  if(message != null && e.getMessageId().equals(message.getId())) {
    	       event = e;
    	      isReacted();
    		 }
       }
       }
     }   
     
	protected abstract void isReacted();
	
	public MessageReactionAddEvent eventReact() {
		return this.event;
	}
}
