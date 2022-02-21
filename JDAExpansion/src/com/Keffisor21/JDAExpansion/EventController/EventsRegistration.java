package com.Keffisor21.JDAExpansion.EventController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import com.Keffisor21.JDAExpansion.API.MessageConsoleReceivedEvent;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventsRegistration extends ListenerAdapter {
	public static HashMap<Class<?>, List<EventValues>> registered = new HashMap<>();
	
	public static void loadEvent(PluginListener o) {
		for(Method method : o.getClass().getDeclaredMethods()) {
			EventHandler e = method.getAnnotation(EventHandler.class);
			if(e == null || method.getParameters().length != 1) continue;
			Class<?> type = method.getParameters()[0].getType();
			setEventType(type, new EventValues(o, type, method, e));
		}
	}
	
	public static void setEventType(Class<?> o, EventValues values) {
		new EventsRegistration().getListEvents().forEach(cl -> {
			try {
				if(o.getName().equalsIgnoreCase(cl.getName())) {
					if(registered.get(cl) == null) registered.put(cl, new ArrayList<>());
					List<EventValues> list = registered.get(cl);
					if(list.stream().anyMatch(value -> value.getClass().getName().equals(values.getObject().getClass().getName()))) return;
					list.add(values);
					registered.put(cl, list);
				}
			} catch (ClassCastException e) {
				// TODO: handle exception
			}
		});	
	}
	
	public List<EventValues> getClass(Class<?> o) {
		if(registered.get(o) == null) return new ArrayList<EventValues>();
		return registered.get(o);
	}
	
	public List<Class<?>> getListEvents() {
		List<Class<?>> cls = new ArrayList<>();
		for(Method method : ListenerAdapter.class.getDeclaredMethods()) {
			if(method.getParameters().length != 1) continue;
			Nonnull nonnull = method.getParameters()[0].getAnnotation(Nonnull.class);
			if(nonnull == null) continue;
			cls.add(method.getParameters()[0].getType());
		}
		cls.add(MessageConsoleReceivedEvent.class);
		return cls;
	}
	
	public void executeClass(Class<?> cl, Object e) {
		orderPriority(getClass(cl)).forEach(value -> {
			try {
				try {
				value.getMethod().invoke(value.getObject(), e);
				} catch(IllegalArgumentException ex) {}
			} catch (Exception ex) {
			}
		});
	}
	
	public List<EventValues> orderPriority(List<EventValues> values) {
		LinkedList<EventValues> result = new LinkedList<>();
		for(EventPriority priority : EventPriority.values()) {
			values.stream().filter(v -> v.getAnnotation().priority().equals(priority)).forEach(result::add);
		}
		Collections.reverse(result);
		return result;
	}

	@Override
	public void onGenericEvent(GenericEvent e) {
		executeClass(e.getClass(), e);
	}
	
    public void onMessageConsoleReceived(MessageConsoleReceivedEvent event) {
    	executeClass(MessageConsoleReceivedEvent.class, event);
    }

}
