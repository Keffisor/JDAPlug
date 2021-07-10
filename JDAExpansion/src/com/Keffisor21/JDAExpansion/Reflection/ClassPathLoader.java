package com.Keffisor21.JDAExpansion.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Stack;

public class ClassPathLoader {
	private URL url;
	
	
	public ClassPathLoader(URL url) {
		this.url = url;
	}
	
	public void addURL() {
		try {
		  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		  Class URLLoader = URLClassLoader.class;
		  		  
		  Method method= URLLoader.getDeclaredMethod("addURL", new Class[] { URL.class });
		  method.setAccessible(true);
		  method.invoke(classLoader, new Object[] { url });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeURL() {
		try {
			
		} catch(Exception e) {
			
		}
	}
	
	public Stack<URL> getURLs() {
		try {
		  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		  Class URLLoader = URLClassLoader.class;
		  
		  Field URLClassPathField = URLLoader.getDeclaredField("ucp");
		  URLClassPathField.setAccessible(true);
		  Object URLClassPath =  URLClassPathField.get(classLoader);
		  
		  Field field = URLClassPath.getClass().getDeclaredField("urls");
		  field.setAccessible(true);
		  
		  return (Stack<URL>)field.get(URLClassPath);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<URL> getPaths() {
		try {
		  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		  Class URLLoader = URLClassLoader.class;
		  
		  Field URLClassPathField = URLLoader.getDeclaredField("ucp");
		  URLClassPathField.setAccessible(true);
		  Object URLClassPath =  URLClassPathField.get(classLoader);
		  
		  Field field = URLClassPath.getClass().getDeclaredField("path");
		  field.setAccessible(true);
		  
		  return (ArrayList<URL>)field.get(URLClassPath);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void clearURLs() {
		try {
		  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		  Class URLLoader = URLClassLoader.class;
		  
		  Field URLClassPathField = URLLoader.getDeclaredField("ucp");
		  URLClassPathField.setAccessible(true);
		  Object URLClassPath =  URLClassPathField.get(classLoader);
		  
		  Field field = URLClassPath.getClass().getDeclaredField("urls");
		  field.setAccessible(true);
		  field.set(URLClassPath, new Stack<URL>());
		  
		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public static void clearPaths() {
		try {
			  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			  Class URLLoader = URLClassLoader.class;
			  
			  Field URLClassPathField = URLLoader.getDeclaredField("ucp");
			  URLClassPathField.setAccessible(true);
			  Object URLClassPath =  URLClassPathField.get(classLoader);
			  
			  Field field = URLClassPath.getClass().getDeclaredField("path");
			  field.setAccessible(true);
			  field.set(URLClassPath, new ArrayList<URL>());
			  
			} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
	}
	
}
