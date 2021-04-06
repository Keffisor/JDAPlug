package com.Keffisor21.JDAExpansion.NMS;

import java.util.ArrayList;
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
}
