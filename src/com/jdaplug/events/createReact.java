package com.jdaplug.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Very legacy code
 */

public abstract class createReact extends ListenerAdapter {
	
    private final Message message;
    private final String emote;
    
    public createReact(Message me, String emote) {
    	this.message = me;
    	this.emote = emote;
    }
    
     @Override
	 public void onMessageReactionAdd(MessageReactionAddEvent e) {
    	 if(e.getMember().getUser().isBot()) return;
    	 if(e.getReaction().getEmoji().getName().replace("RE:", ":").equals(emote)) {
    		 if(message != null && e.getMessageId().equals(message.getId())) {
    			 addReaction(e);
    			 return;
    		 }
    		 addReaction(e);
    	 }
     }
     
     @Override
     public void onMessageReactionRemove(MessageReactionRemoveEvent e) {
    	 if(e.getMember().getUser().isBot()) return;
    	 if(e.getReaction().getEmoji().getName().replace("RE:", ":").equals(emote)) {
    		 if(message != null && e.getMessageId().equals(message.getId())) {
    			 removeReaction(e);
    			 return;
    		 }
    		 removeReaction(e);
    	 }
     }
     
	protected abstract void addReaction(MessageReactionAddEvent event);
	protected abstract void removeReaction(MessageReactionRemoveEvent event);
	
}
