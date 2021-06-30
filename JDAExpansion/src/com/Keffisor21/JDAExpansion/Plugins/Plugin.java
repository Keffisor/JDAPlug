package com.Keffisor21.JDAExpansion.Plugins;

import java.nio.file.Path;

public class Plugin {
    
	private String path;
	private String name;
	private PluginListener main;
	private String author = null;
	private String description = null;
	private String version = null;
	
	public Plugin(String path, String name, PluginListener main, String author, String description, String version) {
    	this.path = path;
    	this.name = name;
    	this.main = main;
    	this.author = author;
    	this.description = description;
    	this.version = version;
    }
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public PluginListener getMainClass() {
		return main;
	}
	
	public String getAuthor() {
	    return author;	
	}
	
	public String getDescription() {
	   return description;	
	}
	
	public String getVersion() {
		if(version == null) return "v1.0";
	  return version;	
	}	
}
