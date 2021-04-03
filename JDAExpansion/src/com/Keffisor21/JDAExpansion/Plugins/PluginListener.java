package com.Keffisor21.JDAExpansion.Plugins;

import com.Keffisor21.JDAExpansion.ConfigManager.FileConfiguration;
import com.Keffisor21.JDAExpansion.ConfigManager.PluginConfigurationObject;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class PluginListener extends ListenerAdapter {
	
	   protected abstract void onEnable();
	   protected abstract void onDisable();
		
	   public FileConfiguration getConfig(String configName) {
		   if(!configName.contains(".yml")) {
			   configName = configName+".yml";
		   }
 			return new FileConfiguration(JDAExpansion.getAbsolutePath()+"/plugins/"+getPluginName(), configName, this);
	   }
	   
	   public String getPluginName() {
		  /* ConcurrentHashMap<String, PluginListener> plugins = Plugin.registedClass;
		   for (Map.Entry<String, PluginListener> entry : plugins.entrySet()) {
			   String name = entry.getKey();
			   Object object = entry.getValue();
			   if(o.equals(object)) return name;
		   }
		   return "Unknown";^*/
		   return PluginConfigurationObject.getPluginInformation.get(this).name;
	   }
}
