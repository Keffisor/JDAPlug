package com.jdaplug.exceptions;

import com.jdaplug.Utils;
import com.jdaplug.consolehandler.ConsoleColor;

public class InvalidPluginYMLException extends RuntimeException {
	public InvalidPluginYMLException(Throwable err) {
        super(Utils.convertToColors(ConsoleColor.RED, "Invalid Plugin.yml file"), err);
    }
}
