package com.jdaplug.plugins;

import java.io.File;
import java.util.stream.Collectors;

import com.jdaplug.JDAPlug;
import com.jdaplug.configmanager.FileConfiguration;
import com.jdaplug.configmanager.PluginConfigurationObject;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class JavaPlugin extends ListenerAdapter {

    /**
     * The function that will be executed when the plugin it's enabled.
     */
    protected abstract void onEnable();

    /**
     * The function what will be executed when the plugin it's disabled.
     */
    protected abstract void onDisable();

    /**
     * Get the path to the plugin's directory.
     * @return File
     */
    public File getDataFolder() {
        return new File(JDAPlug.getAbsolutePath() + "/plugins/" + getPluginName());
    }

    /**
     * Create a config file with a custom name for the plugin into the plugin's directory.
     * @param configName The name of the yml file that will be created in the plugin directory.
     * @return FileConfiguration
     */
    public FileConfiguration getConfig(String configName) {
        if (!configName.contains(".yml"))
            configName = configName + ".yml";
        return new FileConfiguration(getPluginFile(), getDataFolder().getAbsolutePath(), configName, this);
    }

    /**
     * Returns the name of the plugin registered in the plugin.yml file
     * @return String
     */
    public String getPluginName() {
        return getPluginConfigurationObject().name;
    }

    private PluginConfigurationObject getPluginConfigurationObject() {
        String main = this.getClass().getName();
        return PluginConfigurationObject.filteredList.stream().filter(o -> o.main.equals(main)).collect(Collectors.toList()).get(0);
    }

    /**
     * Return the File of the plugin's jar.
     * @return File
     */
    public File getPluginFile() {
        return getPluginConfigurationObject().file;
    }

}
