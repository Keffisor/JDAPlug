package com.Keffisor21.JDAExpansion.ConsoleHandler;

import java.util.Scanner;
import java.util.stream.Collectors;

import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.EventsHandler.ConsoleEvents;
import com.Keffisor21.JDAExpansion.EventsHandler.createCommand;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;


public class ConsoleReader extends Thread {
	private JDANMS jda;
	
	public ConsoleReader(JDANMS jda) {
	  this.jda = jda;
	}
    
   @Override
   public void run() {
     while(true) {
        try {
        Scanner scanner = new Scanner(System.in);
        System.out.flush();
        String command = scanner.nextLine();
        Console.lines.add("> "+command);
       	if(!command.isEmpty()) {
       			detectCommand(command);
       	 } else {
       	  		System.out.print("\033[F");
       			Console.logger.info(">");
       	 }		
         		 }catch(Exception e2) {}
        } 
  }
         
    private void detectCommand(String command) {
    	if(jda.getEventManager().stream().filter(obj -> {
    		if(obj instanceof ConsoleEvents) {
    			((ConsoleEvents)obj).onMessageConsoleReceive(command);
    		}
    		if(obj instanceof createCommand) {
   				return ((createCommand) obj).onConsoleMessageReceived(command);
   			}
   			return false;
   		}).collect(Collectors.toList()).isEmpty())  {
			 Console.logger.info("Unknown command");	
    	} else {

    	}
    }
}
