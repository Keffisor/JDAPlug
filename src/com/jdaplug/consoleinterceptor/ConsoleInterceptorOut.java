package com.jdaplug.consoleinterceptor;

import java.io.OutputStream;
import java.io.PrintStream;

import com.jdaplug.JDAPlug;

public class ConsoleInterceptorOut extends PrintStream
    {
        public ConsoleInterceptorOut(OutputStream out)
        {
            super(out, true);
        }

        @Override
        public void println(String s)
        {
        	JDAPlug.getLogger().info(s);
        }
        
        @Override
        public void print(String s)
        {
        	JDAPlug.getLogger().info(s);
       	}
        
        @Override
        public void println(Object o) {
        	JDAPlug.getLogger().info(String.valueOf(o));
        }
        
        @Override
        public void print(Object o) {
        	JDAPlug.getLogger().info(String.valueOf(o));
        }
        
        @Override
        public void println(int i) {
        	JDAPlug.getLogger().info(String.valueOf(i));
        }
        
        @Override
        public void print(int i) {
        	JDAPlug.getLogger().info(String.valueOf(i));
        }
        
    }