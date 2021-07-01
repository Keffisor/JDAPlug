package com.Keffisor21.JDAExpansion.Exception;

import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class MainNotFound extends RuntimeException {
	public MainNotFound(Throwable err) {
        super(ConsoleColor.RED+"Main indicated by the plugin.yml was not found "+ConsoleColor.RESET, err);
    }
}
