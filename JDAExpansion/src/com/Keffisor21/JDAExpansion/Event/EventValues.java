package com.Keffisor21.JDAExpansion.Event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class EventValues {
	
	private final Object o;
	private final Object type;
	private final Method method;
	private final EventHandler annotation;
	
	public EventValues(Object o, Object type, Method method, EventHandler annotation) {
		this.o = o;
		this.type = type;
		this.method = method;
		this.annotation = annotation;
	}
	
	public Object getObject() {
		return o;
	}
	
	public Object getType() {
		return type;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public EventHandler getAnnotation() {
		return annotation;
	}
	
}
