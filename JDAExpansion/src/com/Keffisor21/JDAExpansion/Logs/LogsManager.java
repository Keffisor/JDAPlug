package com.Keffisor21.JDAExpansion.Logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.Utils;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;

public class LogsManager {
	
	private String logName;
	private String logExtension = ".log";
	private File logFile;
	private String directoryLogs = "logs/";
	
	public LogsManager() {
		//start loading the logs
		load();
	}
	
	private void load() {
		File logsDirectory = createDirectory();
		this.logName = generateNameLog();
		this.logFile = createLogFile();
	}
	
	public String getLogName() {
		return logName;
	}
	
	private String generateNameLog() {
		String date = Utils.getDate();
		String defaultName = directoryLogs+date+logExtension;
		
		if(!new File(defaultName).exists()) return defaultName;
		
		for (int i = 1; true; i++) {
			String newLogName = directoryLogs+date+"-"+i+logExtension;
			if(!new File(newLogName).exists()) return newLogName;
		}
	}
	
	public void writeLog(String content) {
	  try {
		  FileWriter fw = new FileWriter(logFile.getAbsoluteFile(), true);
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write(content+"\n");
	      bw.close();
	  } catch (IOException e) {
		e.printStackTrace();
	  }
	}
	
	private File createLogFile() {
		String name = getLogName();
		try {
			File file = new File(name);
			file.createNewFile();
			file.setWritable(true, false);
			file.setReadable(true, false);
			return file;
			} catch (IOException e) {
				Console.info(ConsoleColor.RED_BRIGHT, "Failed on creating the log "+name);
			}
		return null;
	}
	
	private File createDirectory() {
		File f = new File("logs");
		if(!f.exists()) {
			f.mkdir();
		}
		return f;	
	}
	
}