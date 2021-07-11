package com.Keffisor21.JDAExpansion.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Stack;


	/*
	 * OLD Code. With the code of below you can add to the class path of the program any file (jars, images, etc).
	 * It's very effective, but when it's added for the first time you will be not able to reload the class path.
	 */

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
		  			  
		  /*Field URLClassPathField = URLLoader.getDeclaredField("ucp");
		  URLClassPathField.setAccessible(true);
		  Object URLClassPath =  URLClassPathField.get(classLoader);
		  
		  Field field = URLClassPath.getClass().getDeclaredField("urls");
		  field.setAccessible(true);
		  Stack<URL> newUrls = getURLs();
		  newUrls.add(url);
		  field.set(URLClassPath, newUrls);*/

		  /*Field field2 = URLClassPath.getClass().getDeclaredField("path");
		  field2.setAccessible(true);
		  ArrayList<URL> path = getPaths();
		  path.add(url);
		  field2.set(URLClassPath, path);*/
		  
		  System.out.println(getURLs()+" /-/ "+getPaths());
		  
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeURL() {
		try {
			  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			  Class URLLoader = URLClassLoader.class;
			  
			  Field URLClassPathField = URLLoader.getDeclaredField("ucp");
			  URLClassPathField.setAccessible(true);
			  Object URLClassPath =  URLClassPathField.get(classLoader);
			  
			  Field field = URLClassPath.getClass().getDeclaredField("urls");
			  field.setAccessible(true);
			  Stack<URL> newUrls = getURLs();
			  newUrls.remove(url);
			  field.set(URLClassPath, newUrls);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePath() {
		try {
			  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			  Class URLLoader = URLClassLoader.class;
			  
			  Field URLClassPathField = URLLoader.getDeclaredField("ucp");
			  URLClassPathField.setAccessible(true);
			  Object URLClassPath =  URLClassPathField.get(classLoader);
			  
			  Field field = URLClassPath.getClass().getDeclaredField("path");
			  field.setAccessible(true);
			  ArrayList<URL> url = getPaths();
			  url.remove(url);
			  field.set(URLClassPath, url);
			  
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public static Stack<URL> getURLs() {
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
	
	public static ArrayList<URL> getPaths() {
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