package com.hopes.connect.model.exception;

/**
 * @author SaNNy - Sep 8, 2018
 * 
 * desc: Enum to suggest possible causes of custom exceptions on service side
 */
public enum ErrorCode {
	
	ZERO("Generic exception"),
	ONE("Required values NULL while registration"),
	TWO("Value Duplicate"),
	THREE("Incorrect identity type");

	private final String value;

	private ErrorCode(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
