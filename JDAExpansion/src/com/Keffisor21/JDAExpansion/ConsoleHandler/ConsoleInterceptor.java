package com.Keffisor21.JDAExpansion.ConsoleHandler;

import java.io.OutputStream;
import java.io.PrintStream;

import com.Keffisor21.JDAExpansion.Main;
import com.Keffisor21.JDAExpansion.Utils;

public class ConsoleInterceptor extends PrintStream
    {
        public ConsoleInterceptor(OutputStream out)
        {
            super(out, true);
        }
        @Override
        public void println(String s)
        {

        }
        @Override
        public void print(String s)
        {
        	super.print(s);
       	}
        @Override
        public void println(Object o) {
            	super.println(o);
        }
        @Override
        public void print(Object o) {
            	super.print(o);
        }
    }