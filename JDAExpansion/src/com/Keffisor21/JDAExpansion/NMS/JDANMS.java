package com.Keffisor21.JDAExpansion.NMS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class JDANMS {
	private JDAType type;
	private List<Object> registredListeners = new ArrayList<>();
	
	public JDANMS(JDAType type) {
		this.type = type;
	}
	
	public void addEventListener(Object o) {
		  if(type.jda != null) {
			  type.jda.addEventListener(o);
		  }
		  if(type.shardManager != null) {
			  registredListeners.add(o);
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
				  registredListeners.add(o);
			  	type.shardManager.addEventListener(o);
			  }
		  }
	}

	public void removeEventListener(Object o) {
		if(type.jda != null) {
			  type.jda.removeEventListener(o);
		  }
		  if(type.shardManager != null) {
			  registredListeners.remove(o);
			  type.shardManager.removeEventListener(o);
		  }
	}
	
	public List<Object> getEventManager() {
		if(type.jda != null) {
			  return type.jda.getEventManager().getRegisteredListeners();
		  }
		  if(type.shardManager != null) {
			  return registredListeners;
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
