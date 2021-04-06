package com.Keffisor21.JDAExpansion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.Keffisor21.JDAExpansion.NMS.JDAType;

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
	  public static void clearConsole(){
		    try {

		     if (System.getProperty("os.name").contains("Windows"))
		         new ProcessBuilder("cmd", "/c", 
		                  "cls").inheritIO().start().waitFor();
		     else
		         Runtime.getRuntime().exec("clear");
		         System.out.print("\033[H\033[2J");
		    } catch (IOException | InterruptedException ex) {}
		} 
	  public static String getCharDelete() {
		    	 return "\033[K";
	  }
	  
}
