package com.Keffisor21.JDAExpansion.Exception;

import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class InvalidPluginYML extends RuntimeException {
	public InvalidPluginYML(Throwable err) {
        super(Utils.convertToColors(ConsoleColor.RED, "Invalid Plugin.yml file"), err);
    }
}
