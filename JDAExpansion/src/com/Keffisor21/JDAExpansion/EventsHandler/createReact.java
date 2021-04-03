package com.Keffisor21.JDAExpansion.EventsHandler;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class createReact extends ListenerAdapter {
    String emote = null;
    MessageReactionAddEvent event = null;
    
    public createReact(String emote) {
    	this.emote = emote;
    }
    
     @Override
	 public void onMessageReactionAdd(MessageReactionAddEvent e) {
       if(e.getReactionEmote().getName().replace("RE:", ":").equals(emote)) {
    	   if(!e.getMember().getUser().isBot()) {
    	   event = e;
    	   isReacted();
       }
       }
     }   
     
	public void isReacted() {}
	
	public MessageReactionAddEvent eventReact() {
		return this.event;
	}
}
