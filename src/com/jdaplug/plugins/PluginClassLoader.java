package com.jdaplug.plugins;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
    static {
        ClassLoader.registerAsParallelCapable();
    }

    public PluginClassLoader(URL[] url, ClassLoader classLoader) {
        super(url, classLoader);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

}