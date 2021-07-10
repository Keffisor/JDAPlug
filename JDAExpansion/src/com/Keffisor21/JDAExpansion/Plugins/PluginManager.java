package com.Keffisor21.JDAExpansion.Plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.ConfigManager.PluginConfigurationObject;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.Exception.InvalidPluginYML;
import com.Keffisor21.JDAExpansion.Exception.MainNotFound;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;
import com.Keffisor21.JDAExpansion.Reflection.ClassPathLoader;

public class PluginManager {
	public ConcurrentHashMap<String, Plugin> registedClass = new ConcurrentHashMap<String, Plugin>();
	public List<Plugin> loadedPlugins = new ArrayList<Plugin>();
	public static ClassLoader previusClassLoader = null;
	
	public PluginManager() {
		
	}
	
	public List<Plugin> getInstalledPlugins() {
		return loadedPlugins;
	}
	
	public void loadPlugins(JDANMS jda) {
		File f = createPluginsDirectory();
		//clear data
		unloadPlugins(jda);
		
		List<File> fList = Arrays.asList(f.listFiles());
		if(fList.isEmpty()) return;
				 
		
		getObjectsWithSync(doFilterList(fList)).forEach((o, getClassInf) -> {
			try {
			
			String classMain = getClassInf.main;
			String name = getClassInf.name;
			File f2 = getClassInf.file;
			
			if(classMain == null) return;
			if(name == null) return;
			
				        if(o instanceof PluginListener) {
				        	if(registedClass.get(name) != null) {
				        		Console.info(ConsoleColor.RED_BRIGHT, "There is already a plugin with the same name \""+name+"\"");
				        		return;
				        	}
					        PluginConfigurationObject.getPluginInformation.put(o, getClassInf);
				        	PluginListener lPluginListener = (PluginListener)o;
				        	Console.info(ConsoleColor.YELLOW_BRIGHT, String.format("[%s] Loading %s %s", getClassInf.getName(), getClassInf.getName(), getClassInf.getVersion()));
				        	try {
				        	lPluginListener.onEnable();
				        	jda.addEventListener(lPluginListener);
				        	} catch(Exception | NoClassDefFoundError | NoSuchMethodError e) {
			    				Utils.printStackTrace(e);
				        		
				        		//return;
				        	}
				        	//create plugin
				    		initPlugin(f2, getClassInf, lPluginListener);
				        }
 		      
			} catch(Exception e) {
				Utils.printStackTrace(e);
			}
		});
		
		//make the sync again for debug
		/*getInstalledPlugins().stream().map(Plugin::getFile).forEach(t -> {
			try {
				syncClassPlugin(t);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		});*/
		
		JDAExpansion.registratedClassPlugin.forEach(jda::addEventListener);
	}
	
	private Stream<PluginConfigurationObject> doFilterList(List<File> fileList) {
		return fileList.stream().filter(this::hasExtensionJar).map(this::getMainClass).filter(classInfo -> classInfo.main != null && classInfo.name != null);
	}
	
	private File createPluginsDirectory() {
		File f = new File("plugins");
		if(!f.exists()) {
			f.mkdir();
		}
		return f;
	}
	
	private Stream<PluginConfigurationObject> orderPluginsFilter (Stream<PluginConfigurationObject> classInfo) {
		
		LinkedList<PluginConfigurationObject> filtered = new LinkedList<>(classInfo.filter(r -> r.depends != null).collect(Collectors.toList()));
		
		Stream<PluginConfigurationObject> collec = classInfo.filter(r -> r.depends != null);
		HashMap<String, PluginConfigurationObject> map = new HashMap<String, PluginConfigurationObject>(collec.collect(Collectors.toMap(PluginConfigurationObject::getName, PluginConfigurationObject::getClazz)));
		
		collec.map(PluginConfigurationObject::getDependencies).forEach(d -> {
				PluginConfigurationObject plugin = map.get(d);
				if(plugin == null || filtered.contains(plugin)) {
					return;
				}
				 filtered.add(plugin);
			});
		filtered.addAll(collec.filter(r -> !filtered.contains(r)).collect(Collectors.toList()));
		
		return filtered.stream();
	}
	
