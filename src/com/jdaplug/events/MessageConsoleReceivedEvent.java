package com.jdaplug.events;

public class MessageConsoleReceivedEvent {
	private String contentRaw;
	
	public MessageConsoleReceivedEvent(String contentRaw) {
		this.contentRaw = contentRaw;
	}
	
	public String getContentRaw() {
		return contentRaw;
	}

}
