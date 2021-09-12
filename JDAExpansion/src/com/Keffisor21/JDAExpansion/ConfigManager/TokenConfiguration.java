package com.Keffisor21.JDAExpansion.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class TokenConfiguration {
	public static String getTokenFileContent() {
		File file = new File("token.txt");
		if(!file.exists()) {
			try {
			file.createNewFile();
			file.setWritable(true, false);
			file.setReadable(true, false);
			} catch (IOException e) {
				Console.info(ConsoleColor.RED_BRIGHT, "Failed on creating the token.txt");
			}
		}
		try {
			return Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8).get(0);
		} catch (IOException | IndexOutOfBoundsException e) {
            return "";
		}
	}
}
