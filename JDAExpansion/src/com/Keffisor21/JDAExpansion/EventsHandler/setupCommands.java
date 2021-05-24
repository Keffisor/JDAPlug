package com.Keffisor21.JDAExpansion.EventsHandler;

import java.util.ArrayList;
import java.util.List;

import com.Keffisor21.JDAExpansion.Main;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;
import com.Keffisor21.JDAExpansion.Plugins.JDAExpansion;
import com.Keffisor21.JDAExpansion.Plugins.Plugin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setupCommands {
	
      public static void loadCommands(JDANMS jda) {
      jda.addEventListener(reloadCommand());
      jda.addEventListener(pluginsCommand());
      jda.addEventListener(stopCommand());
      }
      
     
      public static createCommand stopCommand() {
    	  createCommand command = new createCommand("!", "stop", "shutdown") {
			
			@Override
			protected void isExecutedConsole(String[] args) {
 				System.exit(0);	 
			}
			@Override
			protected void isExecuted(String[] args) {
				MessageReceivedEvent e = eventCommand();
				if(e.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
					System.exit(0);
					} else {
						 e.getChannel().sendMessage("You don't have permissions to do this, you must have the administrator permission").queue();  
					}
			}
		};
		return command;
      }
     
      public static createCommand reloadCommand() {
    	  createCommand command = new createCommand("!", "reload", "rl") {
    		  
			
			@Override
			protected void isExecutedConsole(String[] args) {
				Plugin.loadPlugins(JDAExpansion.getJDANMS());
				Console.logger.info("Plugins recargados");
			}
			
			@Override
			protected void isExecuted(String[] args) {
               MessageReceivedEvent e = eventCommand();
               if(e.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
                   Plugin.loadPlugins(JDAExpansion.getJDANMS());
            	   e.getChannel().sendMessage("Plugins reloaded! "+e.getAuthor().getAsMention()).queue();
               } else {
					 e.getChannel().sendMessage("You don't have permissions to do this, you must have the administrator permission").queue();  
               }
             }
		};
		return command;
      }
      public static createCommand pluginsCommand() {
    	  createCommand command = new createCommand("!", "plugins", "pl") {
			
			@Override
			protected void isExecutedConsole(String[] args) {
				List<String> plugins = new ArrayList<String>();
				Plugin.registedClass.forEach((k, v) -> {
					plugins.add(k);
				});
				Console.logger.info("Plugins ("+ConsoleColor.GREEN_BRIGHT+plugins.size()+ConsoleColor.RESET+"):"+ConsoleColor.GREEN_BRIGHT+" "+("$!"+plugins.toString()+"$!").replace("$![", "").replace("]$!", "")+ConsoleColor.RESET);
			}
			
			@Override
			protected void isExecuted(String[] args) {
				MessageReceivedEvent e = eventCommand();
				List<String> plugins = new ArrayList<String>();
				Plugin.registedClass.forEach((k, v) -> {
					plugins.add(k);
				});
				e.getChannel().sendMessage("Plugins ("+plugins.size()+"): "+("$!"+plugins.toString()+"$!").replace("$![", "").replace("]$!", "")).queue();
				
			}
		};
		return command;
      }
    
}
