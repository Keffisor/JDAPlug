package com.jdaplug.consoleinterceptor;

import java.io.OutputStream;
import java.io.PrintStream;

import com.jdaplug.Utils;

public class ConsoleInterceptorErr extends PrintStream
    {
        public ConsoleInterceptorErr(OutputStream out)
        {
            super(out, true);
        }

        @Override
        public void println(String s)
        {
        	if(s.contains("	at")) {
        		super.print(s+"\n");
        		return;
        	}
        	super.print("["+Utils.getTime()+"INFO]: "+s+"\n");
        }
        
        @Override
        public void print(String s)
        {
        	if(s.contains("	at")) {
        		super.print(s);
        		return;
        	}
        	super.print(s);
        	//JDAExpansion.getLogger().info(s);
       	}
        
    }