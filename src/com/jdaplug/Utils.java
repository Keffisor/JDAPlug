package com.jdaplug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.jdaplug.consolehandler.ConsoleColor;

import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.annotation.Nullable;

public class Utils {

    public static String generateRandomID(int length) {
        Random random = new SecureRandom();
        if (length <= 0) {
            throw new IllegalArgumentException("String length must be a positive integer");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append("abcdefghijkmopkrstu123456789".charAt(random.nextInt("abcdefghijkmopkrstu123456789".length())));
        }
        return sb.toString();
    }

    public static ArrayList<String> removeDuplicates(List<String> list) {
        ArrayList<String> result = new ArrayList();
        for (String s : list) {
            if (!result.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public static List<String> getAllConsoleLines() {
        List<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(true) {
            try {
                line = br.readLine();

                if (line == null || line.isEmpty()) {
                    break;
                }
                list.add(line);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void executeCommand(@Nullable String windows, @Nullable String ubuntu) {
        try {
            if(System.getProperty("os.name").contains("Windows") && windows != null)
                new ProcessBuilder("cmd", "/c",
                        windows).inheritIO().start().waitFor();
            else if(ubuntu != null)
                Runtime.getRuntime().exec(ubuntu);
            System.out.print("\033[H\033[2J");
        } catch (Exception e) {

        }
    }

    public static void isOpenByConsole() {
        if (System.console() == null) {
            executeCommand("start cmd.exe /k java -jar \"" + Main.class.getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20", " ").replace("/C:", "C:") + "\"", "");
            System.exit(0);
        }
    }

    public static String getCharDelete() {
        return "\033[K";
    }

    public static String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public static EnumSet<CacheFlag> convertCacheFlagList(List<CacheFlag> list) {
        return list.stream().collect(Collectors.toCollection(() -> EnumSet.noneOf(CacheFlag.class)));
    }

    public static String convertToColors(ConsoleColor color, String msg) {
        if (!hasSupportColors()) return msg;
        return color + msg + ConsoleColor.RESET;
    }

    public static boolean hasSupportColors() {
        List<String> supportedOS = new ArrayList<>(Arrays.asList("Windows 11", "Windows 10"));
        if(supportedOS.contains(System.getProperty("os.name")) || !System.getProperty("os.name").contains("Windows"))
            return true;
        return false;
    }

}
