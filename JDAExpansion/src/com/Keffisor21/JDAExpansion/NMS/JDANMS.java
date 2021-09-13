package com.Keffisor21.JDAExpansion.NMS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.DirectAudioController;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import okhttp3.OkHttpClient;

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
