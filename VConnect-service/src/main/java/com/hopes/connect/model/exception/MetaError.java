package com.hopes.connect.model.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * @author SaNNy - Sep 8, 2018
 */
public class MetaError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	private ErrorCode errorCode;
	private String errorDescription;

	/**
	 * @param httpStatus
	 * @param message
	 * @param handleOperation
	 * 
	 *            Generate a custom error for custom exceptions Handle operation
	 *            indicates action to be taken on client side
	 */
	public MetaError(HttpStatus httpStatus, ErrorCode errorCode) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorCode.getValue();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
