package com.Keffisor21.JDAExpansion.Plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.Keffisor21.Exception.InvalidPluginYML;
import com.Keffisor21.JDAExpansion.ConfigManager.PluginConfigurationObject;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;

public class PluginManager {
	public ConcurrentHashMap<String, Plugin> registedClass = new ConcurrentHashMap<String, Plugin>();
	public List<Plugin> loadedPlugins = new ArrayList<Plugin>();
	public static ClassLoader previusClassLoader = null;
	
	public PluginManager() {
		
	}
	
	public List<Plugin> getPlugins() {
		return loadedPlugins;
	}
	
	public void loadPlugins(JDANMS jda) {
		File f = new File("plugins");
		if(!f.exists()) {
			f.mkdir();
		}
		//clear data
		unloadPlugins(jda);
		
		List<File> fList = Arrays.asList(f.listFiles());
		if(fList.isEmpty()) return;
		fList.stream().filter(s -> hasExtensionJar(s)).collect(Collectors.toList())
		.forEach(f2 -> {
			try {
			PluginConfigurationObject getClassInf = getMainClass(f2);
			
			String classMain = getClassInf.main;
			String name = getClassInf.name;
			
			if(classMain == null) return;
			if(name == null) return;
			
 		     	try {
 				        //instance convert Class<?> to Object
				        Object o = Class.forName(classMain, true, syncClassPlugin(f2)).newInstance();
				        if(o instanceof PluginListener) {
				        	if(registedClass.get(name) != null) {
				        		Console.logger.info(ConsoleColor.RED_BRIGHT+"There is already a plugin with the same name \""+name+"\""+ConsoleColor.RESET);
				        		return;
				        	}
					        PluginConfigurationObject.getPluginInformation.put(o, getClassInf);
				        	PluginListener lPluginListener = (PluginListener)o;
				        	jda.addEventListener(lPluginListener);
				        	try {
				        	lPluginListener.onEnable();
				        	} catch(Exception e) {
				        		if(JDAExpansion.DEBUG) 
				        		e.printStackTrace();
				        		else
				        			JDAExpansion.getLogger().info(e.getMessage());
				        	}
				        	//create plugin
				    		initPlugin(f2, getClassInf, lPluginListener);

				        }
				        
			} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
				if(JDAExpansion.DEBUG)
				e.printStackTrace();
			}  
			} catch(Exception e) {
				if(JDAExpansion.DEBUG)
				e.printStackTrace();
			}
		});
		JDAExpansion.registratedClassPlugin.forEach(jda::addEventListener);
	}
	
	private URLClassLoader syncClassPlugin(File f2) throws MalformedURLException {
		ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
		URLClassLoader child = new URLClassLoader (
		new URL[] {new URL("file:///"+f2.getAbsolutePath())}, currentThreadClassLoader);
    	Thread.currentThread().setContextClassLoader(child);
        return child;
	}
	
	private void initPlugin(File f2, PluginConfigurationObject getClassInf, PluginListener o) {
		Plugin plugin = new Plugin(f2.getPath(), getClassInf.name, o, getClassInf.author, getClassInf.description, getClassInf.version);
		loadedPlugins.add(plugin);
    	registedClass.put(plugin.getName(), plugin);

		if(plugin.getAuthor() == null)
    	Console.logger.info(ConsoleColor.GREEN_BRIGHT+String.format("%s %s has been loaded successfully", plugin.getName(), plugin.getVersion())+ConsoleColor.RESET);
		else
	    	Console.logger.info(ConsoleColor.GREEN_BRIGHT+String.format("%s %s by %s has been loaded successfully", plugin.getName(), plugin.getVersion(), plugin.getAuthor())+ConsoleColor.RESET);
	}
	
	public void unloadPlugins(JDANMS jda) {
		if(!registedClass.isEmpty()) {
			registedClass.forEach((k, v) -> {
				Plugin plugin = v;
				PluginListener pluginListener = (PluginListener)plugin.getMainClass();
				jda.removeEventListener(pluginListener);
			try {
				pluginListener.onDisable();
				if(plugin.getAuthor() == null)
			    	Console.logger.info(ConsoleColor.RED_BRIGHT+String.format("%s %s has been unloaded successfully", plugin.getName(), plugin.getVersion())+ConsoleColor.RESET);
					else
				 Console.logger.info(ConsoleColor.RED_BRIGHT+String.format("%s %s by %s has been unloaded successfully", plugin.getName(), plugin.getVersion(), plugin.getAuthor())+ConsoleColor.RESET);
			} catch(Exception e) {
				if(JDAExpansion.DEBUG)
        		e.printStackTrace();
        	}
			});
		}
		JDAExpansion.registratedClassPlugin.forEach(jda::removeEventListener);
		registedClass.clear();
		PluginConfigurationObject.getPluginInformation.clear();
		JDAExpansion.registratedClassPlugin.clear();
		loadedPlugins.clear();
		
		if(previusClassLoader == null) {
			previusClassLoader = Thread.currentThread().getContextClassLoader();
		} else {
			Thread.currentThread().setContextClassLoader(previusClassLoader);
		}
		
	}
	
	private boolean hasExtensionJar(File f) {
		if(f.isDirectory()) return false;
		String name = f.getName();
		if(!name.contains(".jar")) return false;
		return true;
	}
	private PluginConfigurationObject getMainClass(File f) {
	    try {
			ZipFile zipFile = new ZipFile("plugins/"+f.getName());
			ZipEntry zipEntry = zipFile.getEntry("plugin.yml");
			if(zipEntry == null) {
			   Console.logger.info(ConsoleColor.RED_BRIGHT+"The plugin.yml was not found in "+f.getName()+ConsoleColor.RESET);
				return null;	
			}
			
	        InputStream is = zipFile.getInputStream(zipEntry);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        PluginConfigurationObject result = extract(f.getName(), reader.lines());
	        
	        if(result.main != null && result.name != null) {
	        		return result;
	         
	        } else {
	        	throw new InvalidPluginYML(null);
	        	//Console.logger.info(ConsoleColor.RED_BRIGHT+"The plugin.yml of "+f.getName()+" is invalid"+ConsoleColor.RESET);
	        }
	        
	    } catch (IOException e) {
			Console.logger.info(ConsoleColor.RED_BRIGHT+"No se pudo cargar "+f.getName()+ConsoleColor.RESET);
			return null;
		}
		
	}
	private PluginConfigurationObject extract(String nameJar, Stream<String> list) {
 		String name = null;
		String main = null;
		String author = null;
		String description = null;
		String version = null;
		
		for (String string : list.collect(Collectors.toList())) {
			if(string.contains("main: ")) {
				main = string.replace("main: ", "");
			}
			if(string.contains("name: ")) {
				name = string.replace("name: ", "");
			}
			if(string.contains("author")) {
				author = string.replace("author: ", "");
			}
			if(string.contains("description")) {
				description = string.replace("description: ", "");
			}
			if(string.contains("version")) {
				version = string.replace("version: ", "");
			}
		}
		PluginConfigurationObject configurationObject = new PluginConfigurationObject(name, main, author, description, version, nameJar);
		return configurationObject;
	}

}
