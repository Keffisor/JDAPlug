package com.Keffisor21.JDAExpansion.ConsoleInterceptor;

import java.io.OutputStream;
import java.io.PrintStream;

import com.Keffisor21.JDAExpansion.JDAExpansion;
import com.Keffisor21.JDAExpansion.Main;
import com.Keffisor21.JDAExpansion.Utils;

public class ConsoleInterceptorOut extends PrintStream
    {
        public ConsoleInterceptorOut(OutputStream out)
        {
            super(out, true);
        }

        @Override
        public void println(String s)
        {
        	JDAExpansion.getLogger().info(s);
        }
        
        @Override
        public void print(String s)
        {
        	JDAExpansion.getLogger().info(s);
       	}
        
        @Override
        public void println(Object o) {
        	JDAExpansion.getLogger().info(String.valueOf(o));
        }
        
        @Override
        public void print(Object o) {
        	JDAExpansion.getLogger().info(String.valueOf(o));
        }
        
        @Override
        public void println(int i) {
        	JDAExpansion.getLogger().info(String.valueOf(i));
        }
        
        @Override
        public void print(int i) {
        	JDAExpansion.getLogger().info(String.valueOf(i));
        }
        
    }