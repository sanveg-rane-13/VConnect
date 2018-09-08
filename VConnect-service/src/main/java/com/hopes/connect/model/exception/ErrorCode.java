package com.hopes.connect.model.exception;

/**
 * @author SaNNy - Sep 8, 2018
 * 
 * desc: Enum to suggest possible causes of custom exceptions on service side
 */
public enum ErrorCode {
	
	ZERO("Generic exception"),
	ONE("Null Values in Client Registration"),
	TWO("Client Name Duplicate"),
	THREE("Client Registration ID Duplicate");

	private final String value;

	private ErrorCode(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
