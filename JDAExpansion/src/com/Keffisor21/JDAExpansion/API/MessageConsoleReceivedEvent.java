package com.Keffisor21.JDAExpansion.API;

public class MessageConsoleReceivedEvent {
	private String contentRaw;
	
	public MessageConsoleReceivedEvent(String contentRaw) {
		this.contentRaw = contentRaw;
	}
	
	public String getContentRaw() {
		return contentRaw;
	}

}
