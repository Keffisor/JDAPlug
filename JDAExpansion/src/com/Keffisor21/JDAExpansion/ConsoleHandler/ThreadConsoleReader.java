package com.Keffisor21.JDAExpansion.ConsoleHandler;

import java.util.stream.Collectors;

import com.Keffisor21.JDAExpansion.API.MessageConsoleReceivedEvent;
import com.Keffisor21.JDAExpansion.API.createCommand;
import com.Keffisor21.JDAExpansion.EventController.EventsRegistration;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;

import jline.console.ConsoleReader;


public class ThreadConsoleReader extends Thread {
	private JDANMS jda;
	private ConsoleReader reader;
	
	public ThreadConsoleReader(JDANMS jda, ConsoleReader reader) {
	  this.jda = jda;
	  this.reader = reader;
	  this.setDaemon(true);
	}
    
	@Override
	public void run() {
		while(true) {
			try {
				//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				jline.console.ConsoleReader console = new jline.console.ConsoleReader();
				String command = console.readLine(console.RESET_LINE+"> ", null);
				Console.lines.add("> "+command);
				if(!command.isEmpty()) {
					detectCommand(command);
				} else {
				//	System.out.print("\033[F");
				//	System.out.println(">");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
         
    private void detectCommand(String command) {
		new EventsRegistration().onMessageConsoleReceived(new MessageConsoleReceivedEvent(command));

    	if(jda.getEventManager().stream().filter(obj -> {
    		
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
