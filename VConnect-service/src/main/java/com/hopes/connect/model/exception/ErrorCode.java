package com.hopes.connect.model.exception;

/**
 * Enum to suggest possible causes of custom exceptions on service side
 * 
 * @author SaNNy - Sep 8, 2018
 * 
 */
public enum ErrorCode {
	
	ZERO("Generic exception"),
	ONE("Required values NULL while registration"),
	TWO("Value Duplicate"),
	THREE("Incorrect identity type"),
	FOUR("Incorrect service - client registration attempt"),
	FIVE("Client - Service relation already registered"),
	SIX("Client not registered"),
	SEVEN("User not registered to any Client");

	private final String value;

	private ErrorCode(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
