package com.Keffisor21.JDAExpansion.Plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.Keffisor21.JDAExpansion.ConfigManager.PluginConfigurationObject;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;

public class Plugin {
	public static ConcurrentHashMap<String, PluginListener> registedClass = new ConcurrentHashMap<String, PluginListener>();
	
	public static void loadPlugins(JDANMS jda) {
		File f = new File("plugins");
		if(!f.exists()) {
			f.mkdir();
		}
		if(!registedClass.isEmpty()) {
			registedClass.forEach((k, v) -> {
				PluginListener pluginListener = (PluginListener)v;
				jda.removeEventListener(pluginListener);
			try {
				pluginListener.onDisable();
			} catch(Exception e) {
        		e.printStackTrace();
        	}
			});
		}
		//clear data
		JDAExpansion.registratedClassPlugin.forEach(jda::removeEventListener);
		registedClass.clear();
		PluginConfigurationObject.getPluginInformation.clear();
		JDAExpansion.registratedClassPlugin.clear();
		
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
 				URLClassLoader child = new URLClassLoader (
				        new URL[] {new URL("file:///"+f2.getAbsolutePath())}, Plugin.class.getClassLoader());
				        Class<?> cl = Class.forName(classMain, true, child);
				        Object o = cl.newInstance();
				        if(o instanceof PluginListener) {
				        	if(registedClass.get(name) != null) {
				        		Console.logger.info(ConsoleColor.RED_BRIGHT+"There is already a plugin with the same name \""+name+"\""+ConsoleColor.RESET);
				        		return;
				        	}
					        PluginConfigurationObject.getPluginInformation.put(o, getClassInf);
				        	PluginListener lPluginListener = (PluginListener)o;
				        	registedClass.put(name, lPluginListener);
				        	jda.addEventListener(lPluginListener);
				        	Console.logger.info(name+" has been loaded successfully");
				        	try {
				        	lPluginListener.onEnable();
				        	} catch(Exception e) {
				        		e.printStackTrace();
				        	}
				        }
				        
			} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
				JDAExpansion.getLogger().info(e.getMessage());
			}  

			} catch(Exception e) {
				JDAExpansion.getLogger().info(e.getMessage());
			}
		});
		JDAExpansion.registratedClassPlugin.forEach(jda::addEventListener);
	}
	public static boolean hasExtensionJar(File f) {
		if(f.isDirectory()) return false;
		String name = f.getName();
		if(!name.contains(".jar")) return false;
		return true;
	}
	public static PluginConfigurationObject getMainClass(File f) {
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
	        	Console.logger.info(ConsoleColor.RED_BRIGHT+"The plugin.yml of "+f.getName()+" is invalid"+ConsoleColor.RESET);
	           return null;
	        }
	        
	    } catch (IOException e) {
			Console.logger.info(ConsoleColor.RED_BRIGHT+"No se pudo cargar "+f.getName()+ConsoleColor.RESET);
			return null;
		}
		
	}
	public static PluginConfigurationObject extract(String nameJar, Stream<String> list) {
 		String name = null;
		String main = null;
		for (String string : list.collect(Collectors.toList())) {
			if(string.contains("main: ")) {
				main = string.replace("main: ", "");
			}
			if(string.contains("name: ")) {
				name = string.replace("name: ", "");
			}
		}
		PluginConfigurationObject configurationObject = new PluginConfigurationObject(name, main, nameJar);
		return configurationObject;
	}
}
