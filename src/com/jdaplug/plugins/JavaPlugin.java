package com.jdaplug.plugins;

import java.io.File;
import java.util.stream.Collectors;

import com.jdaplug.JDAPlug;
import com.jdaplug.configmanager.FileConfiguration;
import com.jdaplug.configmanager.PluginConfigurationObject;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class JavaPlugin extends ListenerAdapter {
	
	   protected abstract void onEnable();
	   protected abstract void onDisable();
	   
	   public FileConfiguration getConfig(String configName) {
		   if(!configName.contains(".yml")) {
			   configName = configName+".yml";
		   }
 			return new FileConfiguration(getPluginFile(), JDAPlug.getAbsolutePath()+"/plugins/"+getPluginName(), configName, this);
	   }
	   
	   public String getPluginName() {
		   return getPluginConfigurationObject().name;
	   }
	   
	   private PluginConfigurationObject getPluginConfigurationObject() {
		   String main = this.getClass().getName();
		   return PluginConfigurationObject.filteredList.stream().filter(o -> o.main.equals(main)).collect(Collectors.toList()).get(0);
	   }
	   
	   public File getPluginFile() {
		   return getPluginConfigurationObject().file;
	   }
}
