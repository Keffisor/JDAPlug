package com.Keffisor21.JDAExpansion.ConfigManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.Plugins.JDAExpansion;
import com.google.api.Files;

public class FileConfiguration {
	private Yaml yaml;
	private Map<String, Object> data;
	private File file;
	
	public FileConfiguration(String route, String nameFile, Object o) {
		File directory = new File(route);
		if(!directory.exists()) {
			directory.mkdir();
			directory.setReadable(true, false);
			directory.setWritable(true, false);

		}
		this.file = new File(route+"/"+nameFile);
		try {
			
			DumperOptions options = new DumperOptions();
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
			yaml = new Yaml(options);

	//		InputStream is = o.getClass().getClassLoader().getResourceAsStream(nameFile);
	//		Files.write(file, IOUtils.toString(is, StandardCharsets.UTF_8));
		   		
			if(!isValid(file, o, nameFile)) {
				InputStream is = o.getClass().getClassLoader().getResourceAsStream(nameFile);
				Files.write(file, IOUtils.toString(is, StandardCharsets.UTF_8));
			}
			
			data = yaml.load(new FileInputStream(file));

			Writer writer = new FileWriter(file.getAbsoluteFile());
			yaml.dump(data, writer);
	    	writer.close();	

		
		} catch(IOException e2) { 
			Console.logger.info(ConsoleColor.RED_BRIGHT+"Error on creating the file "+file.getAbsolutePath()+ConsoleColor.RESET);
			e2.printStackTrace();
		}
	}
	public String getPath() {
		return this.file.getPath();
	}
	public Object get(String x) {
		return getElementMap(x, data.get(x), data);
	}
	public Map<String, Object> getElements() {
		return data;
	}
	public void reloadConfig() {
	try {
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		yaml = new Yaml(options);
		data = yaml.load(new FileInputStream(file));
	 } catch (FileNotFoundException e) {
		e.printStackTrace();
	 }
  }
	public void set(String x, Object o) {
	    data.put(x, o);
	    try {
	    Writer writer = new FileWriter(file.getAbsoluteFile());
	    yaml.dump(data, writer);
	    writer.close();
	    } catch(IOException e2) {
	    	e2.printStackTrace();
	    }
	}
	
	public boolean getBoolean(String x) {
       return (boolean)getElementMap(x, data.get(x), data);		
	}
	public String getString(String x) {
		return String.valueOf(getElementMap(x, data.get(x), data));
	}
	public List<Object> getList(String x) {
		return (List<Object>)getElementMap(x, data.get(x), data);
	}
	public int getInt(String x) {
		return (int)getElementMap(x, data.get(x), data);
	}
	public double getDouble(String x) {
		return (double)getElementMap(x, data.get(x), data);
	}
	
	public boolean isValid(File configFile, Object obj, String nameFile) {
		try {
		if(!configFile.exists()) {
			configFile.createNewFile();
			configFile.setReadable(true, false);
			configFile.setWritable(true, false);

			return false;
		}
		Map<String, Object> first = new Yaml().load(new FileInputStream(configFile.getAbsolutePath()));
		Map<String, Object> second = new Yaml().load(obj.getClass().getClassLoader().getResourceAsStream(nameFile));
		
		List<String> firstNames = new ArrayList<String>();
		List<String> secondNames = new ArrayList<String>();
		if(first == null || first.isEmpty()) return false;
		
		first.forEach((k, v) -> {firstNames.add(k);});
		second.forEach((k, v) -> {secondNames.add(k);});
		
		for (String s : secondNames) {
			if(!firstNames.contains(s)) {
				return false;
			}
		}
		return true;
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	private static Object getElementMap(String req, Object x, Map<String, Object> data) {
		if(req.contains(".")) {
			String[] split = (req.replace(".", ":").split(":"));
			List<String> list = new ArrayList<>(Arrays.asList(split));
			list.remove(split[0]);
			final Object object = getLastElement(list, data.get(split[0]));
			return object;
		}
		return x;
	}
	private static Object getLastElement(List<String> each, Object o) {
		if(!(o instanceof Map)) return o;
		if(each.size() == 0) return o;
		String S = each.get(0);
		List<String> nL = new ArrayList<>(each);
		nL.remove(S);
		return getLastElement(nL, ((Map)o).get(S));
	}
}
