package com.jdaplug.nms;

import java.util.ArrayList;
import java.util.List;

public class JDANMS {
	private final JDAType type;
	private final List<Object> registeredListeners = new ArrayList<>();
	
	public JDANMS(JDAType type) {
		this.type = type;
	}
	
	public void addEventListener(Object o) {
		  if(type.jda != null) {
			  type.jda.addEventListener(o);
		  }
		  if(type.shardManager != null) {
			  registeredListeners.add(o);
			  type.shardManager.addEventListener(o);
		  }
	}
	public void addEventsListener(Object... o) {
		  if(type.jda != null) {
			  for (Object object : o) {
				  type.jda.addEventListener(o);
			  }
		  }
		  if(type.shardManager != null) {
			  for (Object object : o) {
				  registeredListeners.add(o);
			  	type.shardManager.addEventListener(o);
			  }
		  }
	}

	public void removeEventListener(Object o) {
		if(type.jda != null) {
			  type.jda.removeEventListener(o);
		  }
		  if(type.shardManager != null) {
			  registeredListeners.remove(o);
			  type.shardManager.removeEventListener(o);
		  }
	}
	
	public List<Object> getEventManager() {
		if(type.jda != null) {
			  return type.jda.getEventManager().getRegisteredListeners();
		  }
		  if(type.shardManager != null) {
			  return registeredListeners;
		  }
		  return null;
	}
	
	public void awaitReady() throws InterruptedException {
		if(type.jda != null) {
			type.jda.awaitReady();
		 }
	}

	public Object getType() {
		//Type that can return: JDA or ShardManager. You can detect it with instanceof
		if(type.jda != null) return type.jda;
		return type.shardManager;
	}
}
