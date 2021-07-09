package com.Keffisor21.JDAExpansion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class Utils {

	  public static String generateRandomID(int length)
	  {
	    Random random = new SecureRandom();
	    if (length <= 0) {
	      throw new IllegalArgumentException("String length must be a positive integer");
	    }
	    StringBuilder sb = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	      sb.append("abcdefghijkmopkrstu123456789".charAt(random.nextInt("abcdefghijkmopkrstu123456789".length())));
	    }
	    return sb.toString();
	  }
	  public static ArrayList<String> removeDuplicates(List<String> list) {
		  ArrayList<String> result = new ArrayList();
		  for(String s : list) {
			  if(!result.contains(s)) {
				  result.add(s);
			  }
		  }
		  return result;
	  }
	  public static String convertFormatNumber(String a) {
		   return NumberFormat.getNumberInstance(Locale.GERMAN).format(Integer.parseInt(a));
	   }
	  public static List<String> getAllConsoleLines() {
		  List<String> list = new ArrayList<String>();
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line;
	        while(true) {
	            try {
					line = br.readLine();
				 
	            if (line == null || line.isEmpty()) {
	                break;
	            }
	            list.add(line);
	            } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        return list;  
	  }
	  public static void executeCommand(String windows, String ubuntu){
		    try {

		     if (System.getProperty("os.name").contains("Windows"))
		         new ProcessBuilder("cmd", "/c", 
		                  windows).inheritIO().start().waitFor();
		     else
		         Runtime.getRuntime().exec(ubuntu);
		         System.out.print("\033[H\033[2J");
		    } catch (IOException | InterruptedException ex) {}
		} 
	  public static void executeCommand(String command){
		    try {

		     if (System.getProperty("os.name").contains("Windows"))
		         new ProcessBuilder("cmd", "/c", 
		                  command).inheritIO().start().waitFor();
		     else
		         Runtime.getRuntime().exec(command);
		         System.out.print("\033[H\033[2J");
		    } catch (IOException | InterruptedException ex) {}
		} 
	  public static String getCharDelete() {
		    	 return "\033[K";
	  }
	  public static String getTime() {
		  return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	  }
	  public static String getDate() {
		  return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	  }
	  public static void printStackTrace(Throwable e) {
		String format = String.format("[%s INFO]: ", Utils.getTime());
		Console.removeLine("> ");
		System.out.print(format);
		e.printStackTrace();
		JDAExpansion.getLogger().info(format+e.getMessage());
	  }
	  
	  public static String convertToColors(ConsoleColor color, String msg) {
			if(!hasSupportColors()) return msg;
			return color+msg+ConsoleColor.RESET;
	  }
	  
	  public static boolean hasSupportColors() {
		  if(System.getProperty("os.name").equalsIgnoreCase("Windows 10") || !System.getProperty("os.name").contains("Windows")) return true;
		  return false;
	  }

	  
	  /*public static void dispachMethod (Object o, String name) {
		  try {
			  Class<?> class0 = o.getClass();
			  Arrays.asList(class0.getMethods()).forEach(obj -> {
				  if(obj.getName().equals(name)) {
					  try {
					    Method method = class0.getMethod(name);
						method.invoke(null);
					} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			  });
		  } catch(Exception e) {}
	  }*/
}
