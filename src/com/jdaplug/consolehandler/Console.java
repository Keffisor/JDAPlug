package com.jdaplug.consolehandler;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdaplug.JDAPlug;
import com.jdaplug.Utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import jline.console.ConsoleReader;


public class Console extends ch.qos.logback.core.UnsynchronizedAppenderBase<ILoggingEvent> {
    public static List<String> lines = new ArrayList<String>();
    public static Logger logger = LoggerFactory.getLogger(Console.class);
    public static ConsoleReader reader = null;
    public static PrintStream previousPrintStream = null;

    @Override
    protected void append(ILoggingEvent e) {
        if (e == null) return;
        filter(e.getMessage());
    }

    private void filter(String msg) {
        try {
            reader.print(String.valueOf(reader.RESET_LINE));
            reader.flush();
            reader.drawLine();
            reader.getCursorBuffer().clear();
            reader.flush();

            int length = msg.split("\n").length - 1;

            if (msg.equals("> ")) return;
            if (msg.startsWith("> ") || msg.equals(">")) {
                JDAPlug.getLogsManager().writeLog(msg);
            } else {
                String format = String.format("[%s INFO]: %s", Utils.getTime(), msg);
                Console.previousPrintStream.println(format);
                JDAPlug.getLogsManager().writeLog(format);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Console.previousPrintStream.print("> ");
    }

    public static void info(ConsoleColor color, String msg) {
        if (!Utils.hasSupportColors()) {
            JDAPlug.getLogger().info(msg);
            return;
        }

        JDAPlug.getLogger().info(color + msg + ConsoleColor.RESET);
    }

}
