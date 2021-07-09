package com.Keffisor21.JDAExpansion.Commands;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

import java.util.ArrayList;
import java.util.List;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.ConsoleHandler.Console;
import com.Keffisor21.JDAExpansion.ConsoleHandler.ConsoleColor;
import com.Keffisor21.JDAExpansion.Events.Command;
import com.Keffisor21.JDAExpansion.EventsHandler.createCommand;
import com.Keffisor21.JDAExpansion.NMS.JDANMS;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class setupCommands {
	
      public static void loadCommands(JDANMS jda) {
      jda.addEventListener(new ReloadCommand());
      jda.addEventListener(new PluginsCommand());
      jda.addEventListener(new StopCommand());
      }
}
