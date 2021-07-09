package com.Keffisor21.JDAExpansion.ConsoleHandler;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.Utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import jline.console.ConsoleReader;


public class Console extends ch.qos.logback.core.UnsynchronizedAppenderBase<ILoggingEvent> {
	public static List<String> lines = new ArrayList<String>();
    public static Logger logger = LoggerFactory.getLogger(Console.class);
    public static ConsoleReader reader = null;
    public static PrintStream previousPrintStream = null;
    
	@Override
	protected void append(ILoggingEvent e) {
 		if(e == null) return;
 		filter(e.getMessage());
	}
	
	private void filter(String msg) {
		try {	
		
		reader.print(reader.RESET_LINE+"");
		reader.flush();
		reader.drawLine();
		reader.getCursorBuffer().clear();
		reader.flush();
		 
		//removeLine(msg);
	 
		int length = msg.split("\n").length-1;
		
		if(msg.equals("> ")) return;
		if(msg.startsWith("> ") || msg.equals(">")) { 
			JDAExpansion.getLogsManager().writeLog(msg);
		} else {
			String format = String.format("[%s INFO]: %s", Utils.getTime(), msg);
			Console.previousPrintStream.println(format);
			JDAExpansion.getLogsManager().writeLog(format);
		}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		Console.previousPrintStream.print("> ");
	}
	
	public static void info(ConsoleColor color, String msg) {
		if(!Utils.hasSupportColors()) {
			JDAExpansion.getLogger().info(msg);
			return;
		}
		JDAExpansion.getLogger().info(color+msg+ConsoleColor.RESET);
	}
	
	
	public static void removeLine(String text) {
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
  }
}
