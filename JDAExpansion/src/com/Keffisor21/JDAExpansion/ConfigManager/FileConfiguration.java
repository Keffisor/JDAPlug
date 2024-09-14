package com.Keffisor21.JDAExpansion.ConfigManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Nullable;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class FileConfiguration {
	private Yaml yaml;
	private Map<String, Object> data;
	private final File file;
	private final File jarFile;
	
	public FileConfiguration(File jarFile, String route, String nameFile, Object o) {
		this.jarFile = jarFile;

		File directory = new File(route);
		if(!directory.exists()) {
			directory.mkdir();
			directory.setReadable(true, false);
			directory.setWritable(true, false);

		}

		this.file = new File(route + "/" + nameFile);
		
		try {

			DumperOptions options = new DumperOptions();
			options.setPrettyFlow(true);
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
			options.setAllowUnicode(true);
			yaml = new Yaml(options);

	//		InputStream is = o.getClass().getClassLoader().(nameFile);
	//		Files.write(file, IOUtils.toString(is, StandardCharsets.UTF_8));

			if(!isValid(file, o, nameFile)) {
				try {
					extractConfig(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			data = yaml.load(new FileInputStream(file));
			
			if(data == null) data = new HashMap<String, Object>();
			
			StringWriter writer = new StringWriter();
			yaml.dump(data, writer);

			BufferedWriter bw = java.nio.file.Files.newBufferedWriter(file.toPath(), Charset.forName("UTF-8"));
			bw.write(writer.toString());
			bw.close();

		} catch(IOException e2) { 
			Console.info(ConsoleColor.RED_BRIGHT, "Error on creating the file "+file.getAbsolutePath());
			e2.printStackTrace();
		}
	}
	
	public String getPath() {
		return this.file.getPath();
	}
	
	@Nullable
	public Object get(String x) {
		return getElementMap(x, data.get(x), data);
	}
	
	public Map<String, Object> getElements() {
		return data;
	}
	
	public void saveConfig() {
		try {
			StringWriter writer = new StringWriter();
			yaml.dump(data, writer);
			BufferedWriter bw = java.nio.file.Files.newBufferedWriter(file.toPath(), Charset.forName("UTF-8"));
			bw.write(writer.toString());
			bw.close();
	    } catch(IOException e2) {
	    	e2.printStackTrace();
	    }
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
		if(x.contains(".") && data.get(x.split("\\.")[0]) != null) {
		    setElementMap(x, data.get(x), o);
		} else data.put(x, o);

		try {
			StringWriter writer = new StringWriter();
			yaml.dump(data, writer);
			BufferedWriter bw = java.nio.file.Files.newBufferedWriter(file.toPath(), Charset.forName("UTF-8"));
			bw.write(writer.toString());
			bw.close();
	    } catch(IOException e2) {
	    	e2.printStackTrace();
	    }
	}
	
	@Nullable
	public boolean getBoolean(String x) {
       return (boolean)getElementMap(x, data.get(x), data);		
	}
	
	@Nullable
	public String getString(String x) {
		return String.valueOf(getElementMap(x, data.get(x), data));
	}
	
	@Nullable
	public List<Object> getList(String x) {
		return (List<Object>)getElementMap(x, data.get(x), data);
	}
	
	@Nullable
	public List<String> getStringList(String x) {
		return (List<String>)getElementMap(x, data.get(x), data);
	}
	
	@Nullable
	public int getInt(String x) {
		return (int)getElementMap(x, data.get(x), data);
	}
	
	@Nullable
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
		Map<String, Object> second = extractConfig(false);

		List<String> firstNames = new ArrayList<String>();
		List<String> secondNames = new ArrayList<String>();
		if(first == null || second == null || first.isEmpty() || second.isEmpty()) return true;

		first.forEach((k, v) -> {firstNames.add(k);});
		second.forEach((k, v) -> {secondNames.add(k);});
				
		for (String s : secondNames) {
			if(!firstNames.contains(s)) {
				data = first;
				second.forEach((k, v) -> {
					data.put(k, v);
				});
				saveConfig();
				return false;
			}
		}
		
		return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private Object getElementMap(String req, Object x, Map<String, Object> data) {
		if(req.contains(".")) {
			String[] split = (req.replace(".", ":").split(":"));
			List<String> list = new ArrayList<>(Arrays.asList(split));
			list.remove(split[0]);
			final Object object = getLastElement(list, data.get(split[0]));
			return object;
		}
		return x;
	}
	
	private Object getLastElement(List<String> each, Object o) {
		if(!(o instanceof Map)) return o;
		if(each.size() == 0) return o;
		String S = each.get(0);
		List<String> nL = new ArrayList<>(each);
		nL.remove(S);
		return getLastElement(nL, ((Map)o).get(S));
	}
	
	private void setElementMap(String req, Object x, Object toChange) {
		if(req.contains(".")) {
			String[] split = (req.split("\\."));
			String s = split[0];

			Map<String, Object> object = setNewElementMap(Stream.of(split).collect(Collectors.toList()), toChange);

		    data.put(s, object);
		    return;
		}
		data.put(req, x);
	}
	
	private Map<String, Object> setNewElementMap(List<String> paths, Object toChange) {
		String start = paths.get(0);
		String end = paths.get(paths.size() - 1);
		
		Map<String, Object> initial = (Map<String, Object>) data.get(start);
		
		paths.remove(0);
		paths.remove(paths.size() - 1);
		
		Object tmp = null;
		for(String path : paths) {
			if(tmp == null) {
				tmp = initial.get(path);
				continue;
			}
			
			tmp = ((Map<String, Object>) tmp).get(path);
		}
		
		((Map<String, Object>) tmp).put(end, toChange);
				
		return initial;
	}
	
	private LinkedList<Object> getAllElements(List<String> each, LinkedList<Object> comp, Object o) {
		comp.add(o);
		if(each.size() == 0) return comp;
		if(!(o instanceof Map)) return comp;
		String S = each.get(0);
		List<String> nL = new ArrayList<>(each);
		nL.remove(S);
		return getAllElements(nL, comp, ((Map)o).get(S));
	}
	
	private Map<String, Object> extractConfig(boolean write) {
		try {
		ZipFile zipFile = new ZipFile(jarFile);
		ZipEntry zipEntry = zipFile.getEntry(file.getName());
		if(zipEntry == null) {
		   Console.info(ConsoleColor.RED_BRIGHT, "The " + file.getName() + " was not found in " + jarFile.getName());
		   return null;	
		}
		if(write) {
			FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipEntry), file);
		}
		return new Yaml().load(zipFile.getInputStream(zipEntry));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}