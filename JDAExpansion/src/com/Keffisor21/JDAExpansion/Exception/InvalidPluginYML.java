package com.Keffisor21.JDAExpansion.Exception;

import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class InvalidPluginYML extends RuntimeException {
	public InvalidPluginYML(Throwable err) {
        super(ConsoleColor.RED+"Invalid Plugin.yml file "+ConsoleColor.RESET, err);
    }
}
