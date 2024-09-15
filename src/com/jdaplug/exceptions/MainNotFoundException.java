package com.jdaplug.exceptions;

import com.jdaplug.Utils;
import com.jdaplug.consolehandler.ConsoleColor;

public class MainNotFoundException extends RuntimeException {
	public MainNotFoundException(String name, Throwable err) {
        super(Utils.convertToColors(ConsoleColor.RED, "Main indicated by the plugin.yml was not found in "+name), err);
    }
}
