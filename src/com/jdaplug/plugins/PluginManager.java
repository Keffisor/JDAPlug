package com.jdaplug.plugins;

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.jdaplug.JDAPlug;
import com.jdaplug.configmanager.PluginConfigurationObject;
import com.jdaplug.consolehandler.Console;
import com.jdaplug.consolehandler.ConsoleColor;
import com.jdaplug.eventcontroller.EventsRegistration;
import com.jdaplug.exceptions.InvalidPluginYMLException;
import com.jdaplug.exceptions.MainNotFoundException;
import com.jdaplug.nms.JDANMS;

public class PluginManager {
    public ConcurrentHashMap<String, Plugin> registedClass = new ConcurrentHashMap<String, Plugin>();
    public List<Plugin> loadedPlugins = new ArrayList<Plugin>();
    private List<File> filteredFileList = new ArrayList<File>();
    public static ClassLoader previusClassLoader = null;

    public List<Plugin> getInstalledPlugins() {
        return loadedPlugins;
    }

    public void loadPlugins(JDANMS jda) {
        File f = createPluginsDirectory();
        //clear data
        unloadPlugins(jda);

        //unsafe to use the fList
        List<File> fList = Arrays.asList(f.listFiles());

        if (fList.isEmpty()) return;
        List<PluginConfigurationObject> filteredList = doFilterList(fList);
        PluginConfigurationObject.filteredList = filteredList;

        filteredFileList = filteredList.stream().map(PluginConfigurationObject::getFile).collect(Collectors.toList());

        getObjectsWithSync((filteredList)).forEach((o, getClassInf) -> {
            try {
                String classMain = getClassInf.main;
                String name = getClassInf.name;
                File f2 = getClassInf.file;

                if (classMain == null) return;
                if (name == null) return;

                if (o instanceof JavaPlugin) {
                    if (registedClass.get(name) != null) {
                        Console.info(ConsoleColor.RED_BRIGHT, "There is already a plugin with the same name \"" + name + "\"");
                        return;
                    }
                    PluginConfigurationObject.getPluginInformation.put(o, getClassInf);
                    JavaPlugin lPluginListener = (JavaPlugin) o;
                    Console.info(ConsoleColor.YELLOW_BRIGHT, String.format("[%s] Loading %s %s", getClassInf.getName(), getClassInf.getName(), getClassInf.getVersion()));
                    try {
                        lPluginListener.onEnable();
                        jda.addEventListener(lPluginListener);
                    } catch (Exception | NoClassDefFoundError | NoSuchMethodError e) {
                        e.printStackTrace();
                    }
                    //create plugin
                    initPlugin(f2, getClassInf, lPluginListener);
                }
            } catch (Exception e) {
                e.printStackTrace();
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

        JDAPlug.registratedClassPlugin.forEach(jda::addEventListener);
    }

    private List<PluginConfigurationObject> doFilterList(List<File> fileList) {
        return fileList.stream().filter(this::hasExtensionJar).map(this::getMainClass).filter(classInfo -> classInfo.main != null && classInfo.name != null).collect(Collectors.toList());
    }

    private File createPluginsDirectory() {
        File f = new File("plugins");
        if (!f.exists()) {
            f.mkdir();
        }
        return f;
    }

    private LinkedList<PluginConfigurationObject> orderPluginsFilter(List<PluginConfigurationObject> classInfo) {

        LinkedList<PluginConfigurationObject> filtered = new LinkedList<>(classInfo.stream().filter(r -> r.depends != null).collect(Collectors.toList()));

        List<PluginConfigurationObject> collec = classInfo.stream().filter(r -> r.depends != null).collect(Collectors.toList());
        HashMap<String, PluginConfigurationObject> map = new HashMap<String, PluginConfigurationObject>(collec.stream().collect(Collectors.toMap(PluginConfigurationObject::getName, PluginConfigurationObject::getClazz)));

        collec.stream().map(PluginConfigurationObject::getDependencies).forEach(d -> {
            PluginConfigurationObject plugin = map.get(d);
            if (plugin == null || filtered.contains(plugin)) {
                return;
            }
            filtered.add(plugin);
        });

        filtered.forEach(o -> {
            if (filtered.contains(o)) return;
            filtered.add(o);
        });

        return filtered;
    }

    private HashMap<Object, PluginConfigurationObject> getObjectsWithSync(List<PluginConfigurationObject> list) {
        return (HashMap) list.stream().collect(Collectors.toMap(this::getObject, PluginConfigurationObject::getClazz));
    }

    private Object getObject(PluginConfigurationObject pluginInfo) {
        try {
            try {
                return Class.forName(pluginInfo.main, true, syncClassPlugin(pluginInfo.file)).newInstance();
            } catch (InstantiationException | IllegalAccessException | MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            new MainNotFoundException(pluginInfo.name, e).printStackTrace();
        }
        return null;
    }

    private URLClassLoader syncClassPlugin(File f2) throws MalformedURLException {
        try {
            ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();

			PluginClassLoader child = new PluginClassLoader(new URL[] { new URL("file:///" + f2.getAbsolutePath()) }, currentThreadClassLoader);

            filteredFileList.stream().forEach(file -> {
                try {
					child.addURL(file.toURL());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });

            Thread.currentThread().setContextClassLoader(child);
            return child;
        } catch (Exception e) {
			e.printStackTrace();
            return null;
        }
    }

    private URLClassLoader getSync(File f2, ClassLoader currentThreadClassLoader) throws MalformedURLException {
        URLClassLoader child = new URLClassLoader(new URL[]{new URL("file:///" + f2.getAbsolutePath())}, currentThreadClassLoader);
        return child;
    }


    private void initPlugin(File f2, PluginConfigurationObject getClassInf, JavaPlugin o) {
        Plugin plugin = new Plugin(f2, f2.getPath(), getClassInf.name, o, getClassInf.author, getClassInf.description, getClassInf.version, getClassInf.depends);
        loadedPlugins.add(plugin);
        registedClass.put(plugin.getName(), plugin);

        if (plugin.getAuthor() == null)
            Console.info(ConsoleColor.GREEN_BRIGHT, String.format("[%s] %s %s has been loaded successfully", plugin.getName(), plugin.getName(), plugin.getVersion()));
        else
            Console.info(ConsoleColor.GREEN_BRIGHT, String.format("[%s] %s %s by %s has been loaded successfully", plugin.getName(), plugin.getName(), plugin.getVersion(), plugin.getAuthor()));
    }

    public void unloadPlugins(JDANMS jda) {
        if (!registedClass.isEmpty()) {
            registedClass.forEach((k, v) -> {
                Plugin plugin = v;
                JavaPlugin pluginListener = (JavaPlugin) plugin.getMainClass();
                jda.removeEventListener(pluginListener);
                try {
                    pluginListener.onDisable();
                    if (plugin.getAuthor() == null)
                        Console.info(ConsoleColor.RED_BRIGHT, String.format("%s %s has been unloaded successfully", plugin.getName(), plugin.getVersion()));
                    else
                        Console.info(ConsoleColor.RED_BRIGHT, String.format("%s %s by %s has been unloaded successfully", plugin.getName(), plugin.getVersion(), plugin.getAuthor()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        JDAPlug.registratedClassPlugin.forEach(jda::removeEventListener);
        registedClass.clear();
        PluginConfigurationObject.getPluginInformation.clear();
        JDAPlug.registratedClassPlugin.clear();
        loadedPlugins.clear();

        if (previusClassLoader == null) {
            previusClassLoader = Thread.currentThread().getContextClassLoader();
        } else {
            Thread.currentThread().setContextClassLoader(previusClassLoader);
        }
        EventsRegistration.registered.clear();
    }

    private boolean hasExtensionJar(File f) {
        if (f.isDirectory()) return false;
        String name = f.getName();
        if (!name.contains(".jar")) return false;
        return true;
    }

    private PluginConfigurationObject getMainClass(File f) {
        try {
            ZipFile zipFile = new ZipFile("plugins/" + f.getName());
            ZipEntry zipEntry = zipFile.getEntry("plugin.yml");
            if (zipEntry == null) {
                Console.info(ConsoleColor.RED_BRIGHT, "The plugin.yml was not found in " + f.getName());
                return null;
            }

            InputStream is = zipFile.getInputStream(zipEntry);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            PluginConfigurationObject result = extract(f, reader.lines());

            if (result.main != null && result.name != null) {
                return result;

            } else {
                new InvalidPluginYMLException(null).printStackTrace();
                return null;
                //Console.logger.info(ConsoleColor.RED_BRIGHT+"The plugin.yml of "+f.getName()+" is invalid"+ConsoleColor.RESET);
            }

        } catch (IOException e) {
            Console.info(ConsoleColor.RED_BRIGHT, "Failed to load " + f.getName());
            return null;
        }

    }

    private PluginConfigurationObject extract(File f, Stream<String> list) {

        String name = null, main = null, author = null, description = null, version = null;
        List<String> depends = null;

        for (String string : list.collect(Collectors.toList())) {
            if (string.contains("main: ")) {
                main = string.replace("main: ", "");
            }
            if (string.contains("name: ")) {
                name = string.replace("name: ", "");
            }
            if (string.contains("author")) {
                author = string.replace("author: ", "");
            }
            if (string.contains("description")) {
                description = string.replace("description: ", "");
            }
            if (string.contains("version")) {
                version = string.replace("version: ", "");
            }
            if (string.contains("depend")) {
                string = string.replace(" ", "").replace("[", "").replace("]", "");
                if (string.contains(",")) {
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