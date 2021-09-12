package com.Keffisor21.JDAExpansion.ConfigManager;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PluginConfigurationObject {
	
	public static ConcurrentHashMap<Object, PluginConfigurationObject> getPluginInformation = new ConcurrentHashMap<Object, PluginConfigurationObject>();
	public String name = null;
    public String main = null;
    public File file = null;
    public String author = null;
    public String description = null;
    public String version = null;
    public List<String> depends = null;
    
	public PluginConfigurationObject(File file, String name, String main, String author, String description, String version, List<String> depends) {
		this.name = name;
		this.main = main;
		this.author = author;
		this.description = description;
		this.version = version;
		this.file = file;
		this.depends = depends;
	}	
	public String getName() {
		return name;
	}
	public List<String> getDependencies() {
		return depends;
	}
	public PluginConfigurationObject getClazz() {
		return this;
	}
	public File getFile() {
		return file;
	}
	public String getVersion() {
		if(version == null) return "v1.0";
		return version;
	}
}
