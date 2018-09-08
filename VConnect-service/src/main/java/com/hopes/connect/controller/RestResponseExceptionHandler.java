package com.hopes.connect.controller;

import java.nio.file.AccessDeniedException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.MetaError;
import com.hopes.connect.model.exception.RegistrationException;

/**
 * @author SaNNy - Sep 8, 2018
 */

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger LOGGER = Logger.getLogger(RestResponseExceptionHandler.class);

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String responseBody = "Illegal argument exception";
		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		String responseBody = "Access Denied";
		return new ResponseEntity<Object>(responseBody, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	// For custom exceptions we send string marking the value of ErrorCode enum. We
	// get the enum from string value
	@ExceptionHandler({ RegistrationException.class })
	public ResponseEntity<Object> handleInvalidClientRegistrationIdException(Exception ex, WebRequest request) {

		ErrorCode errorCode;
		try {
			errorCode = ErrorCode.valueOf(ex.getMessage());
		} catch (Exception e) {
			LOGGER.error("Error converting exception enum value, setting default");
			errorCode = ErrorCode.ZERO;
		}

		MetaError regError = new MetaError(HttpStatus.BAD_REQUEST, errorCode);
		return new ResponseEntity<Object>(regError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
