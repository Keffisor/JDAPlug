package com.Keffisor21.JDAExpansion.Exceptions;

import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class InvalidPluginYMLException extends RuntimeException {
	public InvalidPluginYMLException(Throwable err) {
        super(Utils.convertToColors(ConsoleColor.RED, "Invalid Plugin.yml file"), err);
    }
}
