package com.jdaplug.consolehandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jdaplug.events.MessageConsoleReceivedEvent;
import com.jdaplug.commands.CommandExecutor;
import com.jdaplug.eventcontroller.EventsRegistration;
import com.jdaplug.nms.JDANMS;

import jline.console.ConsoleReader;
import jline.console.KeyMap;

public class ThreadConsoleReader extends Thread {
    private final JDANMS jda;
	private ConsoleReader consoleReader;
	private final List<String> commandsLog = new ArrayList<>();

    public ThreadConsoleReader(JDANMS jda) {
        this.jda = jda;
        this.setDaemon(true);

		try {
			this.consoleReader = new jline.console.ConsoleReader();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}

    }

    @Override
    public void run() {
        while(true) {
            try {
                String command = consoleReader.readLine(consoleReader.RESET_LINE + "> ", null);
                Console.lines.add("> " + command);

                if (!command.isEmpty()) {
                    detectCommand(command);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void detectCommand(String command) {
		commandsLog.add(command);

        new EventsRegistration().onMessageConsoleReceived(new MessageConsoleReceivedEvent(command));

        if(jda.getEventManager().stream().filter(obj -> {

            if(obj instanceof CommandExecutor) {
                return ((CommandExecutor) obj).onConsoleMessageReceived(command);
            }

            return false;
        }).collect(Collectors.toList()).isEmpty()) {
            Console.logger.info("Unknown command");
        }
    }

}
