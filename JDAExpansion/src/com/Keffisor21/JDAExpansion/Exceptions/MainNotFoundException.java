package com.Keffisor21.JDAExpansion.Exceptions;

import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class MainNotFoundException extends RuntimeException {
	public MainNotFoundException(String name, Throwable err) {
        super(Utils.convertToColors(ConsoleColor.RED, "Main indicated by the plugin.yml was not found in "+name), err);
    }
}