	private HashMap<Object, PluginConfigurationObject> getObjectsWithSync(Stream<PluginConfigurationObject> stream) {
		return (HashMap)stream.collect(Collectors.toMap(this::getObject, PluginConfigurationObject::getClazz));
	}
	
	private Object getObject(PluginConfigurationObject pluginInfo) {
	try {
         try {
			return Class.forName(pluginInfo.main, true, syncClassPlugin(pluginInfo.file)).newInstance();
		} catch (InstantiationException | IllegalAccessException  | MalformedURLException e) {
			Utils.printStackTrace(e);
		}
		} catch(ClassNotFoundException e) {
  			new MainNotFound(pluginInfo.name, e).printStackTrace();
		}
	return null;
	}
	
	private URLClassLoader syncClassPlugin(File f2) throws MalformedURLException {
		ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
		URLClassLoader child = new URLClassLoader (new URL[] {new URL("file:///"+f2.getAbsolutePath())}, currentThreadClassLoader);
		//start sync class
		ClassPathLoader classPathLoader = new ClassPathLoader(f2.toURL());
		classPathLoader.addURL();
		return child;
	}
	
	private URLClassLoader getSync(File f2, ClassLoader currentThreadClassLoader) throws MalformedURLException {
		URLClassLoader child = new URLClassLoader (new URL[] {new URL("file:///"+f2.getAbsolutePath())}, currentThreadClassLoader);
        return child;
	}
	
	
	private void initPlugin(File f2, PluginConfigurationObject getClassInf, PluginListener o) {
		Plugin plugin = new Plugin(f2, f2.getPath(), getClassInf.name, o, getClassInf.author, getClassInf.description, getClassInf.version, getClassInf.depends);
		loadedPlugins.add(plugin);
    	registedClass.put(plugin.getName(), plugin);

		if(plugin.getAuthor() == null)
    	Console.info(ConsoleColor.GREEN_BRIGHT, String.format("[%s] %s %s has been loaded successfully", plugin.getName(), plugin.getName(), plugin.getVersion()));
		else
	    	Console.info(ConsoleColor.GREEN_BRIGHT, String.format("[%s] %s %s by %s has been loaded successfully", plugin.getName(), plugin.getName(), plugin.getVersion(), plugin.getAuthor()));
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
			    	Console.info(ConsoleColor.RED_BRIGHT, String.format("%s %s has been unloaded successfully", plugin.getName(), plugin.getVersion()));
					else
				 Console.info(ConsoleColor.RED_BRIGHT, String.format("%s %s by %s has been unloaded successfully", plugin.getName(), plugin.getVersion(), plugin.getAuthor()));
			} catch(Exception e) {
				Utils.printStackTrace(e);
        	}
			});
		}
		
		//clear sync
		try {
		ClassPathLoader.clearPaths();
		ClassPathLoader.clearURLs();

		} catch(Throwable e) {
			e.printStackTrace();
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
			   Console.info(ConsoleColor.RED_BRIGHT, "The plugin.yml was not found in "+f.getName());
				return null;	
			}
			
	        InputStream is = zipFile.getInputStream(zipEntry);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        PluginConfigurationObject result = extract(f, reader.lines());
	        
	        if(result.main != null && result.name != null) {
	        		return result;
	         
	        } else {
                new InvalidPluginYML(null).printStackTrace();
                return null;
	        	//Console.logger.info(ConsoleColor.RED_BRIGHT+"The plugin.yml of "+f.getName()+" is invalid"+ConsoleColor.RESET);
	        }
	        
	    } catch (IOException e) {
			Console.info(ConsoleColor.RED_BRIGHT, "No se pudo cargar "+f.getName());
			return null;
		}
		
	}
	private PluginConfigurationObject extract(File f, Stream<String> list) {
		
 		String name = null, main = null, author = null, description = null, version = null;
		List<String> depends = null;
		
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
			if(string.contains("depend")) {
				string = string.replace(" ", "").replace("[", "").replace("]", "");
				if(string.contains(",")) {
				String[] split = string.split(",");
				 depends = Arrays.asList(split);
				} else {
					depends = Arrays.asList(string);
				}
			}
		}
		return new PluginConfigurationObject(f, name, main, author, description, version, depends);
	}

}