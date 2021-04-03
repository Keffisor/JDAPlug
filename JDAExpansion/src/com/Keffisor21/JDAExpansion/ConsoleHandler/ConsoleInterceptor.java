package com.Keffisor21.JDAExpansion.ConsoleHandler;

import java.io.OutputStream;
import java.io.PrintStream;

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
        	if(s.startsWith("[INFO]:")) {
        		super.println(s);
        		return;
        	}
        	if(s.split("\n").length != 1) {
        	super.println(Utils.getCharDelete()+"[INFO]: "+s);
        		return;
        	}
        	super.println(s);
       	}
        @Override
        public void print(String s)
        {
        	if(s.startsWith("[INFO]:")) {
        		super.print(s);
        		return;
        	}
        	if(s.split("\n").length != 1) {
        	super.println(Utils.getCharDelete()+"[INFO]: "+s);
        		return;
        	}
        	super.print(s);
       	}
        @Override
        public void println(Object o) {
        	if(o.toString().split("\n").length != 1) {
            	super.println(Utils.getCharDelete()+"[INFO]: ");
            		return;
            	}
            	super.println(o);
        }
        @Override
        public void print(Object o) {
        	if(o.toString().split("\n").length != 1) {
            	super.print(Utils.getCharDelete()+"[INFO]: "+o);
            		return;
            	}
            	super.print(o);
        }
    }