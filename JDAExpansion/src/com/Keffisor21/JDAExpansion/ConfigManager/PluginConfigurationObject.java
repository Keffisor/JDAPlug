package com.Keffisor21.JDAExpansion.ConfigManager;

import java.util.concurrent.ConcurrentHashMap;

public class PluginConfigurationObject {
	
	public static ConcurrentHashMap<Object, PluginConfigurationObject> getPluginInformation = new ConcurrentHashMap<Object, PluginConfigurationObject>();
	public String name = null;
    public String main = null;
    public String jarName = null;

	public PluginConfigurationObject(String name, String main, String jarName) {
		this.name = name;
		this.main = main;
		this.jarName = jarName;
	}
}
