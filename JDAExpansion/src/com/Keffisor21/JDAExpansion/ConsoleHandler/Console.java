package com.Keffisor21.JDAExpansion.ConsoleHandler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Keffisor21.JDAExpansion.Utils;

import ch.qos.logback.classic.spi.ILoggingEvent;


public class Console extends ch.qos.logback.core.UnsynchronizedAppenderBase<ILoggingEvent> {
	public static List<String> lines = new ArrayList<String>();
     public static Logger logger = LoggerFactory.getLogger(Console.class);
 
	@Override
	protected void append(ILoggingEvent e) {
 		if(e == null) return;
		String text = e.getMessage();
		String s = text;

		removeLine(text);
	 
		int length = text.split("\n").length-1;
		
		if(s.equals("> ")) return;
		if(s.startsWith("> ") || s.equals(">")) { 
			System.out.println(s);
		} else {
			
			System.out.println("[INFO]: "+s);
		}	
		System.out.print("> ");
		/*
		 if(e == null) return;
		String text = e.getMessage();
		lines.add(text);
		Utils.clearConsole();
		lines.forEach(s -> {
			if(s.equals("> ")) return;
		if(s.startsWith("> ") || s.equals(">")) { 
			System.out.println(s);
		} else {
			System.out.println("[INFO]: "+s);
		}
		});
		System.out.print("> ");
		 */
	}
	private void removeLine(String text) {
		String[] split = text.split("\n");
		if(split.length == 1) {
  			System.out.print("\033[F");
			for(int i = 0; i < text.length(); i++) {
				System.out.print(Utils.getCharDelete());
			}
			return;
		} 	
        System.out.print("\033[F");
		for(String s : split) {
	        System.out.print("\033[F");
		}
		System.out.print(Utils.getCharDelete());
/*		
		for(String s : split) {
			for(int i = 0; i < s.length(); i++) {
				System.out.print(Utils.getCharDelete());
			}
  			System.out.print("\033[E");
		}*/
  }
}
