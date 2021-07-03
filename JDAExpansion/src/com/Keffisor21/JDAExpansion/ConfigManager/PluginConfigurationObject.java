package com.Keffisor21.JDAExpansion.ConfigManager;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PluginConfigurationObject {
	
	public static ConcurrentHashMap<Object, PluginConfigurationObject> getPluginInformation = new ConcurrentHashMap<Object, PluginConfigurationObject>();
	public String name = null;
    public String main = null;
    public String jarName = null;
    public String author = null;
    public String description = null;
    public String version = null;
    public List<String> depends = null;
    
	public PluginConfigurationObject(String name, String main, String author, String description, String version, List<String> depends, String jarName) {
		this.name = name;
		this.main = main;
		this.author = author;
		this.description = description;
		this.version = version;
		this.jarName = jarName;
		this.depends = depends;
	}
}
