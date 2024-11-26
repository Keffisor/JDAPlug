package com.jdaplug.configmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Nullable;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.jdaplug.consolehandler.Console;
import com.jdaplug.consolehandler.ConsoleColor;

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
			options.setProcessComments(true);

			yaml = new Yaml(options);

			if(!isValid(file, o, nameFile)) {
				try {
					extractConfig(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			InputStream fileInputStream = new FileInputStream(file);
			data = yaml.load(fileInputStream);
			fileInputStream.close();
			
			if(data == null) data = new HashMap<String, Object>();
			
			saveConfig();

		} catch(IOException e2) { 
			Console.info(ConsoleColor.RED_BRIGHT, "Error on creating the file " + file.getAbsolutePath());
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

	/**
	 * Saves the current configuration to the file.
	 */
	 public void saveConfig() {
		formatConfig();
		try {
			StringWriter writer = new StringWriter();
			yaml.dump(data, writer);

			writer = commentsConfig(writer);

			BufferedWriter bw = java.nio.file.Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
			bw.write(writer.toString());

			bw.close();
	    } catch(IOException e2) {
	    	e2.printStackTrace();
	    }
	}

	/**
	 * Reloads the configuration from the file.
	 */
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
	
	/**
	 * Set the proper format to the config. Ex: Replace a dot with map.
	 */
	private void formatConfig() {
		new LinkedHashSet<String>(data.keySet()).stream().filter(s -> s.contains(".")).forEach(key -> {
			List<String> paths = Stream.of(key.split("\\.")).collect(Collectors.toList());

			String start = paths.get(0);
			String end = paths.get(paths.size() - 1);
			
			paths.remove(start);
			paths.remove(end);
			
			LinkedHashMap<String, Object> initial = (data.get(start) == null ? new LinkedHashMap<>(): (LinkedHashMap<String, Object>) data.get(start));
			
			Map<String, Object> tmp = null;
			for(String path : paths) {
				if(tmp == null) {
					if(initial.get(path) == null) initial.put(path, new LinkedHashMap<>());
					tmp = (LinkedHashMap<String, Object>) initial.get(path);
					continue;
				}
				if(tmp.get(path) == null) tmp.put(path, new LinkedHashMap<>());
				tmp = (LinkedHashMap<String, Object>) tmp.get(path);
			}
			
			(tmp == null ? initial: tmp).put(end, data.get(key));
			
			data.remove(key);
			data.put(start, initial);			
		});
	}

	/**
	 * Recover the comments in the yml file and apply them to the StringWriter so when the config it's overwritten the comments are not removed.
	 * @param writer The StringWriter to apply the comments
	 * @return The StringWriter with the comments applied
	 */
	private StringWriter commentsConfig(StringWriter writer) {
		List<String> lines = Stream.of(writer.toString().split("\n")).collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger();
		StringWriter linesRead = new StringWriter();

		try {
			Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8).forEach(line -> {
				if(line.isEmpty()) return;

				linesRead.append(line).append("\n");

				int pos = count.getAndIncrement();
				if(!line.trim().startsWith("#")) return;

				lines.add(pos + getFormattedNewLinesCount(linesRead.toString()), line);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}

		writer = new StringWriter();
		writer.append(String.join("\n", lines));

		return writer;
	}

	/**
	 * Sets the value for the specified key in the configuration data.
	 * If the key contains dots, it is treated as a path and the value is set in the nested map structure.
	 * Otherwise, the value is set directly in the root map.
	 *
	 * @param x the key for which the value is to be set. If the key contains dots, it is treated as a path.
	 * @param o the value to be set for the specified key.
	 */
	public void set(String x, Object o) {
		if(x.contains(".") && data.get(x.split("\\.")[0]) != null) {
		    setElementMap(x, data.get(x), o);
		} else data.put(x, o);

		try {
			StringWriter writer = new StringWriter();
			yaml.dump(data, writer);
			BufferedWriter bw = java.nio.file.Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
			bw.write(writer.toString());
			bw.close();
	    } catch(IOException e2) {
	    	e2.printStackTrace();
	    }
	}

	/**
	 * Gets the boolean value for the specified key.
	 *
	 * @param x the key to look up
	 * @return the boolean value associated with the key
	 */
	public boolean getBoolean(String x) {
       return (boolean)getElementMap(x, data.get(x), data);		
	}

	/**
	 * Retrieves the string value associated with the specified key.
	 *
	 * @param x the key to look up
	 * @return the string value associated with the key, or null if not found
	 */
	@Nullable
	public String getString(String x) {
		return String.valueOf(getElementMap(x, data.get(x), data));
	}

	/**
	 * Retrieves the list of objects associated with the specified key.
	 *
	 * @param x the key to look up
	 * @return the list of objects associated with the key, or null if not found
	 */
	@Nullable
	public List<Object> getList(String x) {
		return (List<Object>) getElementMap(x, data.get(x), data);
	}

	/**
	 * Retrieves the list of strings associated with the specified key.
	 *
	 * @param x the key to look up
	 * @return the list of strings associated with the key, or null if not found
	 */
	@Nullable
	public List<String> getStringList(String x) {
		return (List<String>) getElementMap(x, data.get(x), data);
	}

	/**
	 * Retrieves the integer value associated with the specified key.
	 *
	 * @param x the key to look up
	 * @return the integer value associated with the key
	 */
	public int getInt(String x) {
		return (int) getElementMap(x, data.get(x), data);
	}

	/**
	 * Retrieves the double value associated with the specified key.
	 *
	 * @param x the key to look up
	 * @return the double value associated with the key
	 */
	public double getDouble(String x) {
		return (double) getElementMap(x, data.get(x), data);
	}

	private boolean isValid(File configFile, Object obj, String nameFile) {
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

	private int getFormattedNewLinesCount(String data) {
		DumperOptions options = new DumperOptions();

		options.setPrettyFlow(true);
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setAllowUnicode(true);

		Map<String, Object> map = new Yaml(options).load(data);

		if(map == null) return 0;

		new LinkedHashSet<String>(map.keySet()).stream().filter(s -> s.contains(".")).forEach(key -> {
			List<String> paths = Stream.of(key.split("\\.")).collect(Collectors.toList());

			String start = paths.get(0);
			String end = paths.get(paths.size() - 1);

			paths.remove(start);
			paths.remove(end);

			LinkedHashMap<String, Object> initial = (map.get(start) == null ? new LinkedHashMap<>(): (LinkedHashMap<String, Object>) map.get(start));

			Map<String, Object> tmp = null;
			for(String path : paths) {
				if(tmp == null) {
					if(initial.get(path) == null) initial.put(path, new LinkedHashMap<>());
					tmp = (LinkedHashMap<String, Object>) initial.get(path);
					continue;
				}
				if(tmp.get(path) == null) tmp.put(path, new LinkedHashMap<>());
				tmp = (LinkedHashMap<String, Object>) tmp.get(path);
			}

			(tmp == null ? initial: tmp).put(end, map.get(key));

			map.remove(key);
			map.put(start, initial);
		});

		StringWriter formatted = new StringWriter();
		yaml.dump(map, formatted);

		return formatted.toString().split("\n").length - data.split("\n").length + 1;
	}

	private Map<String, Object> setNewElementMap(List<String> paths, Object toChange) {
		String start = paths.get(0);
		String end = paths.get(paths.size() - 1);
		
		LinkedHashMap<String, Object> initial = (LinkedHashMap<String, Object>) data.get(start);
		
		paths.remove(0);
		paths.remove(paths.size() - 1);
		
		Object tmp = null;
		for(String path : paths) {
			if(tmp == null) {
				if(initial.get(path) == null) initial.put(path, new LinkedHashMap<>());
				tmp = initial.get(path);
				continue;
			}
			if(((LinkedHashMap) tmp).get(path) == null) ((LinkedHashMap) tmp).put(path, new LinkedHashMap<>());
			tmp = ((LinkedHashMap) tmp).get(path);
		}
		
		((LinkedHashMap<String, Object>) (tmp == null ? initial: tmp)).put(end, toChange);

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
			try(ZipFile zipFile = new ZipFile(jarFile)) {
				ZipEntry zipEntry = zipFile.getEntry(file.getName());
				
				if(zipEntry == null) {
				   Console.info(ConsoleColor.RED_BRIGHT, "The " + file.getName() + " was not found in " + jarFile.getName());
				   return null;	
				}

				if(write) FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipEntry), file);

				InputStream inputStream = zipFile.getInputStream(zipEntry);
				Map<String, Object> yaml = new Yaml().load(inputStream);
				inputStream.close();

				return yaml;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}